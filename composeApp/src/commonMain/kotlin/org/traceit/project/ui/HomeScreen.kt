package org.traceit.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key.Companion.W
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(modifier: Modifier = Modifier) {
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
                                    painter = customMenuIcon(),
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
                    ,verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CardLayoutButton(
                        "VOIP LOOKUP", voipLookupIcon(),
                        onClick = {  }
                    )
                    CardLayoutButton(
                        "WHOIS LOOKUP",
                        domainLookupIcon(),
                        onClick = {}
                    )

                }
            }
        }
    }
}

@Composable
fun CardLayoutButton(
    text: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit)
{
    Box(
        modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .background(Color(0xFFFFD691), shape = RoundedCornerShape(15.dp))
        .clickable{ onClick() }
    ){
        Column(
            modifier =  Modifier
            .fillMaxWidth(),
           ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF233A66),
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier
                    .padding(15.dp)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Icon(
                painter = painter,
                contentDescription = "Primary Icon",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp),
                tint = Color.Unspecified
            )
            Icon(painter = forwardIcon(),
                contentDescription = "Navigation",
                modifier = Modifier
                    .align(Alignment.End)
                    .size(60.dp)
                    .padding(10.dp),
                tint = Color.Unspecified
            )
        }

    }
}