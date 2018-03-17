package net.ymka.mvidemo.domain.interactor

import io.reactivex.Observable
import net.ymka.mvidemo.common.SchedulersFactory
import net.ymka.mvidemo.domain.entity.User
import net.ymka.mvidemo.domain.repository.UsersRepository

class DefaultUserListInteractor(private val usersRepository: UsersRepository,
                                private val schedulersFactory: SchedulersFactory) : UsersListInteractor {

    override fun getAllUsers(): Observable<List<User>> {
        return usersRepository.getUsersList()
                .map { it.map { User(it.name) }.sortedBy { it.name } }
                .subscribeOn(schedulersFactory.io())
    }
}
