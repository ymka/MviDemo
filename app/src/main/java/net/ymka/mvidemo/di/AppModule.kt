package net.ymka.mvidemo.di

import android.content.Context
import dagger.Module
import dagger.Provides
import net.ymka.mvidemo.common.DefaultSchedulersFactory
import net.ymka.mvidemo.common.SchedulersFactory
import net.ymka.mvidemo.data.LocalDataStore
import net.ymka.mvidemo.data.RemoteDataStore
import net.ymka.mvidemo.data.retrofit.RetrofitApiProvider
import net.ymka.mvidemo.data.retrofit.RetrofitDataStore
import net.ymka.mvidemo.data.retrofit.ApiProvider
import net.ymka.mvidemo.data.room.RoomDataStore
import net.ymka.mvidemo.domain.repository.DefaultUsersRepository
import net.ymka.mvidemo.domain.repository.UsersRepository
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideLocalDataStore(context: Context, schedulersFactory: SchedulersFactory): LocalDataStore {
        return RoomDataStore(context, schedulersFactory)
    }

    @Provides
    @Singleton
    fun provideRemoteDataStore(apiProvider: ApiProvider): RemoteDataStore {
        return RetrofitDataStore(apiProvider.userApi)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(remoteDataStore: RemoteDataStore, localDataStore: LocalDataStore): UsersRepository {
        return DefaultUsersRepository(remoteDataStore, localDataStore)
    }

    @Provides
    @Singleton
    fun provideSchedulersFactory(): SchedulersFactory = DefaultSchedulersFactory()

    @Provides
    @Singleton
    fun provideRetrofit(): ApiProvider = RetrofitApiProvider()

}
