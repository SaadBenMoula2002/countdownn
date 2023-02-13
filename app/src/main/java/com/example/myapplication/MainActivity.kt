package com.example.myapplication

import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var countdownTimer: CountDownTimer
    private var timeRemaining: Long = 0
    private var timerIsRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countdownTimer = object : CountDownTimer(25 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                timerIsRunning = false
                updateButtons()
            }
        }

        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            startTimer()
        }
        val stopButton = findViewById<Button>(R.id.stop_button)
        stopButton.setOnClickListener {
            stopTimer()
        }
    }

    private fun startTimer() {
        timerIsRunning = true
        updateButtons()
        countdownTimer.start()
    }

    private fun stopTimer() {
        timerIsRunning = false
        updateButtons()
        countdownTimer.cancel()
    }

    private fun updateTimerText() {
        val timerText = findViewById<TextView>(R.id.timer)
        val minutes = timeRemaining / 60000
        val seconds = timeRemaining % 60000 / 1000
        timerText.text = "$minutes:$seconds"
    }

    private fun updateButtons() {
        val startButton = findViewById<Button>(R.id.start_button)
        val stopButton = findViewById<Button>(R.id.stop_button)
        startButton.isEnabled = !timerIsRunning
        stopButton.isEnabled = timerIsRunning
    }

}

