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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.traceit.project.navigation.Screen
import org.traceit.project.navigation.SimpleNavController
import org.traceit.project.networking.ipvalidation.IpClient
import org.traceit.project.networking.ipvalidation.IpResponse
import org.traceit.project.networking.phonevalidation.PhoneClient
import org.traceit.project.networking.phonevalidation.TwilioClient
import org.traceit.project.networking.phonevalidation.TwilioResponse
import org.traceit.project.networking.phonevalidation.VoipResponse
import org.traceit.project.networking.whoislookup.WhoIsClient
import org.traceit.project.networking.whoislookup.WhoIsResponse
import org.traceit.project.util.NetworkError
import org.traceit.project.util.onError
import org.traceit.project.util.onSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VirtualNumberInput(
    modifier: Modifier = Modifier,
    navController: SimpleNavController,
    visible : Boolean,
    client: PhoneClient,
    twilioClient: TwilioClient
) {

    var numberInput by remember { mutableStateOf("") }
    if (visible) {
        var isLoading by remember {
            mutableStateOf(false)
        }

        var errorMessage by remember {
            mutableStateOf< NetworkError?>(null)
        }

        var notValidPhone by remember {
            mutableStateOf("")
        }

        val scope = rememberCoroutineScope()

        val scope2 = rememberCoroutineScope()

        var voipResponse by remember {
            mutableStateOf<VoipResponse?>(null)
        }
        var twilioResponse by remember {
            mutableStateOf<TwilioResponse?>(null)
        }
        HandleBackPress {
            navController.navigateBack()
            errorMessage=null
            notValidPhone=""
            numberInput=""
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
                                    errorMessage=null
                                    notValidPhone=""
                                    numberInput=""
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
                                onClick = {
                                    val number = numberInput.filter {
                                        it.isDigit()
                                    }
                                    try {
                                        if (isValidPhoneNumber(number)) {
                                            scope.launch {
                                                notValidPhone = ""
                                                isLoading = true
                                                errorMessage = null

                                                client.getPhoneValidation(number)
                                                    .onSuccess { currentVoipResponse ->
                                                        voipResponse = currentVoipResponse
                                                        //navController.navigateTo(Screen.VoipOutput(voipResponse))
                                                        scope2.launch {
                                                            twilioClient.getPhoneValidation("+$number")
                                                                .onSuccess {
                                                                    twilioResponse = it
                                                                    navController.navigateTo(
                                                                        Screen.VoipOutput(
                                                                            voipResponse,
                                                                            twilioResponse
                                                                        )
                                                                    )
                                                                    errorMessage = null
                                                                    notValidPhone = ""
                                                                    numberInput = ""
                                                                    isLoading = false
                                                                }
                                                                .onError {
                                                                    errorMessage = it
                                                                    isLoading = false
                                                                }
                                                        }
                                                    }
                                                    .onError {
                                                        errorMessage = it
                                                        isLoading = false
                                                    }
                                            }
                                        } else {
                                            println("VirtualNumberInput: Invalid or empty input received: '$numberInput'")
                                            notValidPhone = "Enter a Valid Number"
                                        }
                                    }
                                    catch (e: Exception){
                                        notValidPhone=e.message.toString()
                                    }

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
                                    text = "TRACE NOW  ",
                                    fontSize = 22.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                                Icon(painter = customSearchIcon(), contentDescription = "Search")
                            }
                        }

                        Spacer(modifier = Modifier.padding(10.dp))
                        if(isLoading){
                            Row(modifier = Modifier
                                .safeContentPadding()
                                .fillMaxWidth(),
                                Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                                Spacer(modifier = Modifier.padding(10.dp))
                                Text("Please Wait...",
                                    color = Color.White,
                                    fontSize = 17.sp,
                                    fontFamily = FontFamily.Monospace,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        if(notValidPhone.isNotEmpty()){
                            Text(
                                text = notValidPhone,
                                color = Color.Red,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                                )
                        }

                        errorMessage?.let {
                            Text(
                                text = it.name,
                                color = Color.Red,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DomainIpInput(
    modifier: Modifier = Modifier,
    navController: SimpleNavController,
    visible : Boolean,
    IPclient: IpClient,
    whoIsClient: WhoIsClient
) {
    var domainIpText by remember { mutableStateOf("") }
    val radioOptions = listOf("Domain", "IP Address")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    var isDomain by remember { mutableStateOf( true ) }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf< NetworkError?>(null)
    }

    val scope = rememberCoroutineScope()

    var ipResponse by remember {
        mutableStateOf<IpResponse?>(null)
    }

    var whoIsResponse by remember {
        mutableStateOf<WhoIsResponse?>(null)
    }

    var privateIp  by remember {
        mutableStateOf("")
    }
    if (visible) {
        HandleBackPress {
            navController.navigateBack()
            errorMessage=null
            domainIpText=""
            privateIp=""
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
                                    errorMessage=null
                                    domainIpText=""
                                    privateIp=""
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
                                value = domainIpText,
                                onValueChange = { domainIpText = it },
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
                                                onClick = {
                                                    onOptionSelected(text)
                                                    isDomain = text == "Domain"
                                                    privateIp=""
                                                    domainIpText=""
                                                },
                                                role = Role.RadioButton
                                            )
                                            .padding(horizontal = 5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = (text == selectedOption),
                                            onClick = {
                                                onOptionSelected(text)
                                                isDomain = text == "Domain"
                                                privateIp=""
                                                domainIpText=""
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
                                onClick = {
                                    var ipAddress = domainIpText
                                    try {
                                        if(!isDomain){
                                            scope.launch{
                                                isLoading =  true
                                                errorMessage = null

                                                IPclient.getIpValidation(ipAddress)
                                                    .onSuccess {
                                                        ipResponse = it
                                                        if(ipResponse?.status != "fail"){
                                                            navController.navigateTo(Screen.DomainIpOutput( isDomain = isDomain ,ipResponse))
                                                            privateIp=""
                                                            errorMessage=null
                                                            domainIpText=""
                                                        }
                                                        else
                                                            privateIp="Its a private Ip Address"
                                                        domainIpText=""
                                                        isLoading=false
                                                    }
                                                    .onError {
                                                        errorMessage = it
                                                        isLoading = false
                                                    }
                                            }
                                        }
                                        else
                                        {
                                            println("IpAddressInput: Invalid or empty input received: '$domainIpText'")
                                        }
                                        var domainText = domainIpText
                                        if(isDomain){
                                            scope.launch{
                                                isLoading =  true
                                                errorMessage = null

                                                whoIsClient.getWhoIsValidation(domainText)
                                                    .onSuccess {
                                                        whoIsResponse = it
                                                        if(whoIsResponse?.status == true){
                                                            navController.navigateTo(Screen.DomainIpOutput( isDomain = isDomain , whoIsResponse = whoIsResponse))
                                                            privateIp=""
                                                            errorMessage=null
                                                            domainIpText=""
                                                        }
                                                        else
                                                            privateIp="Enter the domain correctly"
                                                        domainIpText=""
                                                        isLoading=false
                                                    }
                                                    .onError {
                                                        errorMessage = it
                                                        isLoading = false
                                                    }
                                            }
                                        }
                                        else
                                        {
                                            println("DomainInput: Invalid or empty input received: '$domainIpText'")

                                        }
                                    } catch (e: Exception) {
                                        errorMessage=NetworkError.REQUEST_TIMEOUT
                                    }
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
                                    text = "TRACE NOW  ",
                                    fontSize = 22.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                                Icon(painter = customSearchIcon(), contentDescription = "Search")
                            }
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        if(isLoading){
                            Row(modifier = Modifier
                                .safeContentPadding()
                                .fillMaxWidth(),
                                Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                                Spacer(modifier = Modifier.padding(10.dp))
                                Text("Please Wait...",
                                    color = Color.White,
                                    fontSize = 17.sp,
                                    fontFamily = FontFamily.Monospace,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                        if(privateIp.isNotEmpty()){
                            Text(
                                text = privateIp,
                                color = Color.Red,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        errorMessage?.let {
                            Text(
                                text = it.name,
                                color = Color.Red,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
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
}


fun isValidPhoneNumber(input: String): Boolean {
    val trimmed = input.trim()
    return trimmed.length in 8..15 && trimmed.all { it.isDigit() }
}