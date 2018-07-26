package com.citymapper.app.presentation.views.linedetails.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.citymapper.app.R
import com.citymapper.app.presentation.models.StopPointSequenceParcelable
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*

class StopPointSequenceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val lineViewVerticalTop: View = view.findViewById(R.id.lineViewVerticalTop)
    val lineStopName: View = view.findViewById(R.id.lineStopName)
    val lineViewVerticalDown: View = view.findViewById(R.id.lineViewVerticalDown)
    val stopPointName: TextView = view.findViewById(R.id.stopPointName)

    fun bind(stopPoint: StopPointSequenceParcelable) {
        handleTopView(stopPoint.hideTop)
        handleCenterView(stopPoint.hideCenter)
        handleBottomView(stopPoint.hideBottom)
        stopPointName.text = stopPoint.commonName
    }

    private fun handleTopView(hide: Boolean) {
        if (hide) {
            lineViewVerticalTop.visibility = View.INVISIBLE
        } else {
            lineViewVerticalTop.visibility = View.VISIBLE
        }
    }

    private fun handleCenterView(hide: Boolean) {
        if (hide) {
            lineStopName.visibility = View.INVISIBLE
        } else {
            lineStopName.visibility = View.VISIBLE
        }
    }

    private fun handleBottomView(hide: Boolean) {
        if (hide) {
            lineViewVerticalDown.visibility = View.INVISIBLE
        } else {
            lineViewVerticalDown.visibility = View.VISIBLE
        }
    }


}