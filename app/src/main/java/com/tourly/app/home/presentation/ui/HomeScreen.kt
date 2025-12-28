package com.tourly.app.home.presentation.ui
import androidx.compose.runtime.Composable
import com.tourly.app.home.presentation.viewmodel.HomeViewModel
import com.tourly.app.core.ui.utils.WindowSizeState

@Composable
fun HomeScreen(
    // TODO: expose state from the VM, but here we just pass the VM for demo
    vm: HomeViewModel,
    windowSizeState: WindowSizeState,
    onLogout: () -> Unit
) {
    HomeContent(
        userId = vm.route.userId,
        email = vm.route.email,
        onLogoutClick = {
            vm.logout(onLogoutSuccess = onLogout)
        }
    )
}