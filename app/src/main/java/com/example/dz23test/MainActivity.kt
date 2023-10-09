package com.example.dz23test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dz23test.databinding.ActivityMainBinding
import com.example.dz23test.model.ResponseModel
import com.example.dz23test.viewModel.ResponseModelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[ResponseModelViewModel::class.java]
        viewModel.uiState.observe(this) { uiState ->
            when (uiState) {
                ResponseModelViewModel.Empty -> Unit
                ResponseModelViewModel.Processing -> binding.progressCircular.visibilityOn()
                is ResponseModelViewModel.Success -> setTextOnView(uiState.model)
                is ResponseModelViewModel.Error -> setErrorTextOnView(uiState.error)
            }
        }
        binding.btMain.setOnClickListener {
            viewModel.getCryptoByName(name = "bitcoin")
        }
    }


    private fun setErrorTextOnView(error: String) {
        binding.progressCircular.visibilityOff()
        binding.tvMain.text = error
    }

    private fun setTextOnView(model: ResponseModel) {
        binding.progressCircular.visibilityOff()
        binding.tvMain.text = null
        binding.tvMain.append(model.data?.symbol + "\n")
        binding.tvMain.append(priceRounding(price = "${model.data?.rateUsd}") + " $")
    }

    private fun priceRounding(price: String) = String.format("%.2f", price.toDouble())
}

