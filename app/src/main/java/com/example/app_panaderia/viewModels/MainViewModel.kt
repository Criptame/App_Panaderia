package com.example.app_panaderia.viewModels

import androidx.lifecycle.ViewModel
import com.example.app_panaderia.navigation.NavigationEvent
import com.example.app_panaderia.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _navigationsEvents = MutableSharedFlow<NavigationEvent>()

    val navigationEvents:SharedFlow<NavigationEvent> =_navigationsEvents.asSharedFlow()

    fun navigateTo(screen: Screen){
        CoroutineScope(Dispatchers.Main).launch {
            _navigationsEvents.emit(NavigationEvent.NavigateTo(route=screen))
        }
    }

    fun navigateBack(){
        CoroutineScope(Dispatchers.Main).launch {
            _navigationsEvents.emit(NavigationEvent.PopBackStack)
        }
    }

    fun navigateUp(){
        CoroutineScope(Dispatchers.Main).launch {
            _navigationsEvents.emit(NavigationEvent.NavigateUp)
        }
    }
}