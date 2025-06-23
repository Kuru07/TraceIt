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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VirtualNumberOutput(modifier: Modifier = Modifier) {

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
                            IconButton(onClick = {}){
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
                        text = "Original Number: \nValid: \nCarrier: \nLine Type: \nCountry Code:",
                        fontSize = 24.sp,
                        lineHeight = 30.sp
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    FilledOutputTextBox("NUMBER FORMAT")
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = "Local: \nInternational:",
                        fontSize = 24.sp,
                        lineHeight = 30.sp
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    FilledOutputTextBox("LOCATION DETAILS")
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = "Country: \nRegion:",
                        fontSize = 24.sp,
                        lineHeight = 30.sp
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    FilledOutputTextBox("RISK SCORE:")
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = "Fraud Score:\nEmoji:",
                        fontSize = 24.sp,
                        lineHeight = 30.sp
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Button(
                            onClick = {},
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
                                fontSize = 25.sp
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
                            onClick = {},
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFD691),
                                contentColor = Color(0xFF233A66)
                            ),
                            modifier = Modifier.padding(15.dp)
                        ){
                            Text(
                                text = "LOOKUP \nANOTHER",
                                maxLines = 2,
                                fontSize = 20.sp
                            )
                        }
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFD691),
                                contentColor = Color(0xFF233A66)
                            ),
                            modifier = Modifier.padding(15.dp)
                        ){
                            Text(
                                text = "WHOIS \nLOOKUP",
                                maxLines = 2,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DomainIpOutput(isDomain: Boolean = true, modifier: Modifier = Modifier) {

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
                            IconButton(onClick = {}){
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
                    if(isDomain){
                        FilledOutputTextBox("DOMAIN DETAILS")
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(
                            text = "Domain Name: \nCreation Date: \nUpdate Date: \nExpiry Date:",
                            fontSize = 24.sp,
                            lineHeight = 30.sp
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        FilledOutputTextBox("REGISTRAR DETAILS")
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(
                            text = "Registrar Name: \nIANA Id: \nWebsite URL: \nEmail Address: \nPhone Number:",
                            fontSize = 24.sp,
                            lineHeight = 30.sp
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        FilledOutputTextBox("NAME SERVERS")
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(
                            text = "1) \n2)",
                            fontSize = 24.sp,
                            lineHeight = 30.sp
                        )
                    }
                    else
                    {
                        FilledOutputTextBox("IP DETAILS")
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(
                            text = "IP Address: \nISP Name: \nOrganization Name: ",
                            fontSize = 24.sp,
                            lineHeight = 30.sp
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        FilledOutputTextBox("LOCATION DETAILS")
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(
                            text = "Country Code: \nCountry: \nRegion: \nCity: \nZIP: \nLatitude: \nLongitude: \nTime Zone:",
                            fontSize = 24.sp,
                            lineHeight = 30.sp
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
                            onClick = {},
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
                                fontSize = 25.sp
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
                            onClick = {},
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFD691),
                                contentColor = Color(0xFF233A66)
                            ),
                            modifier = Modifier.padding(15.dp)
                        ){
                            Text(
                                text = "LOOKUP \nANOTHER",
                                maxLines = 2,
                                fontSize = 20.sp
                            )
                        }
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFD691),
                                contentColor = Color(0xFF233A66)
                            ),
                            modifier = Modifier.padding(15.dp)
                        ){
                            Text(
                                text = "VOIP \nLOOKUP",
                                maxLines = 2,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}