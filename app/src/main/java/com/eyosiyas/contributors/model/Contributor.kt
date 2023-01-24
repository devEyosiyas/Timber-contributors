package com.eyosiyas.contributors.model


import com.squareup.moshi.Json

data class Contributor(
    @Json(name = "avatar_url")
    val avatar: String,
    @Json(name = "login")
    val login: String
)