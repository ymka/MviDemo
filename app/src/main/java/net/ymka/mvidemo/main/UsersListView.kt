package net.ymka.mvidemo.main

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface UsersListView : MvpView {

    fun render(state: UsersListState)

    fun loadAllUsersIntent(): Observable<Unit>

}
