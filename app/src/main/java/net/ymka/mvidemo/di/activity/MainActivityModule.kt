package net.ymka.mvidemo.di.activity

import dagger.Module
import dagger.Provides
import net.ymka.mvidemo.common.SchedulersFactory
import net.ymka.mvidemo.di.scope.ActivityScope
import net.ymka.mvidemo.domain.interactor.DefaultUserListInteractor
import net.ymka.mvidemo.domain.interactor.UsersListInteractor
import net.ymka.mvidemo.domain.repository.UsersRepository
import net.ymka.mvidemo.main.DefaultUsersListNavigationRouter
import net.ymka.mvidemo.main.MainActivity
import net.ymka.mvidemo.main.UsersListNavigationRouter
import net.ymka.mvidemo.main.UsersListPresenter

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun providePresenter(interactor: UsersListInteractor,
                         schedulersFactory: SchedulersFactory): UsersListPresenter {
        return UsersListPresenter(interactor, schedulersFactory)
    }

    @Provides
    @ActivityScope
    fun provideInteractor(usersRepository: UsersRepository,
                          schedulersFactory: SchedulersFactory): UsersListInteractor {
        return DefaultUserListInteractor(usersRepository, schedulersFactory)
    }

    @Provides
    @ActivityScope
    fun provideRouter(activity: MainActivity): UsersListNavigationRouter {
        return DefaultUsersListNavigationRouter(activity)
    }

}
