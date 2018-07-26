package com.citymapper.app.domain.models.linedetails


data class LineDetails(val lineId: String,
                       val lineName: String, val stopPointSequences: List<LineSequence>)