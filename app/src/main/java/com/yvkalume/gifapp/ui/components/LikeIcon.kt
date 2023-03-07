package com.yvkalume.gifapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yvkalume.gifapp.R

@Composable
fun LikeIcon(
		modifier: Modifier = Modifier,
		isChecked: Boolean,
		onClick: () -> Unit
) {
		val composition by rememberLottieComposition(
				LottieCompositionSpec.RawRes(R.raw.favorite_icon)
		)
		val progress by animateFloatAsState(
				targetValue = if (isChecked) 10f else 0f,
				animationSpec = tween(3500)
		)

		IconButton(onClick = onClick) {
				LottieAnimation(
						composition = composition,
						progress = { if (isChecked) progress else 0f },
						modifier = modifier
				)
		}


}