package com.ukstations.app.util

import android.support.v7.util.DiffUtil
import com.ukstations.app.domain.models.stoppoint.StopPoint

class StopPointDiffCallback constructor(private val mOldStopPoints: List<StopPoint>, private val mNewStopPoints: List<StopPoint>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldStopPoints.size
    }

    override fun getNewListSize(): Int {
        return mNewStopPoints.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldStopPoints[oldItemPosition].id == mNewStopPoints[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldStopPoint = mOldStopPoints[oldItemPosition]
        val newStopPoint = mNewStopPoints[newItemPosition]
        return oldStopPoint.toString() == newStopPoint.toString()
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}