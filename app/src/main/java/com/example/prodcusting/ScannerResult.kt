package com.example.prodcusting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class ScannerResult : AppCompatActivity() {
    lateinit var txthash:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_result)

        val b = intent.extras
        val hash = b!!.getString("hash")
        txthash = findViewById(R.id.txthash)
        txthash.text = hash.toString()
        sendRequest(hash.toString())
    }

    fun sendRequest(hash:String){
        val queue = Volley.newRequestQueue(this)
        val uri = "http://192.168.0.121/broadcasting/public/api/subscribe?hash=${hash}"

        val myReq = StringRequest(
            Request.Method.GET,
            uri,
            { response ->
                // Display the first 500 characters of the response string.
                Toast.makeText(this,"We Are Done", Toast.LENGTH_LONG).show()
                this.finish()
            },
            {error ->
//                Toast.makeText(this,"Request Failed", Toast.LENGTH_LONG).show()
                Log.d("requestfailed", "sendRequest: "+error)
            }
        )
        queue.add(myReq)
    }
}