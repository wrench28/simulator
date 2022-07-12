package com.example.simulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

class PaymentSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)
        val amt: String? = intent.getStringExtra("Amount")
        intent.putExtra("Amount",amt)
        Log.d("Dsuccess",amt.toString())
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, PrintReciept :: class.java))
        }, 3000)
    }

    override fun onBackPressed() {

    }
}