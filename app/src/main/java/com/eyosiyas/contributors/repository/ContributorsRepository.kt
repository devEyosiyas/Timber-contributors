package com.eyosiyas.contributors.repository

import com.eyosiyas.contributors.api.GithubApi
import com.eyosiyas.contributors.model.Contributor
import com.eyosiyas.contributors.util.Result
import javax.inject.Inject

class ContributorsRepository @Inject constructor(private val api: GithubApi) {
    suspend fun fetchContributors(): Result<List<Contributor>> {
        return try {
            val response = api.contributors("jakewharton", "timber")
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception("Error fetching contributors data"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}