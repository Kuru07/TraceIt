package org.traceit.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
expect fun customMenuIcon(): Painter

@Composable
expect fun customBackIcon(): Painter

@Composable
expect fun customSearchIcon(): Painter

@Composable
expect fun domainLookupIcon(): Painter

@Composable
expect fun voipLookupIcon():Painter

@Composable
expect fun downloadIcon():Painter

@Composable
expect fun forwardIcon(): Painter

@Composable
expect fun font(name: String, res: String, weight: FontWeight, style: FontStyle): Font

@Composable
expect fun HandleBackPress( onBack: () -> Unit)


@Composable
fun FilledTextBox(text: String,modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFD691), shape = RoundedCornerShape(15.dp))
            .padding(start = 8.dp, top = 10.dp, bottom = 10.dp,end=0.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF233A66),
            fontFamily = FontFamily.Monospace,
            fontSize = 20.sp,
        )
    }

}

@Composable
fun FilledOutputTextBox(text: String,modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFD691), shape = RoundedCornerShape(15.dp))
            .padding(10.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF233A66),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterStart),
            fontFamily = FontFamily.Monospace,
            fontSize = 25.sp
        )
    }

}