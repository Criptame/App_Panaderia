package com.example.app_panaderia.model.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CompradorDao {

    @Query("SELECT * FROM compradores ORDER BY nombre ASC")
    fun getAll(): Flow<List<Comprador>>

    @Query("SELECT * FROM compradores WHERE id = :id")
    suspend fun getById(id: Long): Comprador?  // Cambiado a suspend

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comprador: Comprador): Long

    @Update
    suspend fun update(comprador: Comprador)

    @Delete
    suspend fun delete(comprador: Comprador)

    @Query("SELECT * FROM compradores WHERE email = :email")
    suspend fun findByEmail(email: String): Comprador?  // Cambiado a suspend

    @Query("SELECT * FROM compradores WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): Comprador?

    @Query("DELETE FROM compradores WHERE id = :id")
    suspend fun deleteById(id: Long)

    // Método para obtener como Flow (opcional, para observación reactiva)
    @Query("SELECT * FROM compradores WHERE id = :id")
    fun getByIdFlow(id: Long): Flow<Comprador?>
}