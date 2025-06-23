package org.traceit.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.traceit.project.ui.CardLayoutButton
import org.traceit.project.ui.DomainIpInput
import org.traceit.project.ui.DomainIpOutput
import org.traceit.project.ui.FilledTextBox
import org.traceit.project.ui.Home
import org.traceit.project.ui.TraceItTheme
import org.traceit.project.ui.VirtualNumberInput
import org.traceit.project.ui.VirtualNumberOutput
import org.traceit.project.ui.customMenuIcon
import org.traceit.project.ui.voipLookupIcon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
                TraceItTheme {
                    Home()
                }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppAndroidPreview() {
   Home()
}