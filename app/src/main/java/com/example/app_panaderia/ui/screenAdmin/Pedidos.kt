package com.example.app_panaderia.ui.screenAdmin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_panaderia.model.Pedido
import com.example.app_panaderia.viewModels.MainViewModel

// Datos de ejemplo
val samplePedidos = listOf(
    Pedido(id = "PED-001", compradorId = "USR-001", total = 25.50, estado = "Pendiente", fecha = "2024-05-20"),
    Pedido(id = "PED-002", compradorId = "USR-002", total = 15.00, estado = "En reparto", fecha = "2024-05-20"),
    Pedido(id = "PED-003", compradorId = "USR-001", total = 45.75, estado = "Entregado", fecha = "2024-05-19")
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
