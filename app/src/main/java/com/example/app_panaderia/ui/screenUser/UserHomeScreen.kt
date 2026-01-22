package com.example.app_panaderia.ui.screenUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_panaderia.navigation.Screen
import com.example.app_panaderia.ui.components.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Bienvenido a la Panadería") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¿Qué te apetece hoy?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "Ver Catálogo de Panes",
                onClick = { navController.navigate(Screen.UserCatalogo.route) }
            )
            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = "Mis Pedidos",
                onClick = { /* TODO: Navegar a la pantalla de mis pedidos */ }
            )
            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = "Mi Perfil",
                onClick = { navController.navigate(Screen.UserProfile.route) }
            )
        }
    }
}
