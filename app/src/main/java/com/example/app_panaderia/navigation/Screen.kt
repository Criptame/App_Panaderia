package com.example.app_panaderia.navigation

sealed class Screen(val route:String){
    data object Admin:Screen (route = "admin_page")
    data object Vizu:Screen(route = "vizu_page")
    data object ConfigPed:Screen(route = "configped_page")
    data object AñadirCom:Screen(route = "añadircom_page")
    data object AñadirPed:Screen(route = "añadirped_page")
    data object Com:Screen(route = "com_page")
    data object DeteleCom:Screen(route = "deletecom_page")
    data object DetelePed:Screen(route = "deleteped_page")
    data object Ped:Screen(route = "ped_page")


    data class Detail (val itemId:String):Screen(route = "detail_page/{itemId}"){

        fun buidRoute():String {
            return route.replace("{itemId}",itemId)
        }
    }
}