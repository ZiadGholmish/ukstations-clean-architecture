package com.citymapper.app.presentation.utils

import android.support.v7.util.DiffUtil
import com.citymapper.app.domain.models.stoppoint.StopPointModel

class StopPointDiffCallback constructor(private val mOldStopPointModels: List<StopPointModel>, private val mNewStopPointModels: List<StopPointModel>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldStopPointModels.size
    }

    override fun getNewListSize(): Int {
        return mNewStopPointModels.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldStopPointModels[oldItemPosition].id == mNewStopPointModels[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldStopPoint = mOldStopPointModels[oldItemPosition]
        val newStopPoint = mNewStopPointModels[newItemPosition]
        return oldStopPoint.toString() == newStopPoint.toString()
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}