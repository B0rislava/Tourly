package com.tourly.app.login.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tourly.app.R
import com.tourly.app.login.presentation.ui.components.AuthCard
import com.tourly.app.login.presentation.ui.components.AuthCardHeader
import com.tourly.app.core.ui.components.foundation.ClickableText
import com.tourly.app.login.presentation.ui.components.EmailTextField
import com.tourly.app.login.presentation.ui.components.FirstNameTextField
import com.tourly.app.login.presentation.ui.components.LastNameTextField
import com.tourly.app.login.presentation.ui.components.PasswordTextField
import com.tourly.app.core.ui.components.foundation.PrimaryButton
import com.tourly.app.login.presentation.ui.components.RoleSelector
import com.tourly.app.login.presentation.ui.components.UserRole

@Composable
fun SignUpContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    role: UserRole,
    onRoleChange: (UserRole) -> Unit,
    emailError: String?,
    passwordError: String?,
    firstNameError: String?,
    lastNameError: String?,
    signUpError: String?,
    isLoading: Boolean,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    AuthCard {
        AuthCardHeader(
            title = stringResource(id = R.string.welcome_abroad),
            subtitle = stringResource(id = R.string.journey_starts)
        )

        Spacer(modifier = Modifier.height(height = 20.dp))

        RoleSelector(
            selectedRole = role,
            onRoleSelected = onRoleChange
        )

        Spacer(modifier = Modifier.height(height = 10.dp))

        FirstNameTextField(
            value = firstName,
            onValueChange = onFirstNameChange
        )

        if (firstNameError != null) {
            Text(
                text = firstNameError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(height = 10.dp))

        LastNameTextField(
            value = lastName,
            onValueChange = onLastNameChange
        )

        if (lastNameError != null) {
            Text(
                text = lastNameError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(height = 10.dp))

        EmailTextField(
            value = email,
            onValueChange = onEmailChange
        )

        if (emailError != null) {
            Text(
                text = emailError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(height = 10.dp))

        PasswordTextField(
            value = password,
            onValueChange = onPasswordChange
        )

        if (passwordError != null) {
            Text(
                text = passwordError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(height = 20.dp))

        PrimaryButton(
            text = stringResource(id = R.string.register),
            onClick = onRegisterClick,
            enabled = !isLoading
        )

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        if (signUpError != null) {
            Text(
                text = signUpError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(height = 8.dp))

        ClickableText(
            prefixText = stringResource(id = R.string.have_account),
            actionText = stringResource(id = R.string.login),
            onActionClick = onLoginClick
        )
    }
}