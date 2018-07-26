package com.citymapper.app.dagger

import android.arch.lifecycle.ViewModel
import com.citymapper.app.presentation.views.linedetails.LineDetailsVM
import com.citymapper.app.presentation.views.nearbystations.NearbyStationsVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NearbyStationsVM::class)
    abstract fun bindNearbyStationsVM(nearbyStationsVM: NearbyStationsVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LineDetailsVM::class)
    abstract fun bindLineDetailsVMVM(liveDetailsVM: LineDetailsVM): ViewModel


}