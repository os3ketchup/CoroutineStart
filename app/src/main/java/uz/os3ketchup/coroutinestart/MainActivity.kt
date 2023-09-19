package uz.os3ketchup.coroutinestart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import uz.os3ketchup.coroutinestart.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        binding.btnEnter.setOnClickListener {
            viewModel.calculate(binding.etNumber.text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) {
            binding.progressBar.visibility = View.GONE
            binding.btnEnter.isEnabled = true

            when (it) {
                is Error -> {
                    Toast.makeText(this, "you did not enter value", Toast.LENGTH_SHORT).show()
                }

                is Progress -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnEnter.isEnabled = false
                }

                is Factorial -> {
                    binding.tvNumber.text = it.value
                }
            }
        }
    }
}


