package co.edu.uan.simpsonsvm0411.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.uan.simpsonsvm0411.services.DogImage
import co.edu.uan.simpsonsvm0411.services.DogsApi
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _dogs = MutableLiveData<List<DogImage>>().apply {
        value = listOf()
    }
    val dogs: LiveData<List<DogImage>> = _dogs

    fun readDogs() {
        viewModelScope.launch {
            _dogs.value = DogsApi.getInstance().search(20)
        }
    }
}