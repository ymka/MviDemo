package net.ymka.mvidemo.main.changes

import net.ymka.mvidemo.domain.entity.User
import net.ymka.mvidemo.main.UsersListState

class AllUsersListChanges(private val users: List<User>) : UsersListChanges {

    override fun apply(previousState: UsersListState): UsersListState {
        return previousState.copy(users = users, error = false)
    }
}
