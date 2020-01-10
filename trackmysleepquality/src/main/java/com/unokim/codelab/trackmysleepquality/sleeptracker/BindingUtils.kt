package com.unokim.codelab.trackmysleepquality.sleeptracker

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.unokim.codelab.trackmysleepquality.R
import com.unokim.codelab.trackmysleepquality.convertDurationToFormatted
import com.unokim.codelab.trackmysleepquality.convertNumericQualityToString
import com.unokim.codelab.trackmysleepquality.database.SleepNight

// It turns out that with this new click-handling mechanism, it is now possible for the binding adapters
// to get called with a null value for item. In particular, when the app starts, the LiveData starts as null,
// so you need to add null checks to each of the adapters.

@BindingAdapter("sleepDurationFormatted")
fun TextView.setSleepDurationFormatted(item: SleepNight?) {
    item?.let {
        text = convertDurationToFormatted(it.startTimeMilli, it.endTimeMilli, context.resources)
    }
}

@BindingAdapter("sleepQualityString")
fun TextView.setSleepQualityString(item: SleepNight?) {
    item?.let {
        text = convertNumericQualityToString(it.sleepQuality, context.resources)
    }
}

@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: SleepNight?) {
    item?.let {
        setImageResource(
            when (it.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            }
        )
    }
}