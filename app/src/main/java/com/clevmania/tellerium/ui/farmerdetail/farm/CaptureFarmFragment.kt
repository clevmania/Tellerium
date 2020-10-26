package com.clevmania.tellerium.ui.farmerdetail.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.clevmania.tellerium.R
import com.clevmania.tellerium.data.FarmEntity
import com.clevmania.tellerium.ui.farmerdetail.FarmerDetailViewModel
import com.clevmania.tellerium.utils.EventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_capture_farm.*


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
            farmInfo.observe(viewLifecycleOwner, EventObserver{
                populateView(it)
            })
        }
    }

    private fun populateView(it: FarmEntity){
        latLngList.add(LatLng(it.lat_one,it.lng_one))
        latLngList.add(LatLng(it.lat_two,it.lng_two))
        latLngList.add(LatLng(it.lat_three,it.lng_three))
        latLngList.add(LatLng(it.lat_three,it.lng_three))
        tvFarmName.text = it.farm_name
        tvFarmLocation.text = it.farm_location
        tvFarmCoordinates.text = buildCoordinates(it.lat_one,it.lng_one,
            it.lat_two,it.lng_two,it.lat_three,it.lng_three,it.lat_four,it.lng_four)
    }

    private fun buildCoordinates(vararg double: Double): String{
        val coordinates  = StringBuilder()
        double.forEach {
            coordinates.append(it)
        }
        return coordinates.toString()
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
                .addAll(latLngList)
//                .add(LatLng(6.509544, 3.371094), LatLng(6.549890, 3.360590),
//                    LatLng(6.538200, 3.383590), LatLng(6.532310, 3.382380))
                .strokeColor(ContextCompat.getColor(requireContext(),R.color.colorAccent))
                .strokeWidth(2F)
                .fillColor(ContextCompat.getColor(requireContext(),R.color.colorAccent))
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