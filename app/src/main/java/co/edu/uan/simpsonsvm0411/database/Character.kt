package co.edu.uan.simpsonsvm0411.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    val name: String,
    val description: String,
    val photo: String // url
) {
}