package net.ymka.mvidemo.common

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class DefaultSchedulersFactory : SchedulersFactory {

    private val dbScheduler = Schedulers.from(Executors.newSingleThreadExecutor())

    override fun main(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()

    override fun db(): Scheduler = dbScheduler
}
