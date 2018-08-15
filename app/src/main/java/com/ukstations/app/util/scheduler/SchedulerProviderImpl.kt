package com.ukstations.app.util.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulerProviderImpl : BaseSchedulerProvider {

    override fun getIOScheduler(): Scheduler {
        return Schedulers.io()
    }

    override fun getUiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}