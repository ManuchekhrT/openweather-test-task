package com.kotlin.openweatherapp.ui.weather.details

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.kotlin.openweatherapp.R
import com.kotlin.openweatherapp.data.db.model.Weather
import com.kotlin.openweatherapp.databinding.FragmentWeatherDetailsBinding
import com.kotlin.openweatherapp.ui.base.BaseFragment

class WeatherDetailsFragment : BaseFragment<FragmentWeatherDetailsBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_weather_details


    companion object {
        const val ARG_WEATHER = "ARG_WEATHER"
    }

    private val viewModel: WeatherDetailsViewModel by viewModels()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDataBinding().viewModel = viewModel

        val weather = requireArguments().getSerializable(ARG_WEATHER) as Weather
        getDataBinding().model = weather
    }

}