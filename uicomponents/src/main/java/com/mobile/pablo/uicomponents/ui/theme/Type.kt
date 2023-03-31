package com.mobile.pablo.uicomponents.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mobile.pablo.uicomponents.R

val SF_PRO = FontFamily(
    listOf(
        Font(R.font.sf_pro_regular, FontWeight.Normal),
        Font(R.font.sf_pro_medium, FontWeight.Medium),
        Font(R.font.sf_pro_black, FontWeight.Black),
        Font(R.font.sf_pro_bold, FontWeight.Bold),
        Font(R.font.sf_pro_ultra_thin, FontWeight.ExtraLight),
        Font(R.font.sf_pro_thin, FontWeight.Thin),
        Font(R.font.sf_pro_light, FontWeight.Light),
        Font(R.font.sf_pro_semi_bold, FontWeight.SemiBold),
    )
)
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = SF_PRO,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)