package com.ukstations.app.util

import com.ukstations.app.domain.models.arrivals.ArrivalTime
import com.ukstations.app.domain.models.stoppoint.StopPoint
import com.ukstations.app.presentation.models.ArrivalTimeParcelable
import com.ukstations.app.presentation.models.StopPointSequence


fun StopPoint.toParcelable(): StopPointSequence {
    return StopPointSequence(this.id,
            this.commonName, this.distance, this.lat, this.lon)
}


fun ArrivalTime.toParcelable(lat: Double, lon: Double): ArrivalTimeParcelable {
    return ArrivalTimeParcelable(this.id,
            this.lineName, this.lineId, this.naptanId, this.expectedArrival, this.direction, lat, lon)
}
