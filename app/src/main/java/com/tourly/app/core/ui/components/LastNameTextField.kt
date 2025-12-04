package com.tourly.app.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun LastNameTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        label = "Last Name",
        placeholder = "Jane DOe",
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Person,
                contentDescription = "Last Name"
            )
        }
    )
}

