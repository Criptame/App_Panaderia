package com.example.app_panaderia.ui.screenRepartidor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_panaderia.ui.components.PrimaryButton
import com.example.app_panaderia.viewModels.RepartidorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmacionPedidoScreen(
    navController: NavController,
    repartidorViewModel: RepartidorViewModel = viewModel(),
    pedidoId: String?
) {
    val pedidos by repartidorViewModel.pedidos.collectAsState()
    val pedido = pedidos.find { it.id == pedidoId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirmar Entrega") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (pedido != null) {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Pedido #${pedido.id}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Cliente: ${pedido.compradorId}")
                        Text("Total a cobrar: $${String.format("%.2f", pedido.total)}")
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("¿Confirmas que el pedido ha sido entregado y pagado?", fontSize = 16.sp)
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                PrimaryButton(
                    text = "Sí, confirmar entrega",
                    onClick = {
                        // TODO: viewModel.confirmarEntrega(pedido.id)
                        navController.popBackStack()
                    }
                )
            } else {
                Text("No se encontró el pedido.")
            }
        }
    }
}
