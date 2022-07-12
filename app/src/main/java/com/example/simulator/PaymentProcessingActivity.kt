package com.example.simulator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.simulator.databinding.ActivityPaymentProcessingBinding


class PaymentProcessingActivity : AppCompatActivity() {
    private var binding: ActivityPaymentProcessingBinding? = null
    private lateinit var amt: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentProcessingBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val pin: String? = loadData().toString()  ///// code to change
        if(pin == "1")
        {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this,PaymentSuccessActivity::class.java))
                finish()
            }, 5000)
        }
        if(pin == "4")
        {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this,PaymentFailureActivity::class.java).putExtra("Amount",amt))
                finish()
            }, 5000)
        }
        else{
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, PaymentSuccessActivity :: class.java))
                finish()
            }, 5000)
        }
    }
    private fun loadData(): String? {
        val sharedRef: SharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        return sharedRef.getString("Pin", null)
    }
    override fun onBackPressed() {

    }
}