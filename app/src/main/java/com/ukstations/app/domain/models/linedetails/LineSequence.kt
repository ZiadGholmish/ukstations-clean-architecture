package com.ukstations.app.domain.models.linedetails

import com.ukstations.app.domain.models.stoppoint.StopPoint

data class LineSequence(val lineId: String, val lineName: String, val direction: String,
                        val branchId: Int, val stopPoint: List<StopPoint>)