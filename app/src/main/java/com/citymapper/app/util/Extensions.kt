package com.citymapper.app.util

import com.citymapper.app.domain.models.arrivals.ArrivalTime
import com.citymapper.app.domain.models.stoppoint.StopPoint
import com.citymapper.app.presentation.models.ArrivalTimeParcelable
import com.citymapper.app.presentation.models.StopPointSequence


fun StopPoint.toParcelable(): StopPointSequence {
    return StopPointSequence(this.id,
            this.commonName, this.distance, this.lat, this.lon)
}


fun ArrivalTime.toParcelable(lat: Double, lon: Double): ArrivalTimeParcelable {
    return ArrivalTimeParcelable(this.id,
            this.lineName, this.lineId, this.naptanId, this.expectedArrival, this.direction, lat, lon)
}
