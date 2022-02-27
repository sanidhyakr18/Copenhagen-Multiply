package com.sandystudios.copenhagen

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs


class ExperimentActivity : AppCompatActivity() {

    private lateinit var name: String
    private lateinit var age: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experiment)

        try {
            name = intent.getStringExtra(NAME)!!
            age = intent.getStringExtra(AGE)!!
        } catch (e: Exception) {
            Toast.makeText(this, "Number not found, Try Again!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                MainActivity.h.sendEmptyMessage(0);
                this@ExperimentActivity.finish()
            })
            .setNegativeButton("No", null)
            .show()
    }
}