package com.example.simulator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.simulator.databinding.ActivityEntryPageBinding

class EntryPage : AppCompatActivity() {
    var binding:ActivityEntryPageBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val sharedRef: SharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val aniSwipe: Animation = AnimationUtils.loadAnimation(this, R.anim.swipe)
        binding?.imSwipeCard?.startAnimation(aniSwipe)
        val aniInsert: Animation = AnimationUtils.loadAnimation(this, R.anim.insert)
        binding?.imInsertCard?.startAnimation(aniInsert)
        val aniTap: Animation = AnimationUtils.loadAnimation(this, R.anim.tap)
        binding?.imnfcCard?.startAnimation(aniTap)
        val amt: String? =  sharedRef.getString("ActualAmt", null)
        binding?.imSwipe?.setOnClickListener{
            val editor: SharedPreferences.Editor = sharedRef.edit()
            editor.apply{
                putString("mode","SWIPE")
            }.apply()
            startActivity(Intent(this,PinEntry::class.java))
            finish()
        }
        binding?.imInsert?.setOnClickListener{
            val editor: SharedPreferences.Editor = sharedRef.edit()
            editor.apply{
                putString("mode","CHIP")
            }.apply()
            startActivity(Intent(this,PinEntry::class.java))
            finish()
        }
        binding?.imnfc?.setOnClickListener{
            val editor: SharedPreferences.Editor = sharedRef.edit()
            editor.apply{
                putString("mode","NFC")
            }.apply()
            if(amt?.toInt()!! >= 200000)
            {
                startActivity(Intent(this,PinEntry::class.java))
                finish()
            }
            if(amt.toInt() < 200000)
            {
                startActivity(Intent(this,PaymentProcessingActivity::class.java))
                finish()
            }
        }
        binding?.Manual?.setOnClickListener {
            startActivity(Intent(this,PinEntry::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}