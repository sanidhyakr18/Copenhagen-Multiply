package com.sandystudios.copenhagen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

const val NAME = "name"
const val AGE = "age"

class MainActivity : AppCompatActivity() {

    private val tvDecisive: TextView by lazy {
        findViewById(R.id.tv_decisive)
    }

    private val etName: EditText by lazy {
        findViewById(R.id.et_name)
    }

    private val etAge: EditText by lazy {
        findViewById(R.id.et_age)
    }

    private val tvConsent: TextView by lazy {
        findViewById(R.id.tv_consent)
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

        val spannable = SpannableString("Decisive.")
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.primaryBlue)),
            8, // start
            9, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        tvDecisive.text = spannable

        val str = "CLICK HERE"
        makeTextLink(tvConsent, str, true, getColor(R.color.primaryBlue))

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            btnStart.isEnabled = isChecked
        }

        btnStart.setOnClickListener {
            completeSetup()
        }
    }

    private fun completeSetup() {
        val name = etName.text.toString()
        val age = etAge.text.toString()
        when {
            name.isEmpty() -> Toast.makeText(this, "Name can not be empty!", Toast.LENGTH_SHORT).show()
            age.isEmpty() -> Toast.makeText(this, "Age can not be empty!", Toast.LENGTH_SHORT).show()
            else -> {
                btnStart.isEnabled = false
//                val uid = UUID.randomUUID().toString()
//                val user = User(uid, name, age, 1000, "")
                val intent = Intent(this, InstructionsActivity::class.java)
                intent.putExtra(NAME, name)
                intent.putExtra(AGE, age)
                startActivity(intent)
                checkBox.isChecked = false
            }
        }
    }

    private fun makeTextLink(
        textView: TextView,
        str: String,
        underlined: Boolean,
        color: Int?
    ) {
        val spannableString = SpannableString(textView.text)
        val textColor = color ?: textView.currentTextColor
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                startActivity(Intent(this@MainActivity, ConsentActivity::class.java))
            }

            override fun updateDrawState(drawState: TextPaint) {
                super.updateDrawState(drawState)
                drawState.isUnderlineText = underlined
                drawState.color = textColor
            }
        }
        val index = spannableString.indexOf(str)
        spannableString.setSpan(
            clickableSpan,
            index,
            index + str.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.highlightColor = Color.TRANSPARENT
    }
}