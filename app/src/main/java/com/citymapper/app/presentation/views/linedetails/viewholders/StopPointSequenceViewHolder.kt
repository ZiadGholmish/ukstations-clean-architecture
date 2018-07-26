package com.citymapper.app.presentation.views.linedetails.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.citymapper.app.R
import com.citymapper.app.domain.models.stoppoint.StopPointModel

class StopPointSequenceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val lineViewVerticalTop: View = view.findViewById(R.id.lineViewVerticalTop)
    val lineStopName: View = view.findViewById(R.id.lineStopName)
    val lineViewVerticalDown: View = view.findViewById(R.id.lineViewVerticalDown)
    val stopPointName: View = view.findViewById(R.id.stopPointName)

    fun bind(hideTop: Boolean, hideCenter: Boolean, hideDown: Boolean) {

        if (hideTop) {
            lineViewVerticalTop.visibility = View.INVISIBLE
        } else {
            lineViewVerticalTop.visibility = View.VISIBLE
        }

        if (hideCenter) {
            lineStopName.visibility = View.INVISIBLE
        } else {
            lineStopName.visibility = View.VISIBLE
        }

        if (hideDown) {
            lineViewVerticalDown.visibility = View.INVISIBLE
        } else {
            lineViewVerticalDown.visibility = View.VISIBLE
        }

    }
}