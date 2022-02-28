package com.sandystudios.copenhagen

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sandystudios.copenhagen.model.User
import java.util.*

class GambleActivity : AppCompatActivity() {

    private val btnSubmit: Button by lazy {
        findViewById(R.id.btn_submit)
    }

    private val radioGroup: RadioGroup by lazy {
        findViewById(R.id.radiogroup)
    }

    private lateinit var name: String
    private lateinit var age: String
    private var finalScore = 0F
    private lateinit var choiceOfGamble: String
    private var gambleScore = 0F

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamble)

        try {
            name = intent.getStringExtra(NAME)!!
            age = intent.getStringExtra(AGE)!!
            finalScore = intent.getFloatExtra(FINAL_SCORE, 0F)
        } catch (e: Exception) {
            Toast.makeText(this, "Number not found, Try Again!", Toast.LENGTH_SHORT).show()
        }

        btnSubmit.setOnClickListener {
            val radioButtonID = radioGroup.checkedRadioButtonId
            if (radioButtonID == -1) {
                Toast.makeText(this, "No gamble Selected!", Toast.LENGTH_SHORT).show()
            } else {
                val checkRadioButton = findViewById<RadioButton>(radioButtonID)
                choiceOfGamble = if (checkRadioButton.text == "Gamble (a)") "a" else "b"
                if (choiceOfGamble == "a") {
                    var i = 1F
                    for (j in 1..5) {
                        i *= 1.23F
                    }
                    gambleScore = finalScore * i
                } else {
                    var i = 1F
                    for (j in 1..5) {
                        i *= 1.008F
                    }
                    gambleScore = finalScore * i
                }
//                Log.d(TAG, "$radioButtonID")

                database = FirebaseDatabase.getInstance().getReference("Multiply")

                val uid = UUID.randomUUID().toString()
                val user = User(
                    uid,
                    name,
                    age.toInt(),
                    finalScore.toInt(),
                    choiceOfGamble,
                    gambleScore.toInt()
                )

                database.child(uid).setValue(user).addOnSuccessListener {
                    Toast.makeText(this, "Submitted Successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ThankYouActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Please check your Internet Connection and try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                MainActivity.h.sendEmptyMessage(0);
                this@GambleActivity.finish()
            }
            .setNegativeButton("No", null)
            .show()
    }
}