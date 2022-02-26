package com.sandystudios.copenhagen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.*

class MainActivity : AppCompatActivity() {

    private val tvTCE: TextView by lazy {
        findViewById(R.id.tv_TCE)
    }

    private val etName: EditText by lazy {
        findViewById(R.id.et_name)
    }

    private val etAge: EditText by lazy {
        findViewById(R.id.et_age)
    }

    private val checkBox: CheckBox by lazy {
        findViewById(R.id.checkBox)
    }

    private val btnStart: Button by lazy {
        findViewById(R.id.btn_start)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spannable = SpannableString("TCE.")
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.primaryBlue)),
            3, // start
            4, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        tvTCE.text = spannable

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            btnStart.isEnabled = isChecked
        }
    }
}