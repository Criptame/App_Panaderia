package com.example.app_panaderia.model.data

import com.example.app_panaderia.model.*

import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface ProductoDao {

    @Query("SELECT * FROM productos ORDER BY id DESC")
    fun getAll(): Flow<List<Pan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Pan): Long

    @Update
    suspend fun update(product: Pan)

    @Delete
    suspend fun delete(product: Pan)

    @Query("SELECT * FROM productos WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): Pan?
    
}