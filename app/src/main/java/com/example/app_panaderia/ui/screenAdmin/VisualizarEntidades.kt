package com.example.app_panaderia.ui.screenAdmin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_panaderia.navigation.Screen
import com.example.app_panaderia.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisualizarEntidadesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Dashboard,
                            contentDescription = "Panel",
                            tint = Color.White
                        )
                        Text(
                            "Panel de Administrador",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BrownPrimary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(CreamBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Tarjetas de gestión
                CardOpcionAdmin(
                    title = "Gestionar Productos",
                    description = "Agregar, editar y eliminar productos del catálogo",
                    icon = Icons.Default.BakeryDining,
                    backgroundColor = BrownPrimary.copy(alpha = 0.1f),
                    iconColor = BrownPrimary,
                    onClick = { navController.navigate(Screen.Produc.route) }
                )

                CardOpcionAdmin(
                    title = "Gestionar Compradores",
                    description = "Ver y administrar clientes registrados",
                    icon = Icons.Default.People,
                    backgroundColor = BrownVariant.copy(alpha = 0.1f),
                    iconColor = BrownVariant,
                    onClick = { navController.navigate(Screen.Com.route) }
                )

                CardOpcionAdmin(
                    title = "Gestionar Pedidos",
                    description = "Ver y administrar pedidos del sistema",
                    icon = Icons.Default.ShoppingCart,
                    backgroundColor = RedAccent.copy(alpha = 0.1f),
                    iconColor = RedAccent,
                    onClick = { navController.navigate(Screen.Pedidos.route) }
                )

                Spacer(modifier = Modifier.weight(1f))

                // Botón para cerrar sesión
                OutlinedButton(
                    onClick = {
                        // Navegar de vuelta al login
                        navController.navigate(Screen.Admin.route) {
                            popUpTo(Screen.Vizu.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = BrownPrimary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Cerrar sesión",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cerrar Sesión")
                }
            }
        }
    }
}

@Composable
fun CardOpcionAdmin(
    title: String,
    description: String,
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Icono
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Información
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = BrownVariant
                )

                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp
                )
            }

            // Flecha
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ir a $title",
                tint = Color.Gray
            )
        }
    }
}