package com.tourly.app.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tourly.app.R

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit
){
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        label = stringResource(id = R.string.email),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = stringResource(id = R.string.email)
            )
        }
    )
}