package com.citymapper.app.util.scheduler

import io.reactivex.Scheduler

interface BaseSchedulerProvider{

    fun getIOScheduler(): Scheduler

    fun getUiScheduler(): Scheduler
}