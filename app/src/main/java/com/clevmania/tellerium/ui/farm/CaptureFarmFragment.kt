package com.clevmania.tellerium.ui.farm

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clevmania.tellerium.R
import com.clevmania.tellerium.ui.farmerdetail.FarmerDetailViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class CaptureFarmFragment : Fragment(), OnMapReadyCallback {
    private lateinit var viewModel: FarmerDetailViewModel
    private val latLngList = arrayListOf<LatLng>()


    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment : SupportMapFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_capture_farm, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel){

        }
    }

    companion object {
        fun newInstance(viewModel: FarmerDetailViewModel) =
            CaptureFarmFragment().apply { this.viewModel = viewModel }
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.let { mMap = it }

        mMap.uiSettings.isZoomControlsEnabled = true

        val poly = mMap.addPolygon(
            PolygonOptions()
                .add(LatLng(6.509544, 3.371094), LatLng(6.549890, 3.360590),
                    LatLng(6.538200, 3.383590), LatLng(6.532310, 3.382380))
                .strokeColor(Color.RED)
                .strokeWidth(2F)
                .fillColor(Color.BLUE)
        )

        val bounds = LatLngBounds.builder()
        poly.points.forEach { bounds.include(it) }
        bounds.build().center?.let {
            val markerOptions = MarkerOptions().position(it).title("Farm Location")
            mMap.addMarker(markerOptions)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(it))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it,13f))
        }

    }
}