package com.ally.devcat.mycalculadoraimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editWeight = findViewById<EditText>(R.id.edit_weight)
        val seekbarHight = findViewById<SeekBar>(R.id.seekbar_height)
        val buttonClear = findViewById<Button>(R.id.button_clear)
        val buttonCalculate = findViewById<Button>(R.id.button_calculate)
        val textResult = findViewById<TextView>(R.id.text_result)
        val textHeightValue = findViewById<TextView>(R.id.text_height_value)

        seekbarHight.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textHeightValue.text = getString(R.string.cm, p1)
                textHeightValue.visibility = TextView.VISIBLE
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        buttonCalculate.setOnClickListener {
            try {
                val weight = editWeight.text.toString().toDouble()
                val height = seekbarHight.progress.toDouble() / 100

                if (weight > 0 && height > 0) {
                    val imc = weight / (height * height)
                    textResult.text = String.format(getString(R.string.imc_2f), imc)
                    textResult.visibility = TextView.VISIBLE
                } else {
                    Toast.makeText(this, R.string.msg_weight, Toast.LENGTH_SHORT).show()
                }

            } catch (e: NumberFormatException) {
                Toast.makeText(this, R.string.msg_height, Toast.LENGTH_SHORT).show()
            }
        }

        buttonClear.setOnClickListener {
            editWeight.setText("")
            seekbarHight.progress = 0
            textHeightValue.visibility = TextView.GONE
            textResult.visibility = TextView.GONE
        }
    }
}