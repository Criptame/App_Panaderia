package com.example.app_panaderia.ui.screenAdmin

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
import androidx.navigation.NavController
import com.example.app_panaderia.model.Pan
import com.example.app_panaderia.navigation.Screen
import com.example.app_panaderia.viewModels.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(
    navController: NavController,
    productoViewModel: ProductoViewModel
) {
    val productos by productoViewModel.productos.collectAsState()
    var productoToDelete by remember { mutableStateOf<Pan?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Productos", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AñadirCom.route) },
                containerColor = Color(0xFF8D6E63) // BrownPrimary
            ) {
                Icon(Icons.Default.Add, "Añadir producto")
            }
        }
    ) { padding ->
        if (productos.isEmpty()) {
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
                        Icons.Default.BakeryDining,
                        "Vacío",
                        modifier = Modifier.size(60.dp),
                        tint = Color.Gray
                    )
                    Text("No hay productos", fontSize = 18.sp)
                    Text("Añade el primero", color = Color.Gray)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productos) { producto ->
                    ProductoItemSimple(
                        producto = producto,
                        onDelete = { productoToDelete = producto }
                    )
                }
            }
        }

        // Diálogo para eliminar
        if (productoToDelete != null) {
            AlertDialog(
                onDismissRequest = { productoToDelete = null },
                title = { Text("Eliminar Producto") },
                text = { Text("¿Eliminar ${productoToDelete?.nombre}?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            productoToDelete?.id?.let { productoViewModel.eliminarProducto(it) }
                            productoToDelete = null
                        }
                    ) {
                        Text("Eliminar", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { productoToDelete = null }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Composable
fun ProductoItemSimple(
    producto: Pan,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Información
            Column(modifier = Modifier.weight(1f)) {
                Text(producto.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(producto.descripcion, fontSize = 14.sp, color = Color.Gray)

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("$${producto.precio.toInt()}", fontWeight = FontWeight.SemiBold)
                    Text("Stock: ${producto.cantidad}", fontSize = 12.sp, color = Color.Gray)
                    Text(producto.categoria, fontSize = 12.sp, color = Color(0xFF8D6E63))
                }
            }

            // Botón eliminar
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, "Eliminar", tint = Color.Red)
            }
        }
    }
}