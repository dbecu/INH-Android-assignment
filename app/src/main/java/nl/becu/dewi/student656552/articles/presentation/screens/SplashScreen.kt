package nl.becu.dewi.student656552.articles.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter.State.Empty.painter
import kotlinx.coroutines.delay
import nl.becu.dewi.student656552.articles.presentation.util.Screen
import nl.becu.dewi.student656552.R


@Composable
fun SplashScreen(navController: NavController){
    val scale = remember { androidx.compose.animation.core.Animatable(0f) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(Screen.MainScreen.route)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
       Image(
           painter = painterResource(R.drawable.shiba_icon),
           contentDescription = stringResource(com.google.android.material.R.string.icon_content_description),
           modifier = Modifier.scale(scale.value)
       )
    }
}