package com.github.flashtimer01

import android.content.Context
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.github.flashtimer01.databinding.ActivityMainBinding
import android.widget.Chronometer

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val chronometer:Chronometer = findViewById<Chronometer>(R.id.testTime)

        var nowLevel :Int = 1
        var hasCosmicInsight :Int = 0
        var hasIonianBoots :Int = 0
        var isRunning = false

        binding.gameTime.text = "3:00"

        binding.buttonFlash.setOnClickListener {
            val txt = binding.timeFlash.text
            binding.timeFlash.text = "5:00"
        }
        binding.buttonEnemyLevelPlus.setOnClickListener {
            val num = enemyLevelCalc("plus",nowLevel)
            //val txt = binding.enemyLevelCount.text
            binding.enemyLevelCount.text = num.toString()
            nowLevel = num
        }
        binding.buttonEnemyLevelMinus.setOnClickListener {
            val num = enemyLevelCalc("minus",nowLevel)
            //val txt = binding.enemyLevelCount.text
            binding.enemyLevelCount.text = num.toString()
            nowLevel = num
        }
        binding.buttonIonianBoots.setOnClickListener {
            if (hasIonianBoots == 1) {
                hasIonianBoots = 0
                binding.buttonIonianBoots.setImageResource(R.drawable.ionia_boots_dark)
            }else{
                hasIonianBoots = 1
                binding.buttonIonianBoots.setImageResource(R.drawable.ionia_boots)
            }

        }
        binding.buttonCosmicInsight.setOnClickListener {
            if (hasCosmicInsight == 1) {
                hasCosmicInsight = 0
                binding.buttonCosmicInsight.setImageResource(R.drawable.cosmic_insight_dark)
            }else{
                hasCosmicInsight = 1
                binding.buttonCosmicInsight.setImageResource(R.drawable.cosmic_insight)
            }

        }
        binding.buttonGameStart.setOnClickListener {
            isRunning = when(isRunning){
                true -> {
                    chronometer.stop()
                    Toast.makeText(applicationContext , "stop", Toast.LENGTH_SHORT).show()
                    false
                }
                false -> {
                    chronometer.start()
                    Toast.makeText(applicationContext , "start", Toast.LENGTH_SHORT).show()
                    true
                }

            }
            chronometer.start()
        }
    }
    private fun enemyLevelCalc(opSign : String,nowLevelFun:Int): Int {
        var number:Int = nowLevelFun
        if(opSign == "plus"){
            if (number <=17) {
                number += 1
                return number
            }else{
                Toast.makeText(applicationContext , "Level Max", Toast.LENGTH_SHORT).show()
                return number
            }
        }else{
            if (number >=2) {
                number -= 1
                return number
            }else {
                Toast.makeText(applicationContext , "Level Min", Toast.LENGTH_SHORT).show()
                return number
            }
        }
    }
}