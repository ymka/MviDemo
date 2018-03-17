package net.ymka.mvidemo.main

import net.ymka.mvidemo.domain.entity.User

interface UsersListNavigationRouter {

    fun navigateToUserDetails(user: User)

}
