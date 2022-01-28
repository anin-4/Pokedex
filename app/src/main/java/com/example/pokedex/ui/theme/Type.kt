package com.example.pokedex.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pokedex.R


val PTSans = FontFamily(
    Font(R.font.ptsans_regular,FontWeight.Normal),
    Font(R.font.ptsans_bold, FontWeight.Bold),
)



val Typography = Typography(
    body1 = TextStyle(
        fontFamily = PTSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = PTSans,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )

)