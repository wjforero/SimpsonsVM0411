package co.edu.uan.simpsonsvm0411.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharacterDao {

    // CREATE
    @Insert
    suspend fun createCharacter(character: Character)
    // READ
    @Query("SELECT * FROM characters")
    suspend fun readALl() : List<Character>
    @Query("SELECT * FROM characters WHERE name = :nombre")
    fun readByName(nombre: String) : Character
    // UPDATE
    @Update
    fun modifyCharacter(character: Character)
    // DELETE
    @Delete
    fun removeCharacter(character: Character)

}