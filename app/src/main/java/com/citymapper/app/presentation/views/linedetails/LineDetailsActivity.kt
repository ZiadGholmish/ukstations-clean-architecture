package com.citymapper.app.presentation.views.linedetails

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.citymapper.app.R
import com.citymapper.app.app.CitymapperApp
import com.citymapper.app.dagger.ViewModelFactory
import com.citymapper.app.presentation.views.nearbystations.NearbyStationsPresenter
import com.citymapper.app.presentation.views.nearbystations.NearbyStationsVM
import com.citymapper.app.presentation.views.nearbystations.adapter.CustomInfoWindowGoogleMap
import com.citymapper.app.presentation.views.nearbystations.adapter.StopPointAdapter
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_line_details.*
import javax.inject.Inject

class LineDetailsActivity : AppCompatActivity(), LineDetailsController,
        OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {


    @Inject
    lateinit var mPresenter: LineDetailsPresenter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_details)
        setupRecycler()
        setupGoogleMap()
    }


    /**
     *  Obtain the SupportMapFragment and get notified when
     *  the map is ready to be used.
     */
    private fun setupGoogleMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map)
                as SupportMapFragment
        mapFragment.retainInstance = true
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnInfoWindowClickListener(this)
        mMap.setInfoWindowAdapter(CustomInfoWindowGoogleMap(this))
        //recommended by the google to fix the stack overflow exception
        mMap.isIndoorEnabled = false
        setupPresenterAndVM()
    }

    private fun setupPresenterAndVM() {
        (application as CitymapperApp).appComponent.inject(this)
        mPresenter.attachView(this)
        val lineDetailsVM = ViewModelProviders.of(this,
                viewModelFactory).get(LineDetailsVM::class.java)
        mPresenter.initPresenter(lineDetailsVM)
    }

    private fun setupRecycler() {
        val linearLayout = LinearLayoutManager(this)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        lineStopPointsRecycler.layoutManager = linearLayout
    }


    override fun onInfoWindowClick(p0: Marker?) {
    }

}
