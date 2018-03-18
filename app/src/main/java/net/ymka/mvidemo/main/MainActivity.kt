package net.ymka.mvidemo.main

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.hannesdorfmann.mosby3.mvi.MviActivity
import dagger.android.AndroidInjection
import io.reactivex.Observable
import net.ymka.mvidemo.R
import net.ymka.mvidemo.main.adapter.ItemClickListener
import net.ymka.mvidemo.main.adapter.UsersListAdapter
import net.ymka.mvidemo.main.adapter.UsersListDIffUtilCallback
import javax.inject.Inject

class MainActivity : MviActivity<UsersListView, UsersListPresenter>(), UsersListView {

    @Inject
    lateinit var presenter: UsersListPresenter

    @Inject
    lateinit var router: UsersListNavigationRouter

    private lateinit var adapter: UsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usersListView = findViewById<RecyclerView>(R.id.usersListView)
        usersListView.layoutManager = LinearLayoutManager(this)
        adapter = UsersListAdapter(itemClickListener)
        usersListView.adapter = adapter
    }

    override fun createPresenter(): UsersListPresenter = presenter

    override fun loadAllUsersIntent(): Observable<Unit> = Observable.just(Unit)

    override fun render(state: UsersListState) {
        val diff = DiffUtil.calculateDiff(UsersListDIffUtilCallback(adapter.list, state.users))
        adapter.list = state.users
        diff.dispatchUpdatesTo(adapter)
        if (state.error) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private val itemClickListener = object : ItemClickListener {
        override fun onItemClicked(position: Int) {
            router.navigateToUserDetails(adapter.list[position])
        }
    }
}
