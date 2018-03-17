package net.ymka.mvidemo.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import net.ymka.mvidemo.R

class UserDetailsActivity : AppCompatActivity() {

    companion object {
        const val KEY_USER_NAME = "net.ymka.mvidemo.details.UserDetailsActivity.KEY_USER_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        val name = intent.getStringExtra(KEY_USER_NAME) ?: getString(R.string.unknown_user_name)
        val nameView = findViewById<TextView>(R.id.userNameView)
        nameView.text = name
    }
}
