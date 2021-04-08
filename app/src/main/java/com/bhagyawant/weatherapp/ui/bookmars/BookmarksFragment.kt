package com.bhagyawant.weatherapp.ui.bookmars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhagyawant.weatherapp.R
import com.bhagyawant.weatherapp.data.db.AppDatabase
import com.bhagyawant.weatherapp.data.db.entities.Bookmark
import com.bhagyawant.weatherapp.databinding.FragmentBookmarksBinding
import com.bhagyawant.weatherapp.network.responses.WeatherResponse
import com.bhagyawant.weatherapp.ui.BaseFragment
import com.bhagyawant.weatherapp.ui.bookmars.adapter.BookmarksAdapter
import com.bhagyawant.weatherapp.utils.hide
import com.bhagyawant.weatherapp.utils.show
import com.bhagyawant.weatherapp.utils.toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_bookmarks.*
import kotlinx.coroutines.launch


class BookmarksFragment : BaseFragment(), WeatherApiListener,
    BookmarksAdapter.BookmarkItemClickListener {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<MaterialCardView>
    private var bookmarksBinding: FragmentBookmarksBinding? = null;
    var viewModel: BookmarksViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentBookmarksBinding.bind(view)
        bookmarksBinding = binding
        viewModel = ViewModelProvider(this).get(BookmarksViewModel::class.java)
        binding.viewModel = viewModel
        viewModel!!.weatherApiListener = this

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottomSheetBehavior = BottomSheetBehavior.from(cityBottomSheetLayout)

        fab_add.setOnClickListener {
            val action = BookmarksFragmentDirections.actionGoToMap()
            Navigation.findNavController(it).navigate(action)

        }

        rv_bookmarks.layoutManager = LinearLayoutManager(context)

        launch {
            context?.let {
                val bookmarks = AppDatabase(it).getBookmarksDao().getBookmarks()
                if (bookmarks.size > 0) {
                    tv_empty_msg.visibility = View.GONE
                    rv_bookmarks.visibility = View.VISIBLE
                    rv_bookmarks.adapter = BookmarksAdapter(bookmarks, this@BookmarksFragment)
                }

            }
        }
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        bookmarksBinding = null
        super.onDestroyView()
    }


    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(weatherResponse: WeatherResponse) {
        progress_bar.hide()

        name.text = weatherResponse.name
        maxMin.text = String.format(
            getString(R.string.max_min),
            weatherResponse.main.temp_max,
            weatherResponse.main.temp_min
        )
        temp.text = String.format(getString(R.string.temp), weatherResponse.main.temp)
        humidity.text = String.format(getString(R.string.humidity), weatherResponse.main.humidity)
        overview.text = String.format(
            getString(R.string.overview),
            weatherResponse.main.temp,
            weatherResponse.main.feels_like,
            weatherResponse.wind.speed,
            weatherResponse.main.pressure
        )

        if (weatherResponse.weather.size > 0) {
            var weatherDesc = weatherResponse.weather.get(0).description
            description.text = weatherDesc

            if (weatherDesc.contains("rain")) {
                rain.visibility = View.VISIBLE
            }

            if (weatherDesc.contains("cloud")) {
                cloud.visibility = View.VISIBLE
            }

        }

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

    }


    override fun onFailure() {
        progress_bar.hide()
        context?.toast(getString(R.string.failed_to_load_weather))
    }

    override fun onItemClicked(bookmark: Bookmark) {
        viewModel?.getWeatherForecast(bookmark)
    }
}

