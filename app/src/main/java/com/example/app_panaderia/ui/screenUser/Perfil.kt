package com.example.app_panaderia.ui.screenUser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_panaderia.ui.components.PrimaryButton
import com.example.app_panaderia.viewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(
    navController: NavController,
    viewModel: UserViewModel = viewModel()
) {
    val usuarioState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Perfil de Usuario") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tarjeta de perfil
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE0F7FA))
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Imagen de perfil (placeholder)
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Gray, shape = RoundedCornerShape(50.dp))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Nombre de usuario
                    Text(
                        text = usuarioState.nombre,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Email de usuario
                    Text(
                        text = usuarioState.email,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f)) // Empuja el botón hacia abajo

            // Botón de Cerrar Sesión
            PrimaryButton(
                text = "Cerrar Sesión",
                onClick = {
                    viewModel.cerrarSesion()
                    // TODO: Navegar a la pantalla de login después de cerrar sesión
                }
            )
        }
    }
}
