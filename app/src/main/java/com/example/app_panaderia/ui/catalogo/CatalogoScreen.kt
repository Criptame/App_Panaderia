package com.example.app_panaderia.ui.catalogo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_panaderia.model.Pan
import com.example.app_panaderia.viewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    navController: NavController,
    userViewModel: UserViewModel = viewModel()
) {
    val catalogo by userViewModel.catalogo.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo De Pan") },
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
            items(catalogo) { pan ->
                PanItem(pan = pan)
            }
        }
    }
}

@Composable
fun PanItem(pan: Pan) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = pan.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = pan.descripcion, fontSize = 14.sp)
                // Formato para pesos chilenos (sin decimales)
                Text(text = "$${pan.precio.toInt()}", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            }
            Button(onClick = { /* TODO: Añadir al carrito */ }) {
                Text("Añadir")
            }
        }
    }
}
