package com.ukstations.app.data.remote.models

sealed class RequestState {

    object Idle : RequestState()

    object Loading : RequestState()

    object Complete : RequestState()

}