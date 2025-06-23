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
fun FilledTextBox(text: String,modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFD691), shape = RoundedCornerShape(15.dp))
            .padding(16.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF233A66),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.Center),
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            fontSize = 32.sp
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
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            fontSize = 28.sp
        )
    }

}