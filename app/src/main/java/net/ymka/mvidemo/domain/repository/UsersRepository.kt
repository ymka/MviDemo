package net.ymka.mvidemo.domain.repository

import io.reactivex.Observable
import net.ymka.mvidemo.data.entity.UserEntity

interface UsersRepository {

    fun getUsersList(): Observable<List<UserEntity>>

}
