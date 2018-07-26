package com.citymapper.app.presentation.views.linedetails

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.WindowManager
import android.widget.Toast
import com.citymapper.app.R
import com.citymapper.app.app.AppConstants
import com.citymapper.app.app.CitymapperApp
import com.citymapper.app.dagger.ViewModelFactory
import com.citymapper.app.presentation.models.StopPointSequence
import com.citymapper.app.presentation.views.linedetails.adapters.StopPointSequenceAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_line_details.*
import javax.inject.Inject


class LineDetailsActivity : AppCompatActivity(), LineDetailsController,
        OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    @Inject
    lateinit var mPresenter: LineDetailsPresenter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_details)
        setupActionBar()
        setupRecycler()
        setupGoogleMap()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        changeStatusBarColor(R.color.colorTube)
        title = ""
    }

    private fun changeStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= 21) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
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
        //recommended by the google to fix the stack overflow exception
        mMap?.isIndoorEnabled = false
        setupPresenterAndVM()
    }

    private fun setupPresenterAndVM() {
        (application as CitymapperApp).appComponent.inject(this)
        mPresenter.attachView(this)
        val lineDetailsVM = ViewModelProviders.of(this,
                viewModelFactory).get(LineDetailsVM::class.java)
        mPresenter.initPresenter(lineDetailsVM, intent.getParcelableExtra(AppConstants.ARRIVAL_TIME_INTENT_NAME))
    }

    private fun setupRecycler() {
        val linearLayout = LinearLayoutManager(this)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        lineStopPointsRecycler.layoutManager = linearLayout
    }

    override fun onInfoWindowClick(p0: Marker?) {
    }

    /**
     * change the stop points to markers on the map
     * and then draw the Polyline
     */
    override fun showSequenceStopPoints(stopPoint: List<StopPointSequence>) {
        showStopPointsForLinea(stopPoint)
        stopPoint.forEach {
            val marker = mMap?.addMarker(MarkerOptions()
                    .position(LatLng(it.lat, it.lon)))
            marker?.snippet = it.commonName
            marker?.tag = it
        }
        drawRouteOnMap(stopPoint.map { LatLng(it.lat, it.lon) })

    }

    private fun drawRouteOnMap(positions: List<LatLng>) {
        val options = PolylineOptions().width(10f).color(Color.BLUE).geodesic(true)
        options.addAll(positions)
        if (positions.isNotEmpty()) {
            mMap?.addPolyline(options)
            val cameraPosition = CameraPosition.Builder()
                    .target(LatLng(positions[0].latitude, positions[0].longitude))
                    .zoom(15f).build()
            mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    private fun showStopPointsForLinea(stopPoints: List<StopPointSequence>) {
        val adapter = StopPointSequenceAdapter(stopPoints)
        lineStopPointsRecycler.adapter = adapter
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

    override fun showLineName(lineName: String) {
        tvLineName.text = lineName
    }

    override fun onDestroy() {
        mMap?.clear()
        super.onDestroy()
    }

}
