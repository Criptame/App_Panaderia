// AñadirProductoScreen.kt
package com.example.app_panaderia.ui.screenAdmin

import androidx.compose.foundation.layout.*
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
import com.example.app_panaderia.viewModels.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AñadirProductoScreenSimple(
    navController: NavController, 
    productoViewModel: ProductoViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Cabecera
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
            }
            Text("Nuevo Producto", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Formulario simple
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precio,
            onValueChange = { if (it.all { c -> c.isDigit() || c == '.' }) precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = cantidad,
            onValueChange = { if (it.all { c -> c.isDigit() }) cantidad = it },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (nombre.isNotEmpty() && precio.isNotEmpty() && cantidad.isNotEmpty()) {
                    productoViewModel.agregarProducto(
                        Pan(
                            nombre = nombre,
                            descripcion = "",
                            precio = precio.toDouble(),
                            cantidad = cantidad.toInt(),
                            categoria = "General",
                            disponible = true
                        )
                    )
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8D6E63)
            )
        ) {
            Text("Guardar")
        }
    }
}