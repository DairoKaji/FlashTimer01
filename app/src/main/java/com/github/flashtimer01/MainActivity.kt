package com.github.flashtimer01

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.flashtimer01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var nowLevel :Int = 1
        var hasCosmicInsight :Int = 0
        var hasIonianBoots :Int = 0
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonFlash.setOnClickListener {
            val txt = binding.timeFlash.text
            binding.timeFlash.text = "5:00"
        }
        binding.buttonEnemyLevelPlus.setOnClickListener {
            val num = enemyLevelCalc("plus",nowLevel)
            val txt = binding.enemyLevelCount.text
            binding.enemyLevelCount.text = num.toString()
            nowLevel = num
        }
        binding.buttonEnemyLevelMinus.setOnClickListener {
            val num = enemyLevelCalc("minus",nowLevel)
            val txt = binding.enemyLevelCount.text
            binding.enemyLevelCount.text = num.toString()
            nowLevel = num
        }
        binding.buttonCosmicInsight.setOnClickListener {
            if (hasCosmicInsight == 1) {
                hasCosmicInsight = 0
                val img = binding.buttonIonianBoots.setImageResource(R.drawable.ionia_boots_dark)
            }else{
                hasCosmicInsight = 1
                val img = binding.buttonIonianBoots.setImageResource(R.drawable.ionia_boots)
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