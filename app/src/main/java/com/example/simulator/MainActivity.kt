package com.example.simulator

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var  arrAmt : String = ""
    private var amt: String? = ""
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        amt = loadData()
        if(amt?.isNotEmpty() == true)
        {
            amt = amt?.replace(".","")
            arrAmt = amt.toString()
            numlog()
        }
        binding?.PayValue1?.setOnClickListener {
            arrAmt += "1"
            numlog()
        }
        binding?.PayValue2?.setOnClickListener {
            arrAmt += "2"
            numlog()
        }
        binding?.PayValue3?.setOnClickListener {
            arrAmt += "3"
            numlog()
        }
        binding?.PayValue4?.setOnClickListener {
            arrAmt += "4"
            numlog()
        }
        binding?.PayValue5?.setOnClickListener {
            arrAmt += "5"
            numlog()
        }
        binding?.PayValue6?.setOnClickListener {
            arrAmt += "6"
            numlog()
        }
        binding?.PayValue7?.setOnClickListener {
            arrAmt += "7"
            numlog()
        }
        binding?.PayValue8?.setOnClickListener {
            arrAmt += "8"
            numlog()
        }
        binding?.PayValue9?.setOnClickListener {
            arrAmt += "9"
            numlog()
        }
        binding?.PayValue0?.setOnClickListener {
            if(arrAmt.isEmpty())
            {
                Toast.makeText(this,"Enter a value first",Toast.LENGTH_SHORT).show()
            }
            if(arrAmt.isNotEmpty())
            {
                arrAmt += "0"
                numlog()
            }

        }
        binding?.PayBackSpace?.setOnClickListener {
            try {
                arrAmt = arrAmt.subSequence(0,arrAmt.length-1).toString()
            }
            catch (e : Exception){
                Toast.makeText(this,"Nothing to clear", Toast.LENGTH_SHORT).show()
            }

            if(arrAmt.isEmpty())
            {
                binding?.txtVAmt?.text = "Rs 0.00"
            }
            if(arrAmt.isNotEmpty())
            {
                numlog()
            }
        }
        binding?.PayBackSpace?.setOnLongClickListener {
            arrAmt =  ""
            binding?.txtVAmt?.text = "Rs 0.00"
            true
        }
        binding?.CancelBtn?.setOnClickListener {
            alertBox()
        }
        binding?.EnterBtn?.setOnClickListener {
            if(arrAmt.isEmpty())
            {
                Toast.makeText(this,"Are you Kidding me.. ",Toast.LENGTH_SHORT).show()
            }
            if(arrAmt.isNotEmpty())
            {
                val intent = Intent(this,PinEntry::class.java)
                saveData()
                startActivity(intent)
                finish()
            }

        }
        binding?.PayValue00?.setOnClickListener {
            if(arrAmt.isEmpty())
            {
                Toast.makeText(this,"Enter a value first",Toast.LENGTH_SHORT).show()
            }
            if(arrAmt.isNotEmpty())
            {
                arrAmt += "00"
                numlog()
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun numlog()
    {
        if(arrAmt.length ==1)
        {
            binding?.txtVAmt?.text = "Rs 0.0$arrAmt"
        }
        if(arrAmt.length ==2)
        {
            binding?.txtVAmt?.text = "Rs 0.$arrAmt"
        }
        if(arrAmt.length == 3)
        {
            binding?.txtVAmt?.text = "Rs " + arrAmt[0] + "." + arrAmt[1] + arrAmt[2]
        }
        if(arrAmt.length in 4..12)
        {
            val len = arrAmt.length
            binding?.txtVAmt?.text = "Rs " + arrAmt.subSequence(0,len-2).toString() + "." + arrAmt.subSequence(len-2,len).toString()    // to do work
        }
        if(arrAmt.length >=13)
        {
            Toast.makeText(this,"Amount should not exceed 12 digit",Toast.LENGTH_SHORT).show()
            arrAmt = arrAmt.subSequence(0,arrAmt.length-1).toString()
        }
    }
    private fun saveData()
    {
        val sharedRef: SharedPreferences = getSharedPreferences("sharedprefs",Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedRef.edit()
        var amt = binding?.txtVAmt?.text?.toString()
        amt = amt?.replace("Rs ","")
        editor.apply{
            putString("PrintAmt", amt)
            putString("ActualAmt",arrAmt)
        }.apply()
    }
    private fun loadData(): String? {
        val sharedRef: SharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        return sharedRef.getString("PrintAmt", "")
    }
    private fun alertBox()
    {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Alert")
        alert.setMessage("Are you sure you want to close this application ?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener(function = positive))
        alert.setNegativeButton("No", DialogInterface.OnClickListener(function = negative))
        alert.show()
    }
    private val positive = { _: DialogInterface, _: Int ->
        finishAffinity()
    }
    private val negative = { dialog: DialogInterface, _: Int ->
        dialog.dismiss()
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onBackPressed() {
        alertBox()
    }
}