package com.eyosiyas.contributors.api

import com.eyosiyas.contributors.model.ContributorResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun contributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<ContributorResponse>
}