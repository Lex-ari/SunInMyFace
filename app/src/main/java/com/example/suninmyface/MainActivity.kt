package com.example.suninmyface // R

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.suninmyface.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var setHour = -1
    var setMinute = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //https://stackoverflow.com/questions/55823042/how-to-update-this-textview-with-the-date-every-second
        val timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask(){
            override fun run(){
                updateClock()
                updateBackground()
            }
        },0,1000)

        binding.textViewMainSetTime.setOnClickListener{
            val setTime = binding.editTextMainAlarmTime.text.toString()
            try{
                var date = SimpleDateFormat("hh:mm").parse(setTime)
                var alarmCalendar = Calendar.getInstance()
                alarmCalendar.time = date

                setHour = alarmCalendar.get(10) // Hour represented by 0-11
                setMinute = alarmCalendar.get(12) //Minutes reprensted by 0-59
                var visibleHour = setHour
                if (visibleHour == 0){
                    visibleHour = 12
                }

                Toast.makeText(this@MainActivity, "Time Set to: " + visibleHour + ":" +  "%02d".format(setMinute), Toast.LENGTH_LONG).show()
            } catch (e: Exception){
                Toast.makeText(this@MainActivity, "Unexpected Input, Try Again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateClock(){
        runOnUiThread{
            binding.textViewMainTrueTime.text = "${SimpleDateFormat("hh:mm:ss").format(Date())}"
        }
    }

    private fun updateBackground(){
        var currentHour = Calendar.getInstance().get(10)
        var currentMinutes = Calendar.getInstance().get(12) + currentHour * 60
        var alarmMinutes = setMinute + setHour * 60
        var difference = alarmMinutes - currentMinutes
        runOnUiThread {
            if (difference in 0..60) {
                var scale = 1 - difference / 60.0
                binding.layoutMain.setBackgroundColor(
                    Color.rgb(
                        (255 * scale).toInt(),
                        (255 * scale).toInt(),
                        (255 * scale).toInt()
                    )
                )

            } else {
                binding.layoutMain.setBackgroundColor(Color.rgb(0, 0, 0))
            }
        }
    }
}