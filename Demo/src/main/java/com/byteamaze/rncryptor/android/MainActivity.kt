package com.byteamaze.rncryptor.android

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.util.Base64
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.cryptonode.jncryptor.AES256JNCryptor
import org.cryptonode.jncryptor.CryptorException
import java.nio.charset.Charset


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val text = edittext.text.toString()
            if (!text.isNullOrBlank()) {
                val start = SystemClock.uptimeMillis()
                val result = encryptText(text)
                val end = SystemClock.uptimeMillis()
                val original = decryptText(result!!)
                textview.text = "encrypt cost ${end - start}ms\n\nresult:\n$result\n\noriginal:\n$original"
            } else {
                Toast.makeText(this,
                        "input some text.",
                        Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun encryptText(text: String): String? {
        val cryptor = AES256JNCryptor()
        val plaintext = text.toByteArray()
        val password = "secretsquirrel"

        try {
            return Base64.encodeToString(cryptor.encryptData(
                    plaintext, password.toCharArray()), Base64.NO_WRAP)
        } catch (e: CryptorException) {
            // Something went wrong
            e.printStackTrace()
        }

        return null
    }

    private fun decryptText(text: String): String? {
        val cryptor = AES256JNCryptor()
        val plaintext = Base64.decode(text, Base64.NO_WRAP)
        val password = "secretsquirrel"

        try {
            return cryptor.decryptData(
                    plaintext!!, password.toCharArray()).toString(Charset.defaultCharset())
        } catch (e: CryptorException) {
            // Something went wrong
            e.printStackTrace()
        }

        return null
    }

}