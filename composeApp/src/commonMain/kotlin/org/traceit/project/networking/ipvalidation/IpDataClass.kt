package org.traceit.project.networking.ipvalidation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.traceit.project.networking.phonevalidation.Country
import org.traceit.project.networking.phonevalidation.Format

@Serializable
data class IpResponse(
    val status: String,
    val country: String ?= null,
    val countryCode: String ?= null,
    val region: String ?= null,
    val regionName: String ?= null,
    val city: String ?= null,
    val zip: String ?= null,
    val lat: Double ?= null,
    val lon: Double ?= null,
    val timezone: String ?= null,
    val isp: String ?= null,
    val org: String ?= null,
    @SerialName("as")
    val asName: String ?= null,
    val query: String,
    val message: String ?= null
)