package com.unokim.codelab.trackmysleepquality.sleeptracker

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.unokim.codelab.trackmysleepquality.database.SleepNight

//class SleepNightAdapter :
//    ListAdapter<DataItem, RecyclerView.ViewHolder>(SleepNightDiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.bind(item)
//    }
//
//    class ViewHolder private constructor(private val binding: ListItemSleepNightLinearBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(item: SleepNight) {
//            // you need tell the binding object about your new SleepNight
//            binding.sleep = item
//            // This call is an optimization that asks data binding to execute any pending bindings right away.
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ListItemSleepNightLinearBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(binding)
//            }
//        }
//    }
//}

class SleepNightDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}

sealed class DataItem {
    abstract val id: Long

    data class SleepNightItem(val sleepNight: SleepNight) : DataItem() {
        override val id: Long = sleepNight.nightId
    }

    object Header : DataItem() {
        override val id: Long = Long.MIN_VALUE
    }
}