package com.example.app_panaderia.ui.screenAdmin

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_panaderia.navigation.Screen
import com.example.app_panaderia.ui.components.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisualizarEntidadesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Panel de Administrador") })
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
            PrimaryButton(
                text = "Gestionar Compradores",
                onClick = { navController.navigate(Screen.Com.route) } // Navega a la lista de compradores
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(
                text = "Gestionar Pedidos",
                onClick = { navController.navigate(Screen.Pedidos.route) } // Navega a la lista de pedidos
            )
        }
    }
}
