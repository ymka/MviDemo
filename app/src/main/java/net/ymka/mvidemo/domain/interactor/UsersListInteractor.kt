package net.ymka.mvidemo.domain.interactor

import io.reactivex.Observable
import net.ymka.mvidemo.domain.entity.User

interface UsersListInteractor {

    fun getAllUsers(): Observable<List<User>>

}
