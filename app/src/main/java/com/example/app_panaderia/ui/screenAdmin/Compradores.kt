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
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_panaderia.model.UsuarioUiState
import com.example.app_panaderia.viewModels.MainViewModel

// Datos de ejemplo
val sampleCompradores = listOf(
    UsuarioUiState(id = "USR-001", nombre = "Carlos", email = "carlos@email.com"),
    UsuarioUiState(id = "USR-002", nombre = "Andrea", email = "andrea@email.com"),
    UsuarioUiState(id = "USR-003", nombre = "Luis", email = "luis@email.com")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompradoresScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Compradores") },
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
            items(sampleCompradores) { comprador ->
                CompradorCard(comprador = comprador)
            }
        }
    }
}

@Composable
fun CompradorCard(comprador: UsuarioUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant // Usamos un color del tema
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Icono de Comprador",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary // Usamos el color primario del tema
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = comprador.nombre,
                    style = MaterialTheme.typography.titleLarge, // Usamos un estilo de tipografía del tema
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = comprador.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
