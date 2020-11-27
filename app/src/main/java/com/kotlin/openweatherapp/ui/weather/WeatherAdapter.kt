package com.kotlin.openweatherapp.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.openweatherapp.data.db.model.Weather
import com.kotlin.openweatherapp.databinding.ItemWeatherBinding
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherAdapter(
    private val listener: OnWeatherClickListener
) : ListAdapter<Weather, WeatherAdapter.WeatherViewHolder>(WeatherComparator()) {

    interface OnWeatherClickListener {
        fun onWeatherClick(weather: Weather)
        fun onDeleteWeatherClick(weather: Weather)
        fun onEditWeatherClick(weather: Weather)
    }

    inner class WeatherViewHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {
            binding.model = weather

            with(binding.root) {
                setOnClickListener {
                    listener.onWeatherClick(weather)
                }
                btn_delete.setOnClickListener {
                    listener.onDeleteWeatherClick(weather)
                }
                btn_edit.setOnClickListener {
                    listener.onEditWeatherClick(weather)
                }
            }

            binding.executePendingBindings()
        }
    }

    class WeatherComparator : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.city == newItem.city
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeatherBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

}