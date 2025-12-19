package com.tourly.app.onboarding.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tourly.app.R
import com.tourly.app.core.ui.components.foundation.AppLogoWithText
import com.tourly.app.core.ui.components.foundation.OutlinedPillButton
import com.tourly.app.core.ui.theme.OutfitFamily

@Composable
fun WelcomeContent(
    onGetStartedClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(height = 70.dp))

            AppLogoWithText(fontSize = 40.sp)

            Spacer(modifier = Modifier.height(height = 16.dp))

            Text(
                text = stringResource(id = R.string.welcome_abroad),
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 38.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(height = 8.dp))

            Text(
                text = stringResource(id = R.string.welcome_subtitle),
                fontFamily = OutfitFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(height = 28.dp))

            OutlinedPillButton(
                text = "Start Now",
                onClick = onGetStartedClick,
                modifier = Modifier
                    .wrapContentWidth()
            )
        }

        Image(
            painter = painterResource(id = R.drawable.traveler),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomStart),
            contentScale = ContentScale.FillWidth
        )


    }
}
