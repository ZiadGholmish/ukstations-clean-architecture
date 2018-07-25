package com.citymapper.app.domain.models.linedetails


data class LineDetailsModel(val lineId: String, val lineName: String,val stopPointSequences: List<StopPointSequenceModel>)