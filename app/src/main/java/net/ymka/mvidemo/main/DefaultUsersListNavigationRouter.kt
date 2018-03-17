package net.ymka.mvidemo.main

import android.content.Intent
import net.ymka.mvidemo.details.UserDetailsActivity
import net.ymka.mvidemo.domain.entity.User

class DefaultUsersListNavigationRouter(private val activity: MainActivity) : UsersListNavigationRouter {

    override fun navigateToUserDetails(user: User) {
        val intent = Intent(activity, UserDetailsActivity::class.java).apply {
            putExtra(UserDetailsActivity.KEY_USER_NAME, user.name)
        }

        activity.startActivity(intent)
    }

}
