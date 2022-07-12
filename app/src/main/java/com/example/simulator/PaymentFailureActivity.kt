package com.example.simulator

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simulator.databinding.ActivityPaymentFailureBinding

class PaymentFailureActivity : AppCompatActivity() {
    private var binding:ActivityPaymentFailureBinding? =null
    lateinit var amt:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentFailureBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        amt = loadData().toString()
        binding?.retry?.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        alertBox()
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    private fun loadData(): String? {
        val sharedRef: SharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        return sharedRef.getString("PrintAmt", null)
    }
    private fun alertBox()
    {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Alert")
        alert.setMessage("Try again : sale amount : Rs $amt ?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener(function = positive))
        alert.setNegativeButton("No", DialogInterface.OnClickListener(function = negative))
        alert.show()
    }
    private val positive = { _: DialogInterface, _: Int ->
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private val negative = { dialog: DialogInterface, _: Int ->
        dialog.dismiss()
    }
}