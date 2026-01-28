package com.example.app_panaderia.ui.screenRepartidor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DeliveryDining  // Icono correcto
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_panaderia.model.Pedido
import com.example.app_panaderia.viewModels.RepartidorViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidosRepartidor(
    navController: NavController,
    repartidorViewModel: RepartidorViewModel,  // Agregado parámetro ViewModel
) {
    val pedidos by repartidorViewModel.pedidos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Pedidos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
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
                PedidoRepartidorItem(pedido = pedido)
            }

            if (pedidos.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No tienes pedidos asignados",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidoRepartidorItem(pedido: Pedido) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = {
            // Aquí puedes navegar a detalles del pedido
        }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.DeliveryDining,  // Cambiado a Icons.Filled
                contentDescription = "Icono de Entrega",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.tertiary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Pedido #${pedido.id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Cliente ID: ${pedido.compradorId}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Total: $${String.format(Locale.getDefault(), "%.2f", pedido.total)}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Dirección: ${pedido.direccionEntrega}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                val estadoColor = when (pedido.estado.lowercase()) {
                    "entregado" -> Color(0xFF388E3C)
                    "en reparto" -> Color(0xFFF57C00)
                    else -> MaterialTheme.colorScheme.primary
                }

                Badge(
                    containerColor = estadoColor,
                    contentColor = Color.White
                ) {
                    Text(
                        text = pedido.estado,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                // Botón de acción según el estado
                when (pedido.estado.lowercase()) {
                    "en reparto" -> {
                        Button(
                            onClick = { /* Marcar como entregado */ },
                            modifier = Modifier.width(120.dp)
                        ) {
                            Text("Entregado")
                        }
                    }
                    "pendiente de recogida" -> {
                        OutlinedButton(
                            onClick = { /* Iniciar entrega */ },
                            modifier = Modifier.width(120.dp)
                        ) {
                            Text("Iniciar")
                        }
                    }
                }
            }
        }
    }
}
