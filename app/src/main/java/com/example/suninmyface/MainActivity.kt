package com.example.suninmyface // R

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.suninmyface.databinding.ActivityMainBinding
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.Clock
import java.util.*

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

        //https://stackoverflow.com/questions/55823042/how-to-update-this-textview-with-the-date-every-second
        val timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask(){
            override fun run(){
                updateClock()
            }
        },0,1000)




    }

    private fun updateClock(){
        runOnUiThread{
            binding.textViewMainTrueTime.text = "${SimpleDateFormat("hh:mm:ss").format(Date())}"
        }
    }
}