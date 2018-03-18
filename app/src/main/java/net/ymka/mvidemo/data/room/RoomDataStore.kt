package net.ymka.mvidemo.data.room

import android.arch.persistence.room.Room
import android.content.Context
import io.reactivex.Completable
import io.reactivex.Single
import net.ymka.mvidemo.common.SchedulersFactory
import net.ymka.mvidemo.data.LocalDataStore
import net.ymka.mvidemo.data.entity.UserEntity
import net.ymka.mvidemo.data.room.entity.UserLocal

class RoomDataStore(private val context: Context,
                    private val schedulersFactory: SchedulersFactory) : LocalDataStore {

    private val store: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    override fun getAllUsers(): Single<List<UserEntity>> {
        return store.userDao().getAll().map { it.map { UserEntity(it.name) } }
                .subscribeOn(schedulersFactory.db())
                .observeOn(schedulersFactory.io())
    }


    override fun saveUsers(users: List<UserEntity>): Completable {
        return Completable.fromCallable({
            store.userDao().insertAll(users.map { UserLocal(null, it.name) })
        })
                .subscribeOn(schedulersFactory.db())
                .observeOn(schedulersFactory.io())
    }
}
