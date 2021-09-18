package com.github.flashtimer01

import android.content.Context
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.github.flashtimer01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    inner class GameCountDownTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        var isRunning = false

        override fun onTick(millisUntilFinished: Long){
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.gameTime.text = "%1d:%2$02d".format(minute ,second)
        }

        override fun onFinish() {
            binding.gameTime.text = "0:00"
        }
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val timer = GameCountDownTimer(3 * 60 * 1000, 100)
        var nowLevel :Int = 1
        var hasCosmicInsight :Int = 0
        var hasIonianBoots :Int = 0

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
            timer.isRunning = when (timer.isRunning) {
                true -> {
                    timer.cancel()
                    false
                }
                false -> {
                    timer.start()
                    true
                }

            }
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