package com.ukstations.app.utils

import com.ukstations.app.util.scheduler.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class FakeSchedulerImpl : BaseSchedulerProvider {

    override fun getIOScheduler(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun getUiScheduler(): Scheduler {
        return Schedulers.trampoline()
    }
}