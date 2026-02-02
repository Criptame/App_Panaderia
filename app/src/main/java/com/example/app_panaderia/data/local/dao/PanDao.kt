package com.example.app_panaderia.data.local.dao

import androidx.room.*
import com.example.app_panaderia.model.Pan
import kotlinx.coroutines.flow.Flow

@Dao
interface PanDao {
    @Query("SELECT * FROM productos ORDER BY nombre")
    fun getAll(): Flow<List<Pan>>

    @Insert
    suspend fun insert(pan: Pan)

    @Delete
    suspend fun delete(pan: Pan)

    @Query("DELETE FROM productos WHERE id = :id")
    suspend fun deleteById(id: Long)
}