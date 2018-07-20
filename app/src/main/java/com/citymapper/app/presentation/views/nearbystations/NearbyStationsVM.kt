package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.ViewModel
import com.citymapper.app.data.remote.repository.RepositoryImpl
import javax.inject.Inject

class NearbyStationsVM @Inject constructor(val repositoryImpl: RepositoryImpl) : ViewModel(){


    fun loadStopPointsTest(){
        repositoryImpl.fetchStopPointsByLocation("NaptanRailStation" ,
                1000 , 51.583157 , -0.074757).subscribe {
        }
    }

}