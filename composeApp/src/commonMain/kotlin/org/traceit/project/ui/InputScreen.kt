package org.traceit.project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VirtualNumberInput(modifier: Modifier = Modifier) {

    var numberInput by remember { mutableStateOf("") }

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
                    FilledTextBox("Enter a Virtual Number")
                    Spacer(modifier = Modifier.padding(20.dp))
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                        contentAlignment = Alignment.Center){
                        Image(painter = voipLookupIcon(), contentDescription = "Voip Lookup")

                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                    , verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                        OutlinedTextField(
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            value = numberInput,
                            onValueChange = { numberInput = it },
                            label = { },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White
                            ),
                            prefix = {
                                Text("+"
                                , color = Color.White
                                )
                            },
                            maxLines = 1
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
                                text = "TRACE NOW  ",
                                fontSize = 22.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Icon(painter = customSearchIcon(), contentDescription = "Search")
                        }
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Row(
                        modifier = Modifier
                            .padding(start = 30.dp,end=30.dp)
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
                                text = "REPORT LOGS",
                                fontSize = 22.sp,
                                fontFamily = FontFamily.Monospace
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
fun DomainIpInput(modifier: Modifier = Modifier) {
    var numbertext by remember { mutableStateOf("") }
    val radioOptions = listOf("Domain", "IP Address")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    var isDomain by remember { mutableStateOf(true) }

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
                    FilledTextBox("Enter a Domain or IP")
                    Spacer(modifier = Modifier.padding(20.dp))
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                        contentAlignment = Alignment.Center){
                        Image(painter = domainLookupIcon(), contentDescription = "Domain Lookup")

                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        , verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                        OutlinedTextField(
                            keyboardOptions = KeyboardOptions(
                                keyboardType = if(isDomain){
                                    KeyboardType.Text
                                }
                                else{
                                    KeyboardType.Number
                                }
                            ),
                            value = numbertext,
                            onValueChange = { numbertext = it },
                            label = { },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White
                            ),
                            singleLine = true,
                            textStyle = TextStyle(
                                fontFamily = FontFamily.Serif,
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row{
                        Row(modifier.selectableGroup()) {
                            radioOptions.forEach { text ->
                                Row(
                                    Modifier
                                        .wrapContentSize()
                                        .height(56.dp)
                                        .selectable(
                                            selected = (text == selectedOption),
                                            onClick = { onOptionSelected(text) },
                                            role = Role.RadioButton
                                        )
                                        .padding(horizontal = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = (text == selectedOption),
                                        onClick = {
                                            onOptionSelected(text)
                                            if(text == "Domain"){
                                                isDomain = true
                                            }
                                            else{
                                                isDomain = false
                                            }

                                        },
                                        enabled = true,
                                        colors = RadioButtonColors(
                                            selectedColor = Color(0xFFFFD691),
                                            unselectedColor = Color(0xFFFFD691),
                                            disabledSelectedColor = Color.White,
                                            disabledUnselectedColor = Color.White
                                        )
                                    )
                                    Text(
                                        text = text,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontFamily = FontFamily.Serif
                                    )
                                }
                            }
                        }
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
                                text = "TRACE NOW  ",
                                fontSize = 22.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Icon(painter = customSearchIcon(), contentDescription = "Search")
                        }
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Row(
                        modifier = Modifier
                            .padding(start = 30.dp,end=30.dp)
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
                                text = "REPORT LOGS",
                                fontSize = 22.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }
        }
    }
}
