package com.example.simulator

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.simulator.databinding.ActivityPinEntryBinding

class PinEntry : AppCompatActivity() {
    private var binding:ActivityPinEntryBinding? = null
    var pin = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinEntryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val amt = loadData()
        timer.start()
        binding?.txtVPayableAmt?.text = binding?.txtVPayableAmt?.text.toString() + " Rs " + amt
        binding?.PinValue1?.setOnClickListener {
            pin += "1"
            pinLogic()
        }
        binding?.PinValue2?.setOnClickListener {
            pin += "2"
            pinLogic()
        }
        binding?.PinValue3?.setOnClickListener {
            pin += "3"
            pinLogic()
        }
        binding?.PinValue4?.setOnClickListener {
            pin += "4"
            pinLogic()
        }
        binding?.PinValue5?.setOnClickListener {
            pin += "5"
            pinLogic()
        }
        binding?.PinValue6?.setOnClickListener {
            pin += "6"
            pinLogic()
        }
        binding?.PinValue7?.setOnClickListener {
            pin += "7"
            pinLogic()
        }
        binding?.PinValue8?.setOnClickListener {
            pin += "8"
            pinLogic()
        }
        binding?.PinValue9?.setOnClickListener {
            pin += "9"
            pinLogic()
        }
        binding?.PinValue0?.setOnClickListener {
            pin += "0"
            pinLogic()
        }
        binding?.PinBackSpace?.setOnClickListener {
            try {
                pin = pin.subSequence(0,pin.length-1).toString()
            }
            catch (e : Exception){
                Toast.makeText(this,"Nothing to clear", Toast.LENGTH_SHORT).show()
            }

            if(pin.isEmpty())
            {
                binding?.txtVpin?.text = ""
            }
            if(pin.isNotEmpty())
            {
                pinLogic()
            }
        }
        binding?.CancelBtn?.setOnClickListener {
            alertBox()
        }
        binding?.PayBtn?.setOnClickListener {
            val intent = Intent(this,PaymentProcessingActivity::class.java)
            saveData()
            startActivity(intent)
        }

    }
    private fun pinLogic()
    {
        if(pin.length in 1..12)
        {
            binding?.txtVpin?.text = pin   // to do work
        }
        if(pin.length >=13)
        {
            Toast.makeText(this,"Pin should not exceed 12 digit",Toast.LENGTH_SHORT).show()
            pin = pin.subSequence(0,pin.length-1).toString()
            binding?.txtVpin?.text = pin
        }
    }
    private fun saveData()
    {
        val sharedRef: SharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedRef.edit()
        editor.apply{
            putString("Pin",binding?.txtVpin?.text?.toString())
        }.apply()
    }
    private fun loadData(): String? {
        val sharedRef: SharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        return sharedRef.getString("PrintAmt", null)
    }
     private fun alertBox()
    {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Alert")
        alert.setMessage("Are you sure you want to cancel this payment ?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener(function = positive))
        alert.setNegativeButton("No",DialogInterface.OnClickListener(function = negative))
        alert.show()
    }
    private val positive = { _: DialogInterface, _: Int ->
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        val sharedrefs: SharedPreferences = getSharedPreferences("sharedprefs", MODE_PRIVATE)
        sharedrefs.edit().clear().commit()
        finish()
    }
    private val negative = { dialog: DialogInterface, _: Int ->
        dialog.dismiss()
    }
    private fun alertBoxTimer()
    {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Alert")
        alert.setMessage("Did not recieve PIN")
        alert.setPositiveButton("Retry", DialogInterface.OnClickListener(function = positiveTimer))
        alert.setNegativeButton("Cancel",DialogInterface.OnClickListener(function = negativeTimer))
        alert.show()
    }
    private val positiveTimer = { _: DialogInterface, _: Int ->
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private val negativeTimer = { _: DialogInterface, _: Int ->
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        val sharedrefs: SharedPreferences = getSharedPreferences("sharedprefs", MODE_PRIVATE)
        sharedrefs.edit().clear().commit()
        finish()
    }
    private val timer = object: CountDownTimer(60000, 1000) {
        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            binding?.timer?.text = "Time Left: " + (millisUntilFinished/1000)
        }
        override fun onFinish() {
            alertBoxTimer()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    override fun onBackPressed() {
        alertBox()
    }
}