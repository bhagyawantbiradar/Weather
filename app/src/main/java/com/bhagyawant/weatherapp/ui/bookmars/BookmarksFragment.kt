package com.bhagyawant.weatherapp.ui.bookmars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bhagyawant.weatherapp.R
import com.bhagyawant.weatherapp.databinding.FragmentBookmarksBinding
import com.bhagyawant.weatherapp.network.responses.WeatherResponse
import com.bhagyawant.weatherapp.utils.hide
import com.bhagyawant.weatherapp.utils.show
import com.bhagyawant.weatherapp.utils.toast
import kotlinx.android.synthetic.main.fragment_bookmarks.*


class BookmarksFragment : Fragment(), WeatherApiListener {

    private var bookmarksBinding: FragmentBookmarksBinding? = null;



    companion object{
        var bookmarksFragment : BookmarksFragment? = null
        fun getInstance():BookmarksFragment{
            if (bookmarksFragment==null){
                bookmarksFragment = BookmarksFragment()
            }
            return bookmarksFragment as BookmarksFragment
        }
    }

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
        val viewModel = ViewModelProvider(this).get(BookmarksViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.weatherApiListener = this

    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        bookmarksBinding = null
        super.onDestroyView()
    }


    override fun onStarted() {
        activity?.toast("Started")
        progress_bar.show()
    }

    override fun onSuccess(weatherResponse: WeatherResponse) {
        activity?.toast("${weatherResponse.name}")
    }

    override fun onFailure() {
        activity?.toast("Failure")
        progress_bar.hide()
    }
}