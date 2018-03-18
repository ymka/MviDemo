package net.ymka.mvidemo.main.changes

import net.ymka.mvidemo.main.UsersListState

interface UsersListChanges {

    fun apply(previousState: UsersListState): UsersListState

}
