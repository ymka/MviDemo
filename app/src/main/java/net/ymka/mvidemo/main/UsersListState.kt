package net.ymka.mvidemo.main

import net.ymka.mvidemo.domain.entity.User

data class UsersListState(
        val users: List<User>,
        val error: Boolean = false
)
