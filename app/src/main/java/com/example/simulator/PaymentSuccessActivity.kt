package com.example.simulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import com.example.simulator.databinding.ActivityPaymentProcessingBinding
import com.example.simulator.databinding.ActivityPaymentSuccessBinding

class PaymentSuccessActivity : AppCompatActivity() {
    private var binding:ActivityPaymentSuccessBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentSuccessBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, PrintReciept :: class.java))
        }, 3000)
    }

    override fun onBackPressed() {

    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}