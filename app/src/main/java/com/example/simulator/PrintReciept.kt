package com.example.simulator

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils.loadAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.simulator.databinding.ActivityPrintRecieptBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class PrintReciept : AppCompatActivity() {
    private var binding: ActivityPrintRecieptBinding? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPrintRecieptBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
        val df = SimpleDateFormat("dd-MM-yyyy")
        val tf = SimpleDateFormat("HH:mm:ss")
        val currentDate = df.format(Date())
        val currentTime = tf.format(Date())
        loadData()
        val amt: String? = loadData()
        binding?.btnMerchantCopy?.setOnClickListener {
            binding?.msg4?.text = "****Merchant copy****\nTHANKS ... VISIT AGAIN"
            moveUp()
            Timer().schedule(2900) {
                moveRight()
            }

        }
        binding?.btnBothCopy?.setOnClickListener {
            binding?.msg4?.text = "****Merchant copy****\nTHANKS ... VISIT AGAIN"
            moveUp()
            Handler(Looper.getMainLooper()).postDelayed({
                binding?.msg4?.text = "****Customer copy****\nTHANKS ... VISIT AGAIN"
                moveDownUp()
                Handler(Looper.getMainLooper()).postDelayed({
                    alertBox()
                }, 1500)
            }, 3000)
        }
        binding?.btnCustomerCopy?.setOnClickListener {
            binding?.msg4?.text = "****Customer copy****\nTHANKS ... VISIT AGAIN"
            moveUp()
            Timer().schedule(2900) {
                moveLeft()
            }
        }
        binding?.price?.text = "Rs " + amt.toString()
        binding?.mAddress?.text = "Del pero heights, Los santos"
        binding?.date?.text = "DATE: $currentDate"
        binding?.Time?.text = "TIME: $currentTime"
        binding?.mid?.text = "MID: Wrench industries"
        binding?.tid?.text = "TID: W01"
        binding?.batch?.text = "BATCH: 001"
        binding?.BR?.text = "BR: 20220712001"
        binding?.invoice?.text = "INVOICE: 001"
        binding?.cardNumber?.text = "Card Number: **** **** **** 4321"
        binding?.cardType?.text = "CARD TYPE: VISA"
        binding?.authCode?.text = "AUTH CODE: 000001"
        binding?.entryMode?.text = "MODE: NFC"
        binding?.RRN?.text = "RRN: 000000123456"

    }
    private fun alertBox()
    {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Print Reciept")
        alert.setMessage("Print Customer Copy? ?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener(function = positive))
        alert.setNegativeButton("No", DialogInterface.OnClickListener(function = negative))
        alert.show()
    }
    private val positive = { _: DialogInterface, _: Int ->
        moveUL()
    }
    private val negative = { dialog: DialogInterface, _: Int ->
        dialog.dismiss()
    }
    private fun moveUL() {
        moveUp()
        Timer().schedule(3000) {
            moveDownUp()
        }
    }
    private fun moveUp() {
        val animation1 = loadAnimation(this, R.anim.move_up)
        binding?.cardView?.startAnimation(animation1)
    }
    private fun moveRight() {
        val animation1 = loadAnimation(this, R.anim.move_right)
        binding?.cardView?.startAnimation(animation1)
    }
    private fun moveLeft() {
        val animation1 = loadAnimation(this, R.anim.move_left)
        binding?.cardView?.startAnimation(animation1)
    }
    private fun moveDownUp() {
        val animation1 = loadAnimation(this, R.anim.move_downup)
        binding?.cardView?.startAnimation(animation1)
    }
    private fun loadData(): String? {
        val sharedRef: SharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        return sharedRef.getString("PrintAmt", null)
    }

    override fun onBackPressed() {
        val sharedrefs: SharedPreferences = getSharedPreferences("sharedprefs", MODE_PRIVATE)
        sharedrefs.edit().clear().commit()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}