/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.unokim.codelab.marsrealestate

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.unokim.codelab.marsrealestate.network.MarsProperty
import com.unokim.codelab.marsrealestate.overview.MarsApiStatus
import com.unokim.codelab.marsrealestate.overview.PhotoGridAdapter
import timber.log.Timber

// You want the final Uri object to use the HTTPS scheme,
// because the server you pull the images from requires that scheme.
// To use the HTTPS scheme, append buildUpon.scheme("https") to the toUri builder.
// The toUri() method is a Kotlin extension function from the Android KTX core library,
// so it just looks like it's part of the String class.

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Timber.i("imgView = ${imgView.tag}, imgUrl = $imgUrl")
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

// Finally, use a BindingAdapter to initialize the PhotoGridAdapter with the list of MarsProperty objects.
// Using a BindingAdapter to set the RecyclerView data causes data binding to automatically observe the LiveData
// for the list of MarsProperty objects. Then the binding adapter is called automatically when the MarsProperty list changes.

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsProperty>?) {
    Timber.i("bindRecyclerView")
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: MarsApiStatus?
) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}