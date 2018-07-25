package com.citymapper.app.domain.models.linedetails

import com.citymapper.app.domain.models.stoppoint.StopPointModel

data class StopPointSequenceModel(val lineId: String,
                                  val lineName: String,
                                  val direction: String,
                                  val branchId: Int,
                                  val stopPointModel: List<StopPointModel>,
                                  val serviceType: String)