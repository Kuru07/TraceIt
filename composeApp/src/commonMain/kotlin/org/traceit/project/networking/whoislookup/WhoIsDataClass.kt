package org.traceit.project.networking.whoislookup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WhoIsResponse(
    val status: Boolean,
    @SerialName("domain_name") val domainName: String? = null,
    @SerialName("query_time") val queryTime: String? = null,
    @SerialName("whois_server") val whoisServer: String? = null,
    @SerialName("domain_registered") val domainRegistered: String? = null,
    @SerialName("name_servers") val nameServers: List<String>? = null,
    @SerialName("whois_raw_domain") val whoisRawDomain: String? = null,
    @SerialName("registry_data") val registryData: RegistryData? = null,
    @SerialName("create_date") val createDate: String? = null,
    @SerialName("update_date") val updateDate: String?  = null,
    @SerialName("expiry_date") val expiryDate: String? = null,
    @SerialName("domain_registrar") val domainRegistrar: DomainRegistrar? = null
)

@Serializable
data class RegistryData(
    @SerialName("domain_name") val domainName: String? = null,
    @SerialName("query_time") val queryTime: String? = null,
    @SerialName("whois_server") val whoisServer: String? = null,
    @SerialName("domain_registered") val domainRegistered: String? = null,
    @SerialName("create_date") val createDate: String? = null,
    @SerialName("update_date") val updateDate: String? = null,
    @SerialName("expiry_date") val expiryDate: String? = null,
    @SerialName("domain_registrar") val domainRegistrar: DomainRegistrar? = null,
    @SerialName("name_servers") val nameServers: List<String>? = null,
    @SerialName("domain_status") val domainStatus: List<String>? = null,
    @SerialName("whois_raw_registery") val whoisRawRegistry: String? = null
)

@Serializable
data class DomainRegistrar(
    @SerialName("iana_id") val ianaId: String? = null,
    @SerialName("registrar_name") val registrarName: String? = null,
    @SerialName("whois_server") val whoisServer: String? = null,
    @SerialName("website_url") val websiteUrl: String? = null,
    @SerialName("email_address") val emailAddress: String? = null,
    @SerialName("phone_number") val phoneNumber: String? = null
)