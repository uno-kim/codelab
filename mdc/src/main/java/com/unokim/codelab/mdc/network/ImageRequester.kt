package com.unokim.codelab.mdc.network

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache

import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.NetworkImageView
import com.android.volley.toolbox.Volley
import com.unokim.codelab.logger.Logger
import com.unokim.codelab.mdc.application.ShrineApplication

/**
 * Class that handles image requests using Volley.
 */
object ImageRequester {
    private const val TAG = "ImageRequester"

    private val requestQueue: RequestQueue
    private val imageLoader: ImageLoader
    private val maxByteSize: Int

    init {
        Logger.d(TAG, "init")
        val context = ShrineApplication.instance
        requestQueue = Volley.newRequestQueue(context)
        requestQueue.start()
        maxByteSize = calculateMaxByteSize(context)
        imageLoader = ImageLoader(
            requestQueue,
            object : ImageLoader.ImageCache {
                private val lruCache = object : LruCache<String, Bitmap>(maxByteSize) {
                    override fun sizeOf(url: String, bitmap: Bitmap): Int {
                        return bitmap.byteCount
                    }
                }

                @Synchronized
                override fun getBitmap(url: String): Bitmap? {
                    return lruCache.get(url)
                }

                @Synchronized
                override fun putBitmap(url: String, bitmap: Bitmap) {
                    lruCache.put(url, bitmap)
                }
            })
    }

    /**
     * Sets the image on the given [NetworkImageView] to the image at the given URL
     *
     * @param networkImageView [NetworkImageView] to set image on
     * @param url              URL of the image
     */
    fun setImageFromUrl(networkImageView: NetworkImageView, url: String) {
        Logger.d(TAG, "setImageFromUrl")
        networkImageView.setImageUrl(url, imageLoader)
    }

    private fun calculateMaxByteSize(context: Context): Int {
        Logger.d(TAG, "calculateMaxByteSize")
        val displayMetrics = context.resources.displayMetrics
        val screenBytes = displayMetrics.widthPixels * displayMetrics.heightPixels * 4
        return screenBytes * 3
    }
}