package com.example.app_panaderia.ui.screenRepartidor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_panaderia.model.Pedido
import com.example.app_panaderia.navigation.Screen
import com.example.app_panaderia.viewModels.RepartidorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidosRepartidorScreen(
    navController: NavController,
    repartidorViewModel: RepartidorViewModel = viewModel()
) {
    val pedidos by repartidorViewModel.pedidos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Pedidos Asignados") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(pedidos) { pedido ->
                PedidoRepartidorItem(navController = navController, pedido = pedido)
            }
        }
    }
}

@Composable
fun PedidoRepartidorItem(
    navController: NavController,
    pedido: Pedido
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Pedido #${pedido.id}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "Cliente: ${pedido.compradorId}")
            Row {
                Text("Estado: ", fontWeight = FontWeight.SemiBold)
                Text(pedido.estado, color = if (pedido.estado == "En reparto") Color(0xFFFFA000) else Color.Blue)
            }
            Text(text = "Total: $${String.format("%.2f", pedido.total)}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate(Screen.RepartidorConfirmacion.createRoute(pedido.id)) }) {
                Text("Confirmar Entrega")
            }
        }
    }
}
