package net.ymka.mvidemo.main

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import net.ymka.mvidemo.common.SchedulersFactory
import net.ymka.mvidemo.domain.interactor.UsersListInteractor
import net.ymka.mvidemo.main.changes.AllUsersListChanges
import net.ymka.mvidemo.main.changes.ErrorRetrievingUsersListChanges
import net.ymka.mvidemo.main.changes.UsersListChanges

class UsersListPresenter(private val interactor: UsersListInteractor,
                         private val schedulersFactory: SchedulersFactory) : MviBasePresenter<UsersListView, UsersListState>() {

    override fun bindIntents() {
        val initialState = UsersListState(emptyList())

        val viewState = getAllUsers().scan(initialState, ::reduce)
                .observeOn(schedulersFactory.main())

        subscribeViewState(viewState, UsersListView::render)
    }

    private fun getAllUsers(): Observable<UsersListChanges> {
        return intent(UsersListView::loadAllUsersIntent)
                .switchMap { interactor.getAllUsers() }
                .map<UsersListChanges> { AllUsersListChanges(it) }
                .onErrorResumeNext(
                    Observable.just(ErrorRetrievingUsersListChanges())
                )
    }

    private fun reduce(previousState: UsersListState, changes: UsersListChanges): UsersListState {
        return changes.apply(previousState)
    }

}
