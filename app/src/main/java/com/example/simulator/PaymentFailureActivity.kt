package com.example.simulator

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.simulator.databinding.ActivityMainBinding
import com.example.simulator.databinding.ActivityPaymentFailureBinding

class PaymentFailureActivity : AppCompatActivity() {
    private var binding:ActivityPaymentFailureBinding? =null
    lateinit var amt:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentFailureBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        amt = intent.getStringExtra("Amount").toString()
        binding?.retry?.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("Amount",amt)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        alertBox()
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
        intent.putExtra("Amount",amt)
        startActivity(intent)
        finish()
    }
    private val negative = { dialog: DialogInterface, _: Int ->
        dialog.dismiss()
    }
}