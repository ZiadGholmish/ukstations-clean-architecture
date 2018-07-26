package com.citymapper.app.util

import com.citymapper.app.data.remote.models.linedetails.StopPointSequence
import com.citymapper.app.domain.models.arrivals.ArrivalTime
import com.citymapper.app.domain.models.stoppoint.StopPoint
import com.citymapper.app.presentation.models.ArrivalTimeParcelable
import com.citymapper.app.presentation.models.StopPointSequenceParcelable


fun StopPoint.toParcelable(): StopPointSequenceParcelable {
    return StopPointSequenceParcelable(this.id,
            this.commonName, this.distance, this.lat, this.lon)
}


fun ArrivalTime.toParcelable(): ArrivalTimeParcelable {
    return ArrivalTimeParcelable(this.id,
            this.lineName, this.lineId, this.expectedArrival, this.direction)
}
