package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.ViewModel
import com.citymapper.app.data.remote.repository.Repository
import javax.inject.Inject

class NearbyStationsVM @Inject constructor(val repository: Repository) : ViewModel(){


    fun loadStopPointsTest(){
        repository.fetchStopPointsByLocation("NaptanRailStation" ,
                1000 , 51.583157 , -0.074757).subscribe {
        }
    }

}