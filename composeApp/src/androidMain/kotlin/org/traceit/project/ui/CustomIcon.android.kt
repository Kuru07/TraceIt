package org.traceit.project.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.traceit.project.R

@Composable
actual fun customMenuIcon(): Painter {
    return painterResource(id = R.drawable.menu_icon)
}

@Composable
actual fun customBackIcon(): Painter {
    return painterResource(id = R.drawable.back_icon)
}

@Composable
actual fun voipLookupIcon(): Painter {
    return painterResource(id = R.drawable.voip_lookup)
}

@Composable
actual fun customSearchIcon(): Painter {
    return painterResource(id = R.drawable.search_icon)
}

@Composable
actual fun domainLookupIcon(): Painter {
    return painterResource(id = R.drawable.domain_lookup)
}

@Composable
actual fun downloadIcon(): Painter {
    return painterResource(id = R.drawable.download_icon)
}

@Composable
actual fun forwardIcon(): Painter {
    return painterResource(id = R.drawable.forward_icon)
}

@Composable
actual fun font(
    name: String,
    res: String,
    weight: FontWeight,
    style: FontStyle
): Font {
    val context = LocalContext.current
    val id = context.resources.getIdentifier(res, "font", context.packageName)
    require(id != 0) { "Font resource not found: $res" }
    return Font(id, weight, style)
}