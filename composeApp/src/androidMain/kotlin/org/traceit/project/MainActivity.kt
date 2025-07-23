package org.traceit.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import io.ktor.client.engine.okhttp.OkHttp
import org.traceit.project.networking.phonevalidation.PhoneClient
import org.traceit.project.networking.createHttpClient
import org.traceit.project.networking.createTwilioClient
import org.traceit.project.networking.ipvalidation.IpClient
import org.traceit.project.networking.phonevalidation.TwilioClient
import org.traceit.project.networking.whoislookup.WhoIsClient
import org.traceit.project.ui.TraceItTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
                TraceItTheme {
                    App(
                        phoneclient = remember {
                            PhoneClient(createHttpClient(OkHttp.create()))
                        },
                        ipClient = remember {
                           IpClient(createHttpClient(OkHttp.create()))
                        },
                        whoIsClient = remember {
                            WhoIsClient(createHttpClient(OkHttp.create()))
                        },
                        twilioClient = remember {
                            TwilioClient(createTwilioClient(OkHttp.create()))
                        }
                    )
                }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppAndroidPreview() {
}