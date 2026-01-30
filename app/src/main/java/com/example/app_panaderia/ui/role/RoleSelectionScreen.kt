package com.example.app_panaderia.ui.role

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_panaderia.navigation.Screen

@Composable
fun RoleSelectionScreen(navController: NavController) {
    // Gradiente de fondo
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF8F4E9),  // Beige claro
            Color(0xFFF5E6D3),  // Beige medio
            Color(0xFFE8D2B5)   // Beige cálido
        )
    )

    Scaffold(
        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBackground)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header con ícono y título
                Icon(
                    imageVector = Icons.Default.PersonSearch,
                    contentDescription = "Selección de rol",
                    tint = Color(0xFF8B4513), // Marrón chocolate
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Bienvenido a Panadería Dulce",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF5D4037), // Marrón oscuro
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Selecciona tu rol para continuar",
                    fontSize = 16.sp,
                    color = Color(0xFF795548), // Marrón medio
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Tarjetas para cada rol
                RoleCard(
                    title = "Administrador",
                    description = "Gestiona productos, pedidos y usuarios",
                    icon = Icons.Default.AdminPanelSettings,
                    iconColor = Color(0xFF1976D2), // Azul
                    backgroundColor = Color(0xFFE3F2FD), // Azul claro
                    onClick = { navController.navigate(Screen.Admin.route) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoleCard(
                    title = "Repartidor",
                    description = "Consulta y entrega pedidos asignados",
                    icon = Icons.Default.DeliveryDining,
                    iconColor = Color(0xFF388E3C), // Verde
                    backgroundColor = Color(0xFFE8F5E9), // Verde claro
                    onClick = { navController.navigate(Screen.Repartidor.route) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoleCard(
                    title = "Usuario",
                    description = "Realiza pedidos y sigue tus compras",
                    icon = Icons.Default.Person,
                    iconColor = Color(0xFF7B1FA2), // Púrpura
                    backgroundColor = Color(0xFFF3E5F5), // Púrpura claro
                    onClick = { navController.navigate(Screen.UserHome.route) } // Cambiar a ruta correcta
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Texto informativo adicional
                Text(
                    text = "Selecciona según tus permisos de acceso",
                    fontSize = 12.sp,
                    color = Color(0xFF757575), // Gris
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleCard(
    title: String,
    description: String,
    icon: ImageVector,
    iconColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Ícono del rol
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.9f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(32.dp)
                )
            }

            // Texto del rol
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF212121) // Casi negro
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color(0xFF424242), // Gris oscuro
                    lineHeight = 16.sp
                )
            }

            // Flecha de navegación
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Ir a $title",
                tint = Color(0xFF757575), // Gris medio
                modifier = Modifier.size(20.dp)
            )
        }
    }
}