package com.example.app_panaderia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.example.app_panaderia.ui.catalogo.CatalogoScreen
import com.example.app_panaderia.ui.role.RoleSelectionScreen
import com.example.app_panaderia.ui.screenAdmin.*
import com.example.app_panaderia.ui.screenRepartidor.*
import com.example.app_panaderia.ui.screenUser.*
import com.example.app_panaderia.ui.theme.App_PanaderiaTheme
import com.example.app_panaderia.viewModels.*
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
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
                        composable(route = Screen.RoleSelection.route) {
                            RoleSelectionScreen(navController = navController)
                        }

                        // --- Flujo de Administrador ---
                        composable(route = Screen.Admin.route) {
                            AdminLoginScreen(navController = navController)
                        }
                        composable(route = Screen.Vizu.route) {
                            VisualizarEntidadesScreen(navController = navController)
                        }
                        composable(route = Screen.Com.route) {
                            CompradoresScreen(navController = navController, viewModel = mainViewModel)
                        }
                        composable(route = Screen.Pedidos.route) {
                            PedidosScreen(navController = navController, viewModel = mainViewModel)
                        }
                        composable(route = Screen.AñadirCom.route) {
                            AñadirComprador(navController = navController, viewModel = mainViewModel)
                        }
                        composable(route = Screen.AñadirPed.route) {
                            AñadirPedidos(navController = navController, viewModel = mainViewModel)
                        }
                        composable(route = Screen.ConfigPed.route) {
                            ConfigurarPedido(navController = navController, viewModel = mainViewModel)
                        }
                        composable(route = Screen.DeteleCom.route) {
                            EliminarComprador(navController = navController, viewModel = mainViewModel)
                        }
                        composable(route = Screen.DetelePed.route) {
                            EliminarPedido(navController = navController, viewModel = mainViewModel)
                        }

                        // --- Flujo de Usuario ---
                        composable(route = Screen.UserHome.route) {
                            UserHomeScreen(navController = navController)
                        }
                        composable(route = Screen.UserProfile.route) {
                            val userViewModel: UserViewModel = viewModel()
                            Perfil(navController = navController, viewModel = userViewModel)
                        }
                        composable(route = Screen.UserCatalogo.route) {
                            val userViewModel: UserViewModel = viewModel()
                            CatalogoScreen(navController = navController, userViewModel = userViewModel)
                        }

                        // --- Flujo de Repartidor ---
                        composable(route = Screen.Repartidor.route) {
                            RepartidorHomeScreen(navController = navController)
                        }
                        composable(route = Screen.RepartidorPedidos.route) {
                            val repartidorViewModel: RepartidorViewModel = viewModel()
                            PedidosRepartidorScreen(navController = navController, repartidorViewModel = repartidorViewModel)
                        }
                        composable(route = Screen.RepartidorGPS.route) {
                            GPSScreen(navController = navController, viewModel = mainViewModel)
                        }
                        composable(
                            route = Screen.RepartidorConfirmacion.route,
                            arguments = listOf(navArgument("pedidoId") { type = NavType.StringType })
                        ) {
                            val repartidorViewModel: RepartidorViewModel = viewModel()
                            val pedidoId = it.arguments?.getString("pedidoId")
                            ConfirmacionPedidoScreen(
                                navController = navController,
                                repartidorViewModel = repartidorViewModel,
                                pedidoId = pedidoId
                            )
                        }
                    }
                }
            }
        }
    }
}
