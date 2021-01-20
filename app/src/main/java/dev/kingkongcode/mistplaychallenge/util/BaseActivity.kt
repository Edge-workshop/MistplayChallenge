package dev.kingkongcode.mistplaychallenge.util

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dev.kingkongcode.mistplaychallenge.util.network.ConnectionStateMonitor


/**
 * Deciding of creating a BaseActivity for the Network Connection ( i could directly put it on the HomeGamesCategoriesActivity
 * put i was thinking that it will be best this way when the app will grow and have multiple activities )
 * **/
open class BaseActivity : AppCompatActivity() {
    private lateinit var cs: ConnectionStateMonitor

    private companion object {
        const val TAG = "BaseActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Creating and initializing object
            cs = ConnectionStateMonitor()
            cs.connectionStateMonitor()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
        //Start network connection for Mobile Data
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cs.enable(this@BaseActivity)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
        //Stop network connection for Mobile Data
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) cs.disable(this@BaseActivity)
    }
}