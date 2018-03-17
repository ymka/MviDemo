package net.ymka.mvidemo.data.retrofit

import io.reactivex.Single
import net.ymka.mvidemo.data.RemoteDataStore
import net.ymka.mvidemo.data.api.UsersApi
import net.ymka.mvidemo.data.entity.UserEntity

class RetrofitDataStore(private val userApi: UsersApi) : RemoteDataStore {

    override fun requestUsers(): Single<List<UserEntity>> {
        return userApi.allUsers().map {
            it.map { UserEntity(it.login) }
        }
    }

}
