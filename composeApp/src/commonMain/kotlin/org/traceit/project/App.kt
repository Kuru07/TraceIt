package org.traceit.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.traceit.project.navigation.Screen
import org.traceit.project.navigation.SimpleNavController
import org.traceit.project.networking.ipvalidation.IpClient
import org.traceit.project.networking.phonevalidation.PhoneClient
import org.traceit.project.networking.phonevalidation.TwilioClient
import org.traceit.project.networking.whoislookup.WhoIsClient
import org.traceit.project.ui.DomainIpInput
import org.traceit.project.ui.DomainIpOutput
import org.traceit.project.ui.Home
import org.traceit.project.ui.TraceItTheme
import org.traceit.project.ui.VirtualNumberInput
import org.traceit.project.ui.VirtualNumberOutput

@Composable
fun App(phoneclient: PhoneClient, ipClient: IpClient, whoIsClient: WhoIsClient,twilioClient: TwilioClient) {
    TraceItTheme {
        Surface (modifier = Modifier.fillMaxSize()){

            val navController = remember { SimpleNavController(Screen.Home) }
            val currentScreen by navController.currentScreen.collectAsState()

            Home(
                visible = currentScreen == Screen.Home ,
                navController = navController
            )
            DomainIpInput(
                visible = currentScreen is Screen.DomainIpInput ,
                navController = navController,
                IPclient  = ipClient,
                whoIsClient = whoIsClient,
            )
            DomainIpOutput(
                visible = currentScreen is Screen.DomainIpOutput,
                navController = navController,
                screen = (currentScreen as? Screen.DomainIpOutput) ?: Screen.DomainIpOutput(isDomain = true,null,null),
            )
            VirtualNumberInput(
                visible = currentScreen == Screen.VoipInput ,
                navController = navController,
                client = phoneclient,
                twilioClient = twilioClient
            )
            VirtualNumberOutput(
                visible = currentScreen is Screen.VoipOutput ,
                navController = navController,
                screen = (currentScreen as? Screen.VoipOutput) ?: Screen.VoipOutput(null)

            )
        }
    }
}