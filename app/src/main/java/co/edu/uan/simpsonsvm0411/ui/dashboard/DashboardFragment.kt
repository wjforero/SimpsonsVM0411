package co.edu.uan.simpsonsvm0411.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.edu.uan.simpsonsvm0411.databinding.FragmentDashboardBinding
import co.edu.uan.simpsonsvm0411.services.DogImage
import co.edu.uan.simpsonsvm0411.services.DogsApi
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.readDogs()

        dashboardViewModel.dogs.observe(viewLifecycleOwner) {
            for(dog in it) {
                showDog(dog.url)
            }
        }
        return root
    }


    fun readDogsIonGson() {
        Ion.with(activity)
            .load("https://api.thedogapi.com/v1/images/search?limit=10")
            .asString()
            .setCallback { ex, result ->
                if(ex!=null) {
                    Log.v("Dogs", "Error: ${ex.message}")
                } else {
                    val dogs = Gson().fromJson(result, Array<DogImage>::class.java)
                    for(dog in dogs) {
                        showDog(dog.url)
                    }
                }
            }
    }

    fun readDogsIonJson() {
        Ion.with(activity)
            .load("https://api.thedogapi.com/v1/images/search?limit=10")
            .asJsonArray()
            .setCallback { ex, result ->
                if(ex!=null) {
                    Log.v("Dogs", "Error: ${ex.message}")
                } else {
                    for(dog in result) {
                        val id = dog.asJsonObject.get("url").asString
                        val width = dog.asJsonObject.get("url").asString
                        val height = dog.asJsonObject.get("url").asString
                        val url = dog.asJsonObject.get("url").asString
                        showDog(url)
                    }
                }
            }

    }

    fun showDog(url: String) {
        val iv = ImageView(requireActivity())
        Ion.with(activity)
            .load(url)
            .intoImageView(iv)
        binding.imagesContainer.addView(iv)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}