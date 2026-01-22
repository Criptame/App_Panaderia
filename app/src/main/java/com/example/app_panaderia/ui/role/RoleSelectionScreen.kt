package com.example.app_panaderia.ui.role

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_panaderia.navigation.Screen
import com.example.app_panaderia.ui.components.PrimaryButton

@Composable
fun RoleSelectionScreen(navController: NavController) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Selecciona un Rol",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                text = "Entrar como Administrador",
                onClick = { navController.navigate(Screen.Admin.route) }
            )
            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = "Entrar como Repartidor",
                onClick = { navController.navigate(Screen.Repartidor.route) } // Necesitaremos esta ruta
            )
            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = "Entrar como Usuario",
                onClick = { navController.navigate(Screen.RoleSelection.route) } // y esta
            )
        }
    }
}
