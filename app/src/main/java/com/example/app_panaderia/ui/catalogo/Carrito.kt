package com.example.app_panaderia.ui.catalogo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_panaderia.model.CarritoItem
import com.example.app_panaderia.viewModels.CarritoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    navController: NavController,
    carritoViewModel: CarritoViewModel = viewModel()
) {
    val carritoItems by carritoViewModel.carritoItems.collectAsState()
    val total by carritoViewModel.total.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Carrito") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                }
            )
        },
        bottomBar = {
            if (carritoItems.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Total:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(
                            "$${total.toInt()}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFFD32F2F)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8D6E63)
                        )
                    ) {
                        Text("Continuar pedido")
                    }
                }
            }
        }
    ) { padding ->
        if (carritoItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Vacío",
                        modifier = Modifier.size(60.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Carrito vacío", fontSize = 18.sp)
                    Text("Añade productos", color = Color.Gray)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(bottom = 100.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(carritoItems) { item ->
                    CarritoItemCard(
                        item = item,
                        onActualizar = { nueva ->
                            carritoViewModel.actualizarCantidad(item.productoId, nueva)
                        },
                        onEliminar = {
                            carritoViewModel.eliminarDelCarrito(item.productoId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CarritoItemCard(
    item: CarritoItem,
    onActualizar: (Int) -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Nombre y botón eliminar
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = onEliminar,
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(Icons.Default.Close, "Eliminar", tint = Color.Red)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Precio unitario
            Text(
                text = "Precio: $${item.precio.toInt()} c/u",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Cantidad y subtotal
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Contador
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onActualizar(item.cantidad - 1) },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Text("-", fontSize = 16.sp)
                    }

                    Text(
                        text = item.cantidad.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    IconButton(
                        onClick = { onActualizar(item.cantidad + 1) },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Text("+", fontSize = 16.sp)
                    }
                }

                // Subtotal
                Text(
                    text = "$${(item.subtotal).toInt()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF8D6E63)
                )
            }
        }
    }
}
