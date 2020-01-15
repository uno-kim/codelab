/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.unokim.codelab.paging.data

import androidx.paging.LivePagedListBuilder
import com.unokim.codelab.paging.api.GithubService
import com.unokim.codelab.paging.db.GithubLocalCache
import com.unokim.codelab.paging.model.RepoSearchResult
import timber.log.Timber

/**
 * Repository class that works with local and remote data sources.
 */
class GithubRepository(
    private val service: GithubService,
    private val cache: GithubLocalCache
) {
    /**
     * Search repositories whose names match the query.
     */
    fun search(query: String): RepoSearchResult {
        Timber.i("search: $query")

        // Get data source factory from the local cache
        val dataSourceFactory = cache.reposByName(query)

        // Construct the boundary callback
        val boundaryCallback = RepoBoundaryCallback(query, service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        // Get the network errors exposed by the boundary callback
        return RepoSearchResult(data, networkErrors)
    }

//    fun requestMore(query: String) {
//        requestAndSaveData(query)
//    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}
