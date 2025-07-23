package org.traceit.project.networking.phonevalidation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoipResponse(
    val phone: String,
    val valid: Boolean,
    val format: Format,
    val country: Country,
    val location: String,
    val type: String,
    val carrier: String
)


@Serializable
data class Format(
    val international: String,
    val local: String
)


@Serializable
data class Country(
    val code: String,
    val name: String,
    val prefix: String
)

@Serializable
data class TwilioResponse(
    @SerialName("calling_country_code") val callingCountryCode: String?,
    @SerialName("country_code") val countryCode: String?,
    @SerialName("phone_number") val phoneNumber: String?,
    @SerialName("national_format") val nationalFormat: String?,
    @SerialName("valid") val valid: Boolean?,
    @SerialName("validation_errors") val validationErrors: List<String>?,
    @SerialName("caller_name") val callerName: String?,
    @SerialName("sim_swap") val simSwap: String?,
    @SerialName("call_forwarding") val callForwarding: String?,
    @SerialName("line_status") val lineStatus: String?,
    @SerialName("line_type_intelligence") val lineTypeIntelligence: LineTypeIntelligence?,
    @SerialName("identity_match") val identityMatch: String?,
    @SerialName("reassigned_number") val reassignedNumber: String?,
    @SerialName("sms_pumping_risk") val smsPumpingRisk: String?,
    @SerialName("phone_number_quality_score") val phoneNumberQualityScore: String?,
    @SerialName("pre_fill") val preFill: String?,
    @SerialName("url") val url: String?
)

@Serializable
data class LineTypeIntelligence(
    @SerialName("error_code") val errorCode: String?,
    @SerialName("mobile_country_code") val mobileCountryCode: String?,
    @SerialName("mobile_network_code") val mobileNetworkCode: String?,
    @SerialName("carrier_name") val carrierName: String?,
    @SerialName("type") val type: String?
)