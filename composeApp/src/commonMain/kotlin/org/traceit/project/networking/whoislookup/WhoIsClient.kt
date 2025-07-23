package org.traceit.project.networking.whoislookup

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.traceit.project.networking.ABSTRACT_API_KEY
import org.traceit.project.networking.WHOIS_API_KEY
import org.traceit.project.networking.phonevalidation.VoipResponse
import org.traceit.project.util.NetworkError
import org.traceit.project.util.Result

class WhoIsClient  (
    private val httpClient: HttpClient
) {
    suspend fun getWhoIsValidation(urlLink: String): Result<WhoIsResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://api.whoisfreaks.com/v1.0/whois"
            ){
                parameter("apiKey", WHOIS_API_KEY)
                parameter("whois", "live")
                parameter("domainName", urlLink)

            }
        } catch (e : UnresolvedAddressException){
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException){
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value){
            in 200..299 -> {
                println("Success! Status code: ${response.status.value}")
                val whoIsResponse : WhoIsResponse = response.body()
                Result.Success(whoIsResponse)
            }
            400 -> Result.Error(NetworkError.BAD_REQUEST)
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            403 -> Result.Error(NetworkError.UNAVAILABLE_DOMAIN_EXTENSION)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            422 -> Result.Error(NetworkError.QUOTA_EXCEEDED)
            429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}