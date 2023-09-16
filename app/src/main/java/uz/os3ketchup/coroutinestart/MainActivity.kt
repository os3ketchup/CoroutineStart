package uz.os3ketchup.coroutinestart

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.os3ketchup.coroutinestart.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnDownload.setOnClickListener {
            lifecycleScope.launch {
                loadData()
            }
        }

    }

    private suspend fun loadData() {
        binding.progressBar.isVisible = true
        binding.btnDownload.isEnabled = false
        val name = loadCity()
        binding.tvCity.text = name
        val temperature = loadTemperature(this, name)
        binding.tvTemp.text = temperature.toString()
        binding.progressBar.isVisible = false
        binding.btnDownload.isEnabled = true


    }
}

 private suspend fun loadTemperature(context: Context, city: String): Int {
    Toast.makeText(context, city, Toast.LENGTH_SHORT).show()
    delay(5000)
    return 17

}

private suspend fun loadCity(): String {
    delay(5000)
    return "London"
}
