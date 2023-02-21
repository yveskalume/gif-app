package com.yvkalume.gifapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yvkalume.gifapp.R

private val fonts = FontFamily(
		Font(R.font.roboto_regular),
		Font(resId = R.font.roboto_light, weight = FontWeight.Light),
		Font(resId = R.font.roboto_medium, weight = FontWeight.Medium),
		Font(resId = R.font.roboto_regular, weight = FontWeight.Normal),
		Font(resId = R.font.roboto_thin, weight = FontWeight.Thin),
		Font(resId = R.font.roboto_bold, weight = FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
		body1 = TextStyle(
				fontFamily = fonts,
				fontWeight = FontWeight.Normal,
				fontSize = 16.sp,
		),

		h1 = TextStyle(
				fontFamily = fonts,
				fontWeight = FontWeight.Normal,
				fontSize = 24.sp,
		),

		h2 = TextStyle(
				fontFamily = fonts,
				fontWeight = FontWeight.Normal,
				fontSize = 20.sp,
		),

		h3 = TextStyle(
				fontFamily = fonts,
				fontWeight = FontWeight.Normal,
				fontSize = 18.sp,
		),

		h4 = TextStyle(
				fontFamily = fonts,
				fontWeight = FontWeight.Normal,
				fontSize = 16.sp,
		),

		button = TextStyle(
				fontFamily = fonts,
				fontWeight = FontWeight.W500,
				fontSize = 14.sp,
		),

		caption = TextStyle(
				fontFamily = fonts,
				fontWeight = FontWeight.Normal,
				fontSize = 12.sp,
		)
)