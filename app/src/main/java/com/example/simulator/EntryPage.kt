package com.example.simulator

import android.content.Intent
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
        val aniSwipe: Animation = AnimationUtils.loadAnimation(this, R.anim.swipe)
        binding?.imSwipeCard?.startAnimation(aniSwipe)
        val aniInsert: Animation = AnimationUtils.loadAnimation(this, R.anim.insert)
        binding?.imInsertCard?.startAnimation(aniInsert)
        val aniTap: Animation = AnimationUtils.loadAnimation(this, R.anim.tap)
        binding?.imnfcCard?.startAnimation(aniTap)
        binding?.Manual?.setOnClickListener {
            startActivity(Intent(this,PinEntry::class.java))
            finish()
        }
    }
}