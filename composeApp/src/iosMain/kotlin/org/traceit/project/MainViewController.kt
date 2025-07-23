package org.traceit.project

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.engine.darwin.Darwin
import org.traceit.project.networking.phonevalidation.PhoneClient
import org.traceit.project.networking.createHttpClient
import org.traceit.project.networking.createTwilioClient
import org.traceit.project.networking.ipvalidation.IpClient
import org.traceit.project.networking.phonevalidation.TwilioClient
import org.traceit.project.networking.whoislookup.WhoIsClient

fun MainViewController() = ComposeUIViewController {
    App(
        phoneclient = remember {
            PhoneClient(createHttpClient(Darwin.create()))
        },
        ipClient = remember {
            IpClient(createHttpClient(Darwin.create()))
        },
        whoIsClient = remember {
            WhoIsClient(createHttpClient(Darwin.create()))
        },
        twilioClient = remember {
            TwilioClient(createTwilioClient(Darwin.create()))
        }
    )
}