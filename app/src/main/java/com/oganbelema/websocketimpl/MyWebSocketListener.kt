package com.oganbelema.websocketimpl

import android.content.Context
import android.widget.TextView
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import okio.ByteString.Companion.decodeHex

class MyWebSocketListener(private val context: Context, private val textView: TextView): WebSocketListener() {

    companion object {
        private const val NORMAL_CLOSURE_STATUS = 100
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.send("Hello Belema")
        webSocket.send("deadbeef".decodeHex())
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        displayMessage("Receiving: $text")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        displayMessage("Receiving: ${bytes.hex()}")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(NORMAL_CLOSURE_STATUS, "Done")
        displayMessage("Closing: $code / $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        displayMessage("Error: ${t.localizedMessage}")
    }

    private fun displayMessage(message: String){
        doAsync {
            uiThread {
                textView.text = context.getString(R.string.message, textView.text, message)
            }
        }
    }
}