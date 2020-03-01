package com.unokim.codelab.databinding.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.unokim.codelab.databinding.R
import com.unokim.codelab.databinding.data.SimpleViewModel
import com.unokim.codelab.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Obtain ViewModel from ViewModelProvider
    private val viewModel by lazy { ViewModelProvider(this).get(SimpleViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }
}
