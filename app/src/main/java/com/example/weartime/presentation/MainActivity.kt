package com.example.weartime.presentation

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.example.weartime.R
import com.example.weartime.presentation.theme.WearTimeTheme
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Calendar
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)
        setContentView(R.layout.layout)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val localTimeT = findViewById<TextView>(R.id.localTime)
        val addBtn = findViewById<ImageButton>(R.id.plusButton)


        fun vibration(vbTime: Long){
            if (vibrator.hasVibrator()) { // Vibrator availability checking
                vibrator.vibrate(VibrationEffect.createOneShot(vbTime, VibrationEffect.DEFAULT_AMPLITUDE))
            }
        }

        fun addGlobalTime() {

        }

        addBtn.setOnClickListener {
            vibration(10)
            addGlobalTime()
        }

        updateTime(localTimeT)
    }

    val handler = Handler()

    private fun updateTime(localTimeT: TextView) {
        handler.post(object : Runnable {
            override fun run() {
                updateCurTime(localTimeT)
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun updateCurTime(localTimeT: TextView) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val second = c.get(Calendar.SECOND)

        localTimeT.text = String.format("%02d:%02d:%02d", hour, minute, second)
    }
}