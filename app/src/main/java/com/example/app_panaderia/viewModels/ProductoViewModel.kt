package com.example.app_panaderia.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.app_panaderia.data.local.database.AppDatabase
import com.example.app_panaderia.model.Pan
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductoViewModel(application: Application) : AndroidViewModel(application) {
    private val panDao = AppDatabase.getDatabase(application).panDao()

    val productos: Flow<List<Pan>> = panDao.getAll()

    fun agregarProducto(pan: Pan) {
        viewModelScope.launch {
            panDao.insert(pan)
        }
    }

    fun eliminarProducto(id: Long) {
        viewModelScope.launch {
            panDao.deleteById(id)
        }
    }
}