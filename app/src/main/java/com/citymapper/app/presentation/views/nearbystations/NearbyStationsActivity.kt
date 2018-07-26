package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.citymapper.app.R
import com.citymapper.app.app.CitymapperApp
import com.citymapper.app.dagger.ViewModelFactory
import com.citymapper.app.domain.models.stoppoint.StopPoint
import com.citymapper.app.presentation.views.nearbystations.adapter.CustomInfoWindowGoogleMap
import com.citymapper.app.presentation.views.nearbystations.adapter.StopPointAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_nearby_stations.*
import javax.inject.Inject


class NearbyStationsActivity : AppCompatActivity(), NearbyStationsController, OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {

    @Inject
    lateinit var mPresenter: NearbyStationsPresenter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private  var mMap: GoogleMap? = null

    private var currentMarkers = mutableListOf<Marker>()

    private lateinit var adapter: StopPointAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_stations)
        setupArrivalTimesRecycler()
        setupGoogleMap()
    }

    private fun setupPresenterAndVM() {
        (application as CitymapperApp).appComponent.inject(this)
        mPresenter.attachView(this)
        val nearbyStationsVM = ViewModelProviders.of(this,
                viewModelFactory).get(NearbyStationsVM::class.java)
        mPresenter.initPresenter(nearbyStationsVM)
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
        mMap?.setOnInfoWindowClickListener(this)
        mMap?.setInfoWindowAdapter(CustomInfoWindowGoogleMap(this))
        //recommended by the google to fix the stack overflow exception
        mMap?.isIndoorEnabled = false
        addCustomSettingsToMap()
        setupPresenterAndVM()
        setOnCameraIdeaListener()
    }

    private fun addCustomSettingsToMap() {
        mMap?.setOnMarkerClickListener {
            it.showInfoWindow()
            return@setOnMarkerClickListener true
        }
    }

    private fun setOnCameraIdeaListener() {
        mMap?.let {
            it.setOnCameraIdleListener {
                mPresenter.fetchStopPoints(it.cameraPosition.target)
            }
        }
    }

    override fun onInfoWindowClick(p0: Marker?) {
    }


    /**
     * show the stop points available and set the tag for each marker
     */
    override fun showStopPoints(stopPoints: List<StopPoint>) {
        clearCurrentMarker()
        stopPoints.forEach {
            val marker = mMap?.addMarker(MarkerOptions()
                    .position(LatLng(it.lat, it.lon)))
            marker?.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.tube_icon))
            marker?.tag = it
            marker?.let { currentMarkers.add(marker) }
        }
    }

    private fun setupArrivalTimesRecycler() {
        val linearLayout = LinearLayoutManager(this)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        stopPointRecycler.layoutManager = linearLayout
        adapter = StopPointAdapter(mutableListOf(), applicationContext)
        stopPointRecycler.adapter = adapter
    }

    override fun showArrivalTimes(stopPoints: List<StopPoint>) {
        adapter.setNewStopPoints(stopPoints)
    }

    /**
     * remove the current markers
     */
    private fun clearCurrentMarker() {
        currentMarkers.forEach {
            it.remove()
        }
        currentMarkers.clear()
    }

    override fun moveMapToDefaultLocation(defaultLatLng: LatLng) {
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLatLng, 10f))
    }

    override fun zoomToStations(stopPoint: StopPoint?) {
        stopPoint?.let {
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.lat, it.lon), 10f))
        }
    }

    override fun showNoPointsAvailable() {
        Toast.makeText(this, getString(R.string.no_points_available), Toast.LENGTH_LONG).show()
    }

    override fun showFetchingError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun onDestroy() {
        mMap?.clear()
        stopPointRecycler.adapter = null
        super.onDestroy()
    }

}
