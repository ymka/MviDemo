package net.ymka.mvidemo.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.ymka.mvidemo.di.activity.MainActivityModule
import net.ymka.mvidemo.di.scope.ActivityScope
import net.ymka.mvidemo.main.MainActivity

@Module
abstract class ActivitiesBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

}
