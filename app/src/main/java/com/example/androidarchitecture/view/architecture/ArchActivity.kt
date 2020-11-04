package com.example.androidarchitecture.view.architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidarchitecture.R
import com.example.androidarchitecture.viewModel.archtecture.ArchViewModel
import com.example.androidarchitecture.viewModel.archtecture.ArchViewModelFactory

class ArchActivity : AppCompatActivity() {
    private lateinit var increaseButton:Button
    private lateinit var countText : TextView
    private lateinit var archViewModel: ArchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arch)

        archViewModel = ViewModelProvider(this, ArchViewModelFactory()).get(ArchViewModel::class.java)

        countText = findViewById(R.id.count_text)
        increaseButton = findViewById(R.id.button_increase)
        increaseButton.setOnClickListener {
            archViewModel.increase()
            increaseButton.isEnabled = false
        }

        archViewModel.count.observe(this, Observer {
            count -> countText.text = count.toString()
        })
    }
}