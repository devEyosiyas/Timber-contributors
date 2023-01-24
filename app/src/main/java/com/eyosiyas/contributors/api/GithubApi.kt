package com.eyosiyas.contributors.api

import com.eyosiyas.contributors.model.Contributor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    /**
     * "This function returns a list of contributors for a given repository."
     *
     * The @GET annotation tells Retrofit that this function will make a GET request to the specified
     * URL. The URL is specified using the @Path annotation, which tells Retrofit to replace the
     * {owner} and {repo} placeholders with the values of the owner and repo parameters
     *
     * @param owner The owner of the repository.
     * @param repo The name of the repository.
     */
    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun contributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<List<Contributor>>
}