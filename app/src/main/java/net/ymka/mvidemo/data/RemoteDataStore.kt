package net.ymka.mvidemo.data

import io.reactivex.Single
import net.ymka.mvidemo.data.entity.UserEntity

interface RemoteDataStore {

    fun requestUsers(): Single<List<UserEntity>>

}
