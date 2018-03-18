package net.ymka.mvidemo.data

import io.reactivex.Completable
import io.reactivex.Single
import net.ymka.mvidemo.data.entity.UserEntity

interface LocalDataStore {

    fun getAllUsers(): Single<List<UserEntity>>

    fun saveUsers(users: List<UserEntity>): Completable

}
