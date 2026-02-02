package com.example.app_panaderia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_panaderia.navigation.NavigationEvent
import com.example.app_panaderia.navigation.Screen
import com.example.app_panaderia.ui.catalogo.CarritoScreen
import com.example.app_panaderia.ui.catalogo.CatalogoScreen
import com.example.app_panaderia.ui.role.RoleSelectionScreen
import com.example.app_panaderia.ui.screenAdmin.*
import com.example.app_panaderia.ui.screenRepartidor.*
import com.example.app_panaderia.ui.screenUser.*
import com.example.app_panaderia.ui.theme.App_PanaderiaTheme
import com.example.app_panaderia.viewModels.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val productoViewModel: ProductoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_PanaderiaTheme {
                val navController = rememberNavController()
                val mainViewModel: MainViewModel = viewModel()

                LaunchedEffect(key1 = Unit) {
                    mainViewModel.navigationEvents.collectLatest { event ->
                        when (event) {
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(route = event.route.route) {
                                    event.popUpToRoute?.let {
                                        popUpTo(it.route) { inclusive = event.inclusive }
                                    }
                                    launchSingleTop = event.singleTop
                                    restoreState = true
                                }
                            }
                            is NavigationEvent.PopBackStack -> navController.popBackStack()
                            is NavigationEvent.NavigateUp -> navController.navigateUp()
                        }
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.RoleSelection.route,
                        modifier = Modifier.padding(paddingValues = innerPadding)
                    ) {
                        // --- Pantalla de Selección de Rol ---
                        composable(Screen.RoleSelection.route) {
                            RoleSelectionScreen(navController)
                        }

                        // --- Flujo de Administrador ---
                        composable(Screen.Admin.route) {
                            AdminLoginScreen(navController)
                        }
                        composable(Screen.Vizu.route) {
                            VisualizarEntidadesScreen(navController)
                        }
                        composable(Screen.Com.route) {
                            CompradoresScreen(navController, mainViewModel)
                        }
                        composable(Screen.Pedidos.route) {
                            PedidosScreen(navController, mainViewModel)
                        }

                        // --- PANTALLA DE PRODUCTOS CON ROOM ---
                        composable(Screen.Produc.route) {
                            ProductosScreen(navController, productoViewModel)
                        }

                        // --- PANTALLA PARA AÑADIR PRODUCTO ---
                        composable(Screen.AñadirCom.route) {
                            AñadirProductoScreenSimple(navController, productoViewModel)
                        }

                        // Otras pantallas de admin (placeholders)
                        composable(Screen.ConfigPed.route) {
                            Text("Configurar Pedidos")
                        }
                        composable(Screen.AñadirPed.route) {
                            Text("Añadir Pedido")
                        }
                        composable(Screen.DeteleCom.route) {
                            Text("Eliminar Comprador")
                        }
                        composable(Screen.DetelePed.route) {
                            Text("Eliminar Pedido")
                        }
                        composable(Screen.DeteleProduc.route) {
                            ProductosScreen(navController, productoViewModel)
                        }
                        composable(Screen.Ped.route) {
                            Text("Detalle Pedido")
                        }

                        // --- Flujo de Usuario ---
                        composable(Screen.UserHome.route) {
                            UserHomeScreen(navController)
                        }
                        composable(Screen.UserProfile.route) {
                            val userViewModel: UserViewModel = viewModel()
                            Perfil(navController, userViewModel)
                        }
                        composable(Screen.UserCatalogo.route) {
                            val userViewModel: UserViewModel = viewModel()
                            val carritoViewModel: CarritoViewModel = viewModel()
                            CatalogoScreen(navController, userViewModel, carritoViewModel)
                        }
                        composable(Screen.Carrito.route) {
                            val carritoViewModel: CarritoViewModel = viewModel()
                            CarritoScreen(navController, carritoViewModel)
                        }

                        // --- Flujo de Repartidor ---
                        composable(Screen.Repartidor.route) {
                            RepartidorHomeScreen(navController)
                        }
                        composable(Screen.RepartidorPedidos.route) {
                            val repartidorViewModel: RepartidorViewModel = viewModel()
                            PedidosRepartidor(navController, repartidorViewModel)
                        }
                        composable(Screen.RepartidorGPS.route) {
                            GPSScreen(navController, mainViewModel)
                        }
                        composable(
                            route = Screen.RepartidorConfirmacion.route,
                            arguments = listOf(navArgument("pedidoId") { type = NavType.StringType })
                        ) {
                            val repartidorViewModel: RepartidorViewModel = viewModel()
                            val pedidoId = it.arguments?.getString("pedidoId")
                            ConfirmacionPedidos(
                                navController = navController,
                                pedidoId = pedidoId?.toLongOrNull(),
                                repartidorId = 1L
                            )
                        }
                    }
                }
            }
        }
    }
}
