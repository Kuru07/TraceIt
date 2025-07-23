package org.traceit.project.networking.ipvalidation

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.traceit.project.networking.ABSTRACT_API_KEY
import org.traceit.project.networking.phonevalidation.VoipResponse
import org.traceit.project.util.NetworkError
import org.traceit.project.util.Result

class IpClient (
    private val httpClient: HttpClient
) {
    suspend fun getIpValidation(ip: String): Result<IpResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "http://ip-api.com/json"
            ){
                url{
                    appendPathSegments(ip)
                }
            }
        } catch (e : UnresolvedAddressException){
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException){
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value){
            in 200..299 -> {
                println("Success! Status code: ${response.status.value}")
                val ipResponse : IpResponse = response.body()
                Result.Success(ipResponse)
            }
            400 -> Result.Error(NetworkError.BAD_REQUEST)
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            422 -> Result.Error(NetworkError.QUOTA_EXCEEDED)
            429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}