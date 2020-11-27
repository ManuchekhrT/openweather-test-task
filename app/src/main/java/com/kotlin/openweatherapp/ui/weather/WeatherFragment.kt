package com.kotlin.openweatherapp.ui.weather

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.kotlin.openweatherapp.MainApplication
import com.kotlin.openweatherapp.R
import com.kotlin.openweatherapp.data.db.model.Weather
import com.kotlin.openweatherapp.databinding.FragmentWeatherBinding
import com.kotlin.openweatherapp.ui.base.BaseFragment
import com.kotlin.openweatherapp.ui.weather.details.WeatherDetailsFragment
import kotlinx.android.synthetic.main.dialog_create_edit_weather.view.*

class WeatherFragment : BaseFragment<FragmentWeatherBinding>(),
    WeatherAdapter.OnWeatherClickListener, WeatherListener {

    override val layoutRes: Int
        get() = R.layout.fragment_weather

    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory((requireActivity().application as MainApplication).repository)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDataBinding().viewModel = viewModel
        getDataBinding().listener = this
        val mAdapter = WeatherAdapter(this)
        getDataBinding().adapter = mAdapter

        syncInitialWeathers()

        viewModel.getWeathers().observe(viewLifecycleOwner, Observer { weathers ->
            weathers.let { mAdapter.submitList(weathers) }
        })
    }

    private fun syncInitialWeathers() {
        if (!MainApplication.prefs.isAdded) {
            viewModel.syncWeather(resources.getString(R.string.title_city_moscow))
            viewModel.syncWeather(resources.getString(R.string.title_city_petersburg))
            MainApplication.prefs.isAdded = true
                requireContext()
        }
    }

    override fun onWeatherClick(weather: Weather) {
        Navigation.findNavController(requireView()).navigate(
            R.id.action_WeatherFragment_to_WeatherDetailsFragment,
            bundleOf(WeatherDetailsFragment.ARG_WEATHER to weather)
        )
    }

    override fun onDeleteWeatherClick(weather: Weather) {
        viewModel.deleteWeather(weather)
    }

    override fun onEditWeatherClick(weather: Weather) {
        val mDialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_create_edit_weather, null)
        mDialogView.edt_city.setText(weather.city)
        mDialogView.edt_city.setSelection(mDialogView.edt_city.text.length)

        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setTitle(resources.getString(R.string.title_edit_weather))
        val mAlertDialog = mBuilder.show()
        mDialogView.btn_submit.setOnClickListener {
            val city = mDialogView.edt_city.text.toString()
            if (city.isBlank()) {
                mDialogView.edt_city.error = resources.getString(R.string.error_empty_field)
                return@setOnClickListener
            } else {
                viewModel.syncWeather(city, id = weather.id)
            }
            mAlertDialog.dismiss()
        }
        mDialogView.btn_cancel.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    override fun onCreateBtnClick() {
        val mDialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_create_edit_weather, null)
        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setTitle(resources.getString(R.string.title_create_weather))
        val mAlertDialog = mBuilder.show()
        mDialogView.btn_submit.setOnClickListener {
            val city = mDialogView.edt_city.text.toString()
            if (city.isBlank()) {
                mDialogView.edt_city.error = resources.getString(R.string.error_empty_field)
                return@setOnClickListener
            } else {
                viewModel.syncWeather(city)
            }
            mAlertDialog.dismiss()
        }
        mDialogView.btn_cancel.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }


}