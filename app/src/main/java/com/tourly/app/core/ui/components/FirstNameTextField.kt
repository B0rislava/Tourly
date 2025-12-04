package com.tourly.app.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tourly.app.R

@Composable
fun FirstNameTextField(
    value: String,
    onValueChange: (String) -> Unit
){
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        label = stringResource(id = R.string.first_name),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = stringResource(id = R.string.first_name)
            )
        }
    )
}