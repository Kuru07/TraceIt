package org.traceit.project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.traceit.project.navigation.Screen
import org.traceit.project.navigation.SimpleNavController
import org.traceit.project.util.NetworkError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VirtualNumberOutput(
    modifier: Modifier = Modifier,
    navController: SimpleNavController,
    visible : Boolean,
    screen: Screen.VoipOutput
) {
    if (visible) {

        HandleBackPress {
            navController.navigateBack()
        }
        TraceItTheme {
            Surface(
                modifier = modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeContentPadding(),
                    topBar = {
                        TopAppBar(
                            title = {

                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.navigateBack()
                                }){
                                    Icon(
                                        painter = customBackIcon(),
                                        contentDescription = "Menu",
                                        tint = Color(0xFFFFD691)
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(start = 13.dp, top = 8.dp, end = 13.dp)
                            .verticalScroll(rememberScrollState())

                    ) {
                        Spacer(modifier = Modifier.padding(10.dp))
                        FilledOutputTextBox("NUMBER INFO")
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(
                            buildAnnotatedString {
                                append("Original Number: ")
                                withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                    append(screen.voipResponse?.phone ?: "Unknown")
                                }
                                append("\n")

                                append("Valid: ")
                                withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                    append("${screen.voipResponse?.valid}".replaceFirstChar { it.uppercase() })
                                }
                                append("\n")

                                append("Carrier: ")
                                withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                    append((screen.voipResponse?.carrier ?: "Unknown").replaceFirstChar { it.uppercase() })
                                }
                                append("\n")
                                if (screen.twilioResponse?.lineTypeIntelligence?.type == "nonFixedVoip" || screen.twilioResponse?.lineTypeIntelligence?.type == "fixedVoip") {
                                    append("Line Type: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(("Voip"))
                                    }
                                }
                                else
                                {
                                    append("Line Type: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append((screen.voipResponse?.type?: "Unknown").replaceFirstChar { it.uppercase() })
                                    }
                                }
                                append("\n")

                                append("Country Code: ")
                                withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                    append(screen.voipResponse?.country?.code ?: "Unknown")
                                }
                            },
                            fontSize = 24.sp,
                            lineHeight = 30.sp,
                            fontFamily = FontFamily.Serif                    )
                        Spacer(modifier = Modifier.padding(10.dp))
                        FilledOutputTextBox("NUMBER FORMAT")
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(
                            buildAnnotatedString {
                                append("Local: ")
                                withStyle(style = SpanStyle(color = Color(0xFFF9D68A))){
                                    append(screen.voipResponse?.format?.local ?: "Unknown")
                                }
                                append("\n")

                                append("International: ")
                                withStyle(style = SpanStyle(color = Color(0xFFF9D68A))){
                                    append(screen.voipResponse?.format?.international ?: "Unknown")
                                }
                            },
                            fontSize = 24.sp,
                            lineHeight = 30.sp,
                            fontFamily = FontFamily.Serif
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        if (screen.twilioResponse?.lineTypeIntelligence?.type != "nonFixedVoip" && screen.twilioResponse?.lineTypeIntelligence?.type != "fixedVoip") {
                            FilledOutputTextBox("LOCATION DETAILS")
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(
                                buildAnnotatedString {
                                    append("Country: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))){
                                        append(screen.voipResponse?.country?.name ?: "Unknown")
                                    }
                                    append("\n")

                                    append("Region: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))){
                                        append(screen.voipResponse?.location ?: "Unknown")
                                    }
                                },
                                fontSize = 24.sp,
                                lineHeight = 30.sp,
                                fontFamily = FontFamily.Serif
                            )
                        }
                        Spacer(modifier = Modifier.padding(20.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            Button(
                                onClick = {
                                },
                                shape = RoundedCornerShape(10.dp),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 10.dp,
                                    pressedElevation = 15.dp,
                                    hoveredElevation = 20.dp,
                                    focusedElevation = 25.dp
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFD691),
                                    contentColor = Color(0xFF233A66)
                                )
                            ){
                                Text(
                                    text = "SAVE REPORT  ",
                                    fontSize = 25.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                                Icon(painter = downloadIcon(), contentDescription = "Download")
                            }
                        }
                        Spacer(modifier = Modifier.padding(20.dp))
                        Row(
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(20.dp))
                                .background(Color(0xFF11264F)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Button(
                                onClick = {
                                    navController.navigateBack()
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFD691),
                                    contentColor = Color(0xFF233A66)
                                ),
                                modifier = Modifier.padding(12.dp)
                            ){
                                Text(
                                    text = "LOOKUP \nANOTHER",
                                    maxLines = 2,
                                    fontSize = 17.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                            Button(
                                onClick = {
                                    navController.jumpNavigate(Screen.DomainIpInput)
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFD691),
                                    contentColor = Color(0xFF233A66)
                                ),
                                modifier = Modifier.padding(12.dp)
                            ){
                                Text(
                                    text = "WHOIS \nLOOKUP",
                                    maxLines = 2,
                                    fontSize = 17.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DomainIpOutput(
    modifier: Modifier = Modifier,
    navController: SimpleNavController,
    visible : Boolean,
    screen: Screen.DomainIpOutput,
) {

    if (visible) {

        HandleBackPress {
            navController.navigateBack()
        }
        TraceItTheme {
            Surface(
                modifier = modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeContentPadding(),
                    topBar = {
                        TopAppBar(
                            title = {

                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.navigateBack()
                                }){
                                    Icon(
                                        painter = customBackIcon(),
                                        contentDescription = "Menu",
                                        tint = Color(0xFFFFD691)
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(start = 13.dp, top = 8.dp, end = 13.dp)
                            .verticalScroll(rememberScrollState())

                    ) {
                        Spacer(modifier = Modifier.padding(10.dp))
                        if(screen.isDomain){
                            FilledOutputTextBox("DOMAIN DETAILS")
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(
                                buildAnnotatedString {
                                        append("Domain Name: ")
                                        withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                            append(screen.whoIsResponse?.domainName ?: "Unknown")
                                        }
                                        append("\n")

                                    if (screen.whoIsResponse?.registryData!=null) {
                                        append("Creation Datee: ")
                                        withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                            append(screen.whoIsResponse.registryData.createDate ?: "Unknown")
                                        }
                                        append("\n")
                                        append("Update Date: ")
                                        withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                            append(screen.whoIsResponse.registryData.updateDate ?: "Unknown")
                                        }
                                        append("\n")
                                        append("Expiry Date: ")
                                        withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                            append(screen.whoIsResponse.registryData.expiryDate ?: "Unknown")
                                        }
                                    }
                                    else{
                                        if (screen.whoIsResponse?.registryData==null) {
                                            append("Creation Datee: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(screen.whoIsResponse?.createDate ?: "Unknown")
                                            }
                                            append("\n")
                                            append("Update Date: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(screen.whoIsResponse?.updateDate ?: "Unknown")
                                            }
                                            append("\n")
                                            append("Expiry Date: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(screen.whoIsResponse?.expiryDate ?: "Unknown")
                                            }
                                        }
                                    }
                                },
                                fontSize = 24.sp,
                                lineHeight = 30.sp,
                                fontFamily = FontFamily.Serif
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            FilledOutputTextBox("REGISTRAR DETAILS")
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(
                                buildAnnotatedString {
                                        if (screen.whoIsResponse?.registryData!=null) {
                                            append("Registrar Name: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(
                                                    screen.whoIsResponse.registryData.domainRegistrar?.registrarName
                                                        ?: "Unknown"
                                                )
                                            }
                                            append("\n")

                                            append("IANA Id: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(
                                                    screen.whoIsResponse.registryData.domainRegistrar?.ianaId
                                                        ?: "Unknown"
                                                )
                                            }
                                            append("\n")

                                            append("Website URL: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(
                                                    screen.whoIsResponse.registryData.domainRegistrar?.websiteUrl
                                                        ?: "Unknown"
                                                )
                                            }
                                            append("\n")

                                            append("Email Address: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(
                                                    screen.whoIsResponse.registryData.domainRegistrar?.emailAddress
                                                        ?: "Unknown"
                                                )
                                            }
                                            append("\n")

                                            append("Phone Number: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(
                                                    screen.whoIsResponse.registryData.domainRegistrar?.phoneNumber
                                                        ?: "Unknown"
                                                )
                                            }
                                        }
                                    else{
                                            append("Registrar Name: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(
                                                    screen.whoIsResponse?.domainRegistrar?.registrarName
                                                        ?: "Unknown"
                                                )
                                            }
                                            append("\n")

                                            append("IANA Id: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(
                                                    screen.whoIsResponse?.domainRegistrar?.ianaId
                                                        ?: "Unknown"
                                                )
                                            }
                                            append("\n")

                                            append("Website URL: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(
                                                    screen.whoIsResponse?.domainRegistrar?.websiteUrl
                                                        ?: "Unknown"
                                                )
                                            }
                                            append("\n")

                                            append("Email Address: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(
                                                    screen.whoIsResponse?.domainRegistrar?.emailAddress
                                                        ?: "Unknown"
                                                )
                                            }
                                            append("\n")

                                            append("Phone Number: ")
                                            withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                                append(
                                                    screen.whoIsResponse?.domainRegistrar?.phoneNumber
                                                        ?: "Unknown"
                                                )
                                            }
                                    }
                                },
                                fontSize = 24.sp,
                                lineHeight = 30.sp,
                                fontFamily = FontFamily.Serif
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            FilledOutputTextBox("NAME SERVERS")
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(
                                buildAnnotatedString {
                                    if(screen.whoIsResponse?.registryData!=null){
                                        append("Name Server: ")
                                        withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                            val formattedServers = screen.whoIsResponse.registryData.nameServers?.joinToString("\n")
                                            append(formattedServers ?: "Unknown")
                                        }
                                    }
                                    else
                                    {
                                        append("Name Server: ")
                                        withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                            val formattedServers = screen.whoIsResponse?.nameServers?.joinToString("\n")
                                            append(formattedServers ?: "Unknown")
                                        }
                                    }
                                },
                                fontSize = 24.sp,
                                lineHeight = 30.sp,
                                fontFamily = FontFamily.Serif
                            )
                        }
                        else
                        {
                            FilledOutputTextBox("IP DETAILS")
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(
                                buildAnnotatedString {
                                    append("IP Address: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(screen.ipResponse?.query ?: "Unknown")
                                    }
                                    append("\n")

                                    append("ISP Name: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(screen.ipResponse?.isp ?: "Unknown")
                                    }
                                    append("\n")

                                    append("Organization Name:: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(screen.ipResponse?.org ?: "Unknown")
                                    }
                                } ,
                                fontSize = 24.sp,
                                lineHeight = 30.sp,
                                fontFamily = FontFamily.Serif
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            FilledOutputTextBox("LOCATION DETAILS")
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(
                                buildAnnotatedString {
                                    append("Country Code: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(screen.ipResponse?.countryCode ?: "Unknown")
                                    }
                                    append("\n")

                                    append("Country: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(screen.ipResponse?.country ?: "Unknown")
                                    }
                                    append("\n")

                                    append("City: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(screen.ipResponse?.city ?: "Unknown")
                                    }
                                    append("\n")

                                    append("ZIP: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(screen.ipResponse?.zip ?: "Unknown")
                                    }
                                    append("\n")

                                    append("Latitude: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(
                                            (if (screen.ipResponse?.lat != null) {
                                                screen.ipResponse.lat.toString()
                                            } else {
                                                "Unknown"
                                            })
                                        )
                                    }
                                    append("\n")

                                    append("Longitude: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(
                                            (if (screen.ipResponse?.lon != null) {
                                                screen.ipResponse.lon.toString()
                                            } else {
                                                "Unknown"
                                            })
                                        )                                    }
                                    append("\n")

                                    append("Time Zone: ")
                                    withStyle(style = SpanStyle(color = Color(0xFFF9D68A))) {
                                        append(screen.ipResponse?.timezone ?: "Unknown")
                                    }
                                },
                                fontSize = 24.sp,
                                lineHeight = 30.sp,
                                fontFamily = FontFamily.Serif
                            )
                        }
                        Spacer(modifier = Modifier.padding(20.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            Button(
                                onClick = {
                                },
                                shape = RoundedCornerShape(10.dp),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 10.dp,
                                    pressedElevation = 15.dp,
                                    hoveredElevation = 20.dp,
                                    focusedElevation = 25.dp
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFD691),
                                    contentColor = Color(0xFF233A66)
                                )
                            ){
                                Text(
                                    text = "SAVE REPORT  ",
                                    fontSize = 25.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                                Icon(painter = downloadIcon(), contentDescription = "Download")
                            }
                        }
                        Spacer(modifier = Modifier.padding(20.dp))
                        Row(
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(20.dp))
                                .background(Color(0xFF11264F)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Button(
                                onClick = {
                                    navController.navigateBack()
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFD691),
                                    contentColor = Color(0xFF233A66)
                                ),
                                modifier = Modifier.padding(12.dp)
                            ){
                                Text(
                                    text = "LOOKUP \nANOTHER",
                                    maxLines = 2,
                                    fontSize = 17.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                            Button(
                                onClick = {
                                    navController.jumpNavigate(Screen.VoipInput)
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFD691),
                                    contentColor = Color(0xFF233A66)
                                ),
                                modifier = Modifier.padding(12.dp)
                            ){
                                Text(
                                    text = "VOIP \nLOOKUP",
                                    maxLines = 2,
                                    fontSize = 17.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}