package com.example.app_panaderia.model.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface CompradoresDao {
    @Query("SELECT * FROM compradores ORDER BY id DESC")
    fun getAll(): Flow<List<Comprador>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comprador: Comprador): Long

    @Update
    suspend fun update(comprador: Comprador)

    @Delete
    suspend fun delete(comprador: Comprador)

    @Query("SELECT * FROM compradores WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): Comprador?

}