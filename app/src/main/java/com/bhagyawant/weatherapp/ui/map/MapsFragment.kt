package com.bhagyawant.weatherapp.ui.map

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction
import com.bhagyawant.weatherapp.R
import com.bhagyawant.weatherapp.data.db.AppDatabase
import com.bhagyawant.weatherapp.data.db.entities.Bookmark
import com.bhagyawant.weatherapp.ui.BaseFragment
import com.bhagyawant.weatherapp.utils.toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch
import java.util.*

class MapsFragment : BaseFragment(), OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener, GoogleMap.OnMapClickListener {

    private lateinit var mMap: GoogleMap

    val pune = LatLng(18.5204, 73.8567)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMap()
    }


    private fun addMarker(lanlng: LatLng?) {

        var geocoder = Geocoder(activity, Locale.getDefault())
        val addresses: List<Address> =
            lanlng?.latitude?.let {
                geocoder.getFromLocation(
                    it,
                    lanlng.longitude,
                    1
                )
            } as List<Address>

        if (addresses.size > 0) {
            if (addresses[0].locality == null) {
                activity?.toast(getString(R.string.please_zoom))
            } else {
                activity?.toast("${addresses[0].locality}")
                val markerOptions = lanlng?.let {
                    MarkerOptions()
                        .position(it)
                }
                mMap.addMarker(markerOptions)

                activity?.let { showBookmarkDialog(it, lanlng, addresses[0].locality) }
            }
        }


    }

    private fun showBookmarkDialog(context: Context, latlng: LatLng, locality: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.bookmark_q))
        builder.setMessage("Do you want to bookmark ${locality}?")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->

            launch {
                var bookmark =
                    Bookmark(locality, latlng.latitude.toString(), latlng.longitude.toString())

                activity?.let {
                    AppDatabase(it).getBookmarksDao().insert(bookmark)
                    it.toast("Bookmark Added")
                    activity?.onBackPressed()
                }
            }



            dialog.dismiss()


        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            mMap.clear()
            dialog.dismiss()
        }

        builder.show()
    }

    private fun addMap() {
        val mapFragment = SupportMapFragment.newInstance()
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.map, mapFragment)
        fragmentTransaction.commit()
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnCameraMoveListener(this)
        mMap.setOnCameraIdleListener(this)
        mMap.setOnMapClickListener(this)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pune))

    }

    override fun onCameraMove() {
        mMap.clear()

    }

    override fun onCameraIdle() {

    }

    override fun onMapClick(lanlng: LatLng?) {
        mMap.clear()
        addMarker(lanlng)
    }


}