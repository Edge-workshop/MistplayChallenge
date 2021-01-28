package dev.kingkongcode.mistplaychallenge.util.network

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import dev.kingkongcode.mistplaychallenge.R

/**
 * I decided to create that class just in case is user try to use app with no connection. A dialog
 * box will appear and advise him that he need to be connected
 * **/
//Class that will detect connection state of device.
open class ConnectionStateMonitor : ConnectivityManager.NetworkCallback() {
    private lateinit var networkRequest: NetworkRequest
    private lateinit var dialog: Dialog
    private lateinit var mActivity: Activity

    private companion object {
        const val TAG = "ConnectionStateMonitor"
    }

    fun connectionStateMonitor() {
        //Building network request object and connection type status (to listen to)
        networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
    }

    fun enable(activity: Activity) {
        Log.i(TAG,"is enable")
        mActivity = activity

        //Initiate connectivity manager to listen status change
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest, this)

        //Creating dialog box for error message
        dialog = Dialog(activity)
        dialog.apply {
            setContentView(R.layout.no_internet_connection_msg_error)
            setTitle("Connection Lost")
            //I decided to put it false because i want it to force the user to be connected
            setCancelable(false)
        }
    }

    fun disable(activity: Activity) {
        Log.i(TAG,"is disable")
        //To unregister connectivity manager calls back
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(this)
    }

    override fun onAvailable(network: Network?) {
        Log.i(TAG,"is onAvailable")
        //Function is trigger when connection is available. If so i want to dismiss dialog
        if (dialog.isShowing) {
            mActivity.runOnUiThread {
                dialog.dismiss()
            }
        }
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Log.i(TAG,"is onLost")
        //Function is trigger when connection is lost and show dialog
        mActivity.runOnUiThread {
            dialog.show()
        }
    }

    override fun onUnavailable() {
        super.onUnavailable()
        Log.i(TAG,"is onUnavailable")
        //Function is trigger when connection is lost and show dialog
        dialog.show()
    }
}