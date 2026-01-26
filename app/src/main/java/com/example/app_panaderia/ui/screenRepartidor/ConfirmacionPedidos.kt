package com.example.app_panaderia.ui.screenRepartidor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ConfirmacionPedidos(
    navController: NavController,
    pedidoId: Long? = null,
    repartidorId: Long? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Confirmación de Pedido",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Mostrar información del pedido si está disponible
        pedidoId?.let { id ->
            Text(
                text = "Pedido ID: $id",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        repartidorId?.let { id ->
            Text(
                text = "Repartidor ID: $id",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "¿Confirmar la asignación de este pedido?",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    // Lógica para confirmar
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Confirmar")
            }

            OutlinedButton(
                onClick = { navController.popBackStack() }
            ) {
                Text("Cancelar")
            }
        }
    }
}