package net.ymka.mvidemo.di

import android.content.Context
import dagger.Binds
import dagger.Module
import net.ymka.mvidemo.App

@Module
abstract class ContextModule {

    @Binds
    abstract fun app(app: App): Context

}
