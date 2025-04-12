package co.edu.uan.simpsonsvm0411.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import co.edu.uan.simpsonsvm0411.database.SimpsonsDatabase
import kotlinx.coroutines.launch
import co.edu.uan.simpsonsvm0411.database.Character

class HomeViewModel(val app: Application) : AndroidViewModel(app) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getCharacters() {
        val db = Room.databaseBuilder(
            app,
            SimpsonsDatabase::class.java,
            "simpsons_db"
        ).build()
        viewModelScope.launch() {
            val characters =db.getCharacterDao().readALl()
            _text.value = characters.toString()
        }

    }

    fun saveCharacter(name: String) {
        val db = Room.databaseBuilder(
            app,
            SimpsonsDatabase::class.java,
            "simpsons_db"
        ).build()
        viewModelScope.launch() {
            db.getCharacterDao().createCharacter(Character(name=name, description = "desc", photo = "url"))
        }
        getCharacters()
    }
}