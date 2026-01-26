package com.example.app_panaderia.ui.screenAdmin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_panaderia.model.Pedido
import com.example.app_panaderia.viewModels.MainViewModel
import java.util.Locale

// Datos de ejemplo
val samplePedidos = listOf(
    Pedido(
        id = 1L,
        compradorId = 1L,
        total = 25.50,
        estado = "Pendiente",
        fecha = "2024-05-20",
        direccionEntrega = "Calle Principal 123"
    ),
    Pedido(
        id = 2L,
        compradorId = 2L,
        total = 15.00,
        estado = "En reparto",
        fecha = "2024-05-20",
        direccionEntrega = "Avenida Central 456"
    ),
    Pedido(
        id = 3L,
        compradorId = 1L,
        total = 45.75,
        estado = "Entregado",
        fecha = "2024-05-19",
        direccionEntrega = "Calle Secundaria 789"
    )
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
            items(samplePedidos) { pedido ->  // Usar items correctamente
                PedidoItem(pedido = pedido)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidoItem(pedido: Pedido) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = {
            // Navegar a detalles del pedido
        }
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
                    text = "Cliente ID: ${pedido.compradorId}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Total: $${String.format(Locale.getDefault(), "%.2f", pedido.total)}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                if (pedido.direccionEntrega.isNotEmpty()) {
                    Text(
                        text = "Dirección: ${pedido.direccionEntrega}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                val estadoColor = when (pedido.estado.lowercase()) {
                    "entregado" -> Color(0xFF388E3C) // Verde para estado completado
                    "en reparto" -> Color(0xFFF57C00) // Naranja para en reparto
                    "preparando" -> Color(0xFF1976D2) // Azul para preparando
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
                Text(
                    text = pedido.fecha,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}