/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.unokim.codelab.owl.ui.learn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unokim.codelab.owl.databinding.RelatedCourseItemBinding
import com.unokim.codelab.owl.model.Course
import com.unokim.codelab.owl.model.CourseDiff

class RelatedAdapter : ListAdapter<Course, RelatedViewHolder>(CourseDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedViewHolder {
        val binding = RelatedCourseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RelatedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RelatedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class RelatedViewHolder(
    private val binding: RelatedCourseItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(course: Course) {
        binding.run {
            this.course = course
            executePendingBindings()
        }
    }

}