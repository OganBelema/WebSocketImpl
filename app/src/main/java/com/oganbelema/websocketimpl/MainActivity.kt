package com.oganbelema.websocketimpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class MainActivity : AppCompatActivity() {

    private lateinit var okHttpClient: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        okHttpClient = OkHttpClient()

        button.setOnClickListener {
            start()
        }
    }

    private fun start() {
        val request = Request.Builder().url("ws://echo.websocket.org").build()
        val webSocketListener = MyWebSocketListener(this, textView)
        val webSocket = okHttpClient.newWebSocket(request, webSocketListener)

        okHttpClient.dispatcher.executorService
    }
}
