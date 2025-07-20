package org.traceit.project.navigation

sealed interface Screen {
    data object Home: Screen
    data object DomainIpInput: Screen
    data object VoipInput: Screen
    data class DomainIpOutput(
        val isDomain:Boolean
    ): Screen
    data object VoipOutput: Screen
}