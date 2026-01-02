package com.tourly.app.profile.presentation.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tourly.app.R
import com.tourly.app.core.ui.theme.OutfitFamily
import com.tourly.app.profile.presentation.state.EditProfileUiState
import com.tourly.app.profile.presentation.ui.components.EditProfileTextField

@Composable
fun EditProfileContent(
    state: EditProfileUiState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val defaultProfilePicture = "https://api.dicebear.com/9.x/avataaars/png?seed=${state.email}"

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Profile Picture
        AsyncImage(
            model = defaultProfilePicture,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Personal Section
        Text(
            text = "Personal",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = OutfitFamily,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        EditProfileTextField(
            value = state.firstName,
            onValueChange = onFirstNameChange,
            label = stringResource(id = R.string.first_name),
            isError = state.firstNameError != null,
            supportingText = state.firstNameError
        )

        Spacer(modifier = Modifier.height(16.dp))

        EditProfileTextField(
            value = state.lastName,
            onValueChange = onLastNameChange,
            label = stringResource(id = R.string.last_name),
            isError = state.lastNameError != null,
            supportingText = state.lastNameError
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email & Password Section
        Text(
            text = "Email & password",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = OutfitFamily,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        EditProfileTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.email),
            isError = state.emailError != null,
            supportingText = state.emailError
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        var passwordVisible by remember { mutableStateOf(false) }
        EditProfileTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = "New Password (Optional)",
            isError = state.passwordError != null,
            supportingText = state.passwordError ?: "Leave empty to keep current password",
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) stringResource(id = R.string.hide_password) else stringResource(id = R.string.show_password)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        )

        if (state.saveError != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = state.saveError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = OutfitFamily
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (state.isSaving) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = onSaveClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Save",
                    fontFamily = OutfitFamily
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}