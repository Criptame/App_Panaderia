package com.example.app_panaderia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.app_panaderia.navigation.*
import com.example.app_panaderia.ui.theme.App_PanaderiaTheme
import com.example.app_panaderia.ui.screenAdmin.*
import com.example.app_panaderia.viewModels.MainViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_PanaderiaTheme {
                // ViewModel y NavController
                val viewModel: MainViewModel = viewModel()
                val navController = rememberNavController()

                // Escuchar eventos de navegación emitidos por el ViewModel
                LaunchedEffect(key1 = Unit) {
                    viewModel.navigationEvents.collectLatest { event ->
                        when (event) {
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(route = event.route.route) {
                                    event.popUpToRoute?.let {
                                        popUpTo(it.route) {
                                            inclusive = event.inclusive
                                        }
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

                // Layout base con NavHost
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Admin.route,
                        modifier = Modifier.padding(paddingValues = innerPadding)
                    ) {
                        composable(route = Screen.Admin.route) {
                            InicioUser(navController = navController, viewModel = viewModel)
                        }
                        composable(route = Screen.Vizu.route) {
                            VisualizarEntidades(navController = navController, viewModel = viewModel)
                        }
                        composable(route = Screen.AñadirCom.route){
                            AñadirComprador(navController = navController, viewModel = viewModel)
                        }

                        }
                    }
                }
            }
        }
    }
