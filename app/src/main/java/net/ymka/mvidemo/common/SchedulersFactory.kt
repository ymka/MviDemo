package net.ymka.mvidemo.common

import io.reactivex.Scheduler

interface SchedulersFactory {

    fun main(): Scheduler
    fun io(): Scheduler
    fun db(): Scheduler

}
