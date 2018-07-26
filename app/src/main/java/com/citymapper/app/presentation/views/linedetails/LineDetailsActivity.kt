package com.citymapper.app.presentation.views.linedetails

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.citymapper.app.R
import com.citymapper.app.app.CitymapperApp
import com.citymapper.app.dagger.ViewModelFactory
import com.citymapper.app.domain.models.stoppoint.StopPointModel
import com.citymapper.app.presentation.views.nearbystations.NearbyStationsPresenter
import com.citymapper.app.presentation.views.nearbystations.NearbyStationsVM
import com.citymapper.app.presentation.views.nearbystations.adapter.CustomInfoWindowGoogleMap
import com.citymapper.app.presentation.views.nearbystations.adapter.StopPointAdapter
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_line_details.*
import javax.inject.Inject
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*


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
        mPresenter.initPresenter(lineDetailsVM, intent.getStringExtra("id"), intent.getStringExtra("direction"))
    }

    private fun setupRecycler() {
        val linearLayout = LinearLayoutManager(this)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        lineStopPointsRecycler.layoutManager = linearLayout
    }


    override fun onInfoWindowClick(p0: Marker?) {
    }


    override fun showSequenceStopPoints(stopPointModel: List<StopPointModel>) {
        stopPointModel.forEach {
            val marker = mMap.addMarker(MarkerOptions()
                    .position(LatLng(it.lat, it.lon)))
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.tube_icon))
            marker.tag = it
        }
        drawRouteOnMap(stopPointModel.map { LatLng(it.lat, it.lon) })
    }


    private fun drawRouteOnMap(positions: List<LatLng>) {
        val options = PolylineOptions().width(10f).color(Color.BLUE).geodesic(true)
        options.addAll(positions)
        if (positions.isNotEmpty()) {
            mMap.addPolyline(options)
            val cameraPosition = CameraPosition.Builder()
                    .target(LatLng(positions[0].latitude, positions[0].longitude))
                    .zoom(17f)
                    .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    override fun showNoStopPoints() {
        Toast.makeText(this, getString(R.string.no_points_available), Toast.LENGTH_LONG).show()
    }

    override fun showFetchingError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

}
