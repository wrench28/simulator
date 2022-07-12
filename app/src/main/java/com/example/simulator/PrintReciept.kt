package com.example.simulator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils.loadAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.simulator.databinding.ActivityPrintRecieptBinding

class PrintReciept : AppCompatActivity() {
    private var binding: ActivityPrintRecieptBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPrintRecieptBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
        loadData()
        val amt: String? = loadData()
        binding?.btnMerchantCopy?.setOnClickListener {
            move()
        }
        Log.d("D",amt.toString())
        binding?.price?.text = amt.toString()
    }
    fun move() {
        val animation1= loadAnimation(this,com.example.simulator.R.anim.move)
        binding?.cardView?.startAnimation(animation1)
    }
    private fun loadData(): String? {
        val sharedRef: SharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        return sharedRef.getString("PrintAmt", null)
    }

    override fun onBackPressed() {
        val sharedrefs: SharedPreferences = getSharedPreferences("sharedprefs", MODE_PRIVATE)
        sharedrefs.edit().clear().commit()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}