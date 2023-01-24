package com.eyosiyas.contributors.model


import com.squareup.moshi.Json

/**
 * Kotlin data classes are a great way to model JSON data.
 * @property {String} avatar - The URL to the contributor's avatar.
 * @property {String} login - The username of the contributor.
 */
data class Contributor(
    @Json(name = "avatar_url")
    val avatar: String,
    @Json(name = "login")
    val login: String
)