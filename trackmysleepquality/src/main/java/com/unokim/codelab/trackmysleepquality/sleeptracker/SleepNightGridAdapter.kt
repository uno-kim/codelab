package com.unokim.codelab.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unokim.codelab.trackmysleepquality.database.SleepNight
import com.unokim.codelab.trackmysleepquality.databinding.ListItemSleepNightGridBinding

class SleepNightGridAdapter(private val clickListener: SleepNightListener) :
    ListAdapter<SleepNight, SleepNightGridAdapter.ViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(private val binding: ListItemSleepNightGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: SleepNight,
            clickListener: SleepNightListener
        ) {
            // you need tell the binding object about your new SleepNight
            binding.sleep = item
            binding.clickListener = clickListener
            // This call is an optimization that asks data binding to execute any pending bindings right away.
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightGridBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
