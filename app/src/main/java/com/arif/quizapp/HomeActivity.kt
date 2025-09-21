package com.arif.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.arif.quizapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kotlin.setOnClickListener {

            val intent = Intent(this,kotlinQuizActivity::class.java)
            startActivity(intent)
        }

        binding.javaPlay.setOnClickListener {
            val intent = Intent(this,javaQuizActivity::class.java)
            startActivity(intent)

        }
        binding.flutterPlay.setOnClickListener {
            val intent = Intent(this,flutterQuizActivity::class.java)
            startActivity(intent)
        }
        binding.pythonPlay.setOnClickListener {
            val intent = Intent(this,pythoneQuizActivity::class.java)
            startActivity(intent)
        }

    }

}




