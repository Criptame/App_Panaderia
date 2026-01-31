// En CatalogoScreen.kt - corregir la navegación
package com.example.app_panaderia.ui.catalogo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_panaderia.model.Pan
import com.example.app_panaderia.navigation.Screen
import com.example.app_panaderia.viewModels.UserViewModel
import com.example.app_panaderia.viewModels.CarritoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    navController: NavController,
    userViewModel: UserViewModel = viewModel(),
    carritoViewModel: CarritoViewModel = viewModel()
) {
    val catalogo by userViewModel.catalogo.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Catálogo de Pan",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    // Icono del carrito con contador
                    val carritoItems by carritoViewModel.carritoItems.collectAsState()
                    val totalItems = carritoItems.sumOf { it.cantidad }

                    BadgedBox(
                        badge = {
                            if (totalItems > 0) {
                                Badge {
                                    Text(totalItems.toString())
                                }
                            }
                        }
                    ) {
                        IconButton(onClick = {
                            navController.navigate(Screen.Carrito.route) // Usa la ruta definida
                        }) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(catalogo) { pan ->
                PanCard(
                    pan = pan,
                    carritoViewModel = carritoViewModel,
                    onVerCarrito = { navController.navigate(Screen.Carrito.route) }
                )
            }
        }
    }
}

@Composable
fun PanCard(
    pan: Pan,
    carritoViewModel: CarritoViewModel,
    onVerCarrito: () -> Unit
) {
    val cantidadEnCarrito by remember {
        derivedStateOf {
            carritoViewModel.getCantidadEnCarrito(pan.id)
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = pan.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = pan.descripcion,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "$${pan.precio.toInt()}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFFD32F2F)
                )
            }

            // Mostrar cantidad si ya está en el carrito
            if (cantidadEnCarrito > 0) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = {
                            carritoViewModel.actualizarCantidad(pan.id, cantidadEnCarrito - 1)
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Text("-", fontSize = 20.sp)
                    }

                    Text(
                        text = cantidadEnCarrito.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    IconButton(
                        onClick = {
                            carritoViewModel.actualizarCantidad(pan.id, cantidadEnCarrito + 1)
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Text("+", fontSize = 20.sp)
                    }
                }
            } else {
                Button(
                    onClick = {
                        carritoViewModel.agregarAlCarrito(pan)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8D6E63)
                    )
                ) {
                    Text("Añadir")
                }
            }
        }
    }
}