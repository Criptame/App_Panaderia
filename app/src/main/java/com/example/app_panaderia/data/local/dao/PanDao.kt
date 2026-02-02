package com.example.app_panaderia.data.local.dao

import androidx.room.*
import com.example.app_panaderia.model.Pan
import kotlinx.coroutines.flow.Flow

@Dao
interface PanDao {
    @Query("SELECT * FROM productos ORDER BY nombre")
    fun getAll(): Flow<List<Pan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productos: List<Pan>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pan: Pan)

    @Query("DELETE FROM productos WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM productos")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM productos")
    suspend fun count(): Int

    @Query("SELECT * FROM productos WHERE id = :id")
    suspend fun getById(id: Long): Pan?
}