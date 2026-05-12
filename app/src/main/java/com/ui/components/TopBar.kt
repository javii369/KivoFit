package com.KivoFit.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.KivoFit.R
import com.KivoFit.navigation.Route

@Composable
fun TopBar(
    navController: NavHostController,
    onProfileClick: () -> Unit = {}
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val hidden = currentRoute == Route.Notifications.route ||
            currentRoute == Route.Chat.route ||
            currentRoute == Route.Login.route ||
            currentRoute == Route.Register.route ||
            currentRoute == Route.RecoverPassword.route

    if (hidden) return

    val isRootTab = currentRoute == Route.Inicio.route ||
            currentRoute == Route.History.route ||
            currentRoute == Route.Calendar.route
    val canGoBack = !isRootTab && navController.previousBackStackEntry != null

    val canGoBackToInicio = currentRoute == Route.Calendar.route &&
            navController.previousBackStackEntry?.destination?.route == Route.Inicio.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        if (canGoBack || canGoBackToInicio) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                    .clickable { navController.popBackStack() }
                    .align(Alignment.CenterStart),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.logo_kivofit),
            contentDescription = "Logo KivoFit",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(56.dp)
                .align(Alignment.Center)
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.primary, CircleShape)
                .clickable(onClick = onProfileClick)
                .align(Alignment.CenterEnd),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Perfil",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
