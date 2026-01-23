package com.example.app_panaderia.ui.role

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.app_panaderia.navigation.Screen
import com.example.app_panaderia.ui.components.PrimaryButton

@Composable
fun RoleSelectionScreen(navController: NavController) {

    Scaffold {

        Text("Bienvenido a la App de Panadería", fontSize = 20.sp, fontWeight = FontWeight.Medium)
        Image(
            painter = androidx.compose.ui.res.painterResource(id = com.example.app_panaderia.R.drawable.imagen),
            contentDescription = "Logo de la Panadería",
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp)
        )

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
