package net.ymka.mvidemo.main.changes

import net.ymka.mvidemo.main.UsersListState

class ErrorRetrievingUsersListChanges : UsersListChanges {

    override fun apply(previousState: UsersListState): UsersListState {
        return previousState.copy(error = true)
    }
}
