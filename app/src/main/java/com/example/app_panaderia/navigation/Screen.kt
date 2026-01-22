package com.example.app_panaderia.navigation

sealed class Screen(val route: String) {
    // Common
    data object RoleSelection : Screen(route = "role_selection_page")

    // Admin Flow
    data object Admin : Screen(route = "admin_page")
    data object Vizu : Screen(route = "vizu_page")
    data object ConfigPed : Screen(route = "configped_page")
    data object AñadirCom : Screen(route = "añadircom_page")
    data object AñadirPed : Screen(route = "añadirped_page")
    data object Com : Screen(route = "com_page")
    data object DeteleCom : Screen(route = "deletecom_page")
    data object DetelePed : Screen(route = "deleteped_page")
    data object Ped : Screen(route = "ped_page")
    data object Pedidos : Screen(route = "pedidos_page")

    // User Flow
    data object UserHome : Screen(route = "user_home_page")
    data object UserProfile : Screen(route = "user_profile_page")
    data object UserCatalogo : Screen(route = "user_catalogo_page") // Catálogo de productos

    // Repartidor Flow
    data object Repartidor : Screen(route = "repartidor_home_page")
    data object RepartidorPedidos : Screen(route = "repartidor_pedidos_page")
    data object RepartidorGPS : Screen(route = "repartidor_gps_page")
    data object RepartidorConfirmacion : Screen(route = "repartidor_confirmacion_page/{pedidoId}") {
        fun createRoute(pedidoId: String) = "repartidor_confirmacion_page/$pedidoId"
    }

    // Detail Screen (Ejemplo con argumentos)
    data class Detail(val itemId: String) : Screen(route = "detail_page/{itemId}") {
        fun buildRoute(): String {
            return route.replace("{itemId}", itemId)
        }
    }
}
