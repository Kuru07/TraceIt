package org.traceit.project.navigation

import org.traceit.project.networking.ipvalidation.IpResponse
import org.traceit.project.networking.phonevalidation.TwilioResponse
import org.traceit.project.networking.phonevalidation.VoipResponse
import org.traceit.project.networking.whoislookup.WhoIsResponse

sealed interface Screen {
    data object Home: Screen
    data object DomainIpInput: Screen
    data object VoipInput: Screen
    data class DomainIpOutput(
        val isDomain:Boolean,
        val ipResponse: IpResponse ?= null,
        val whoIsResponse: WhoIsResponse?= null
    ): Screen
    data class VoipOutput(
        val voipResponse: VoipResponse? = null,
        val twilioResponse: TwilioResponse?=null
    ): Screen
}