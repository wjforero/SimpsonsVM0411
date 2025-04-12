package co.edu.uan.simpsonsvm0411.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Character::class], version = 1)
abstract class SimpsonsDatabase: RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}