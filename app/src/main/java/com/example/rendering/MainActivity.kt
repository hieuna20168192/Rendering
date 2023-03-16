package com.example.rendering

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Trace
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
//        Trace.beginAsyncSection("TFFD->onCreate", 100)
        super.onCreate(savedInstanceState)
        val current = System.currentTimeMillis()
//        Trace.beginSection("TFID -> onCreate")
        setContentView(R.layout.activity_main)
//
//        findViewById<TextView>(R.id.tvHello).setOnClickListener {
////            spinLock(2000)
////            Trace.endAsyncSection("TFFD->onCreate", 100)
//        }
////        Handler().postDelayed({
////            Trace.endAsyncSection("TFFD->onCreate", 100)
////        }, 2000)
//        Log.d("TFID", "manually -> ${System.currentTimeMillis() - current}")
//        Trace.endSection()

        findViewById<Toolbar>(R.id.top_toolbar).setOnMenuItemClickListener {
            if (it.itemId == R.id.menu_item_refresh) {
                (supportFragmentManager.findFragmentByTag("EcommerceFragment") as EcommerceFragment).refresh()
            }
            true
        }

        supportFragmentManager.beginTransaction().replace(R.id.main_container, EcommerceFragment(), "EcommerceFragment")
            .addToBackStack("EcommerceFragment")
            .commit()
    }

    private fun spinLock(time: Long) {
//        val current = System.currentTimeMillis()
//        while (System.currentTimeMillis() - current <= time) {
//
//        }
        Toast.makeText(this, "spinLock finished", Toast.LENGTH_SHORT).show()
    }

}