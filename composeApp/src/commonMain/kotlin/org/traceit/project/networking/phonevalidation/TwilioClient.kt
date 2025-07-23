package org.traceit.project.networking.phonevalidation

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.traceit.project.networking.ABSTRACT_API_KEY
import org.traceit.project.util.NetworkError
import org.traceit.project.util.Result

class TwilioClient  (
    private val httpClient: HttpClient
) {
    suspend fun getPhoneValidation(phone: String): Result<TwilioResponse, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://lookups.twilio.com/v2/PhoneNumbers/${phone}?Fields=line_type_intelligence"
            ){

            }
        } catch (e : UnresolvedAddressException){
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException){
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value){
            in 200..299 -> {
                println("Success! Status code: ${response.status.value}")
                val twilioResponse : TwilioResponse = response.body()
                Result.Success(twilioResponse)
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