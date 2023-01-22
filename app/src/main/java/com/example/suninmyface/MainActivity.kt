package com.example.suninmyface // R

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.suninmyface.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var isRed = false

        binding.buttonMainChangeBackground.setOnClickListener{
            if (isRed){
                binding.layoutMain.setBackgroundColor(Color.rgb(255, 255, 255))
            } else {
                binding.layoutMain.setBackgroundColor(Color.rgb(255, 0, 0))
            }
            isRed = !isRed
        }
    }
}