package com.citymapper.app.presentation.views.linedetails.viewholders

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.citymapper.app.R
import com.citymapper.app.presentation.models.StopPointSequenceParcelable
import kotlin.coroutines.experimental.coroutineContext

class StopPointSequenceViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val lineViewVerticalTop: View = view.findViewById(R.id.lineViewVerticalTop)
    val lineViewCenter: View = view.findViewById(R.id.lineViewCenter)
    val lineViewVerticalDown: View = view.findViewById(R.id.lineViewVerticalDown)
    val stopPointName: TextView = view.findViewById(R.id.stopPointName)

    fun bind(stopPoint: StopPointSequenceParcelable) {
        handleTopView(stopPoint.hideTop)
        handleCenterView(stopPoint.hideCenter)
        handleBottomView(stopPoint.hideBottom)
        stopPointName.text = stopPoint.commonName

        if (stopPoint.clickedStation) {
            stopPointName.textSize = 20F
            stopPointName.setTextColor(ContextCompat.getColor(stopPointName.context,
                    R.color.colorBlack))
        } else {
            stopPointName.textSize = 15F
            stopPointName.setTextColor(ContextCompat.getColor(stopPointName.context,
                    R.color.colorGray))
        }
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
            lineViewCenter.visibility = View.INVISIBLE
        } else {
            lineViewCenter.visibility = View.VISIBLE
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