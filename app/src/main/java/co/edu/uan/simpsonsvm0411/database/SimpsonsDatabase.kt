package co.edu.uan.simpsonsvm0411.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Character::class], version = 1)
abstract class SimpsonsDatabase: RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao

    companion object {
        // Singleton: crea un solo objeto de base de datos
        var database : SimpsonsDatabase? = null

        fun getDatabase(ctx: Context): SimpsonsDatabase {
            if(database!=null)
                return database!!
            else {
                database = Room.databaseBuilder(
                    ctx,
                    SimpsonsDatabase::class.java,
                    "simpsons_db"
                ).build()
            }
            return database!!
        }
    }
}