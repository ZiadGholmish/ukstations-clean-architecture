package com.citymapper.app.domain.models
import com.citymapper.app.data.remote.models.arrivaltimes.ArrivalTimeModel

data class StopPointArrivalTimesModel(val arrivals: List<ArrivalTimeModel>)
