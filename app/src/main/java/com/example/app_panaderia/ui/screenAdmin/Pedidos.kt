package com.example.app_panaderia.ui.screenAdmin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_panaderia.model.Pedido
import com.example.app_panaderia.viewModels.MainViewModel

// Datos de ejemplo
val samplePedidos = listOf(
    Pedido(id = 1L, compradorId = 101L, total = 25.50, estado = "Pendiente", fecha = "2024-05-20", direccionEntrega = "Calle Falsa 123"),
    Pedido(id = 2L, compradorId = 102L, total = 15.00, estado = "En reparto", fecha = "2024-05-20", direccionEntrega = "Avenida Siempreviva 742"),
    Pedido(id = 3L, compradorId = 101L, total = 45.75, estado = "Entregado", fecha = "2024-05-19", direccionEntrega = "Elm Street 1428")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidosScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Pedidos") },
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
            items(samplePedidos) { pedido ->
                PedidoItem(pedido = pedido)
            }
        }
    }
}

@Composable
fun PedidoItem(pedido: Pedido) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ReceiptLong,
                contentDescription = "Icono de Pedido",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Pedido #${pedido.id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Cliente: ${pedido.compradorId}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Total: $${String.format("%.2f", pedido.total)}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                val estadoColor = when (pedido.estado) {
                    "Entregado" -> Color(0xFF388E3C) // Verde para estado completado
                    "En reparto" -> MaterialTheme.colorScheme.tertiary // Rojo suave del tema
                    else -> MaterialTheme.colorScheme.primary // Marrón del tema para otros estados
                }
                Text(text = pedido.estado, color = estadoColor, fontWeight = FontWeight.Bold)
                Text(text = pedido.fecha, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
