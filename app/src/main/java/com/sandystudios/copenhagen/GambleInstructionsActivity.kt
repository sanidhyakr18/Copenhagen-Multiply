package com.sandystudios.copenhagen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.properties.Delegates

class GambleInstructionsActivity : AppCompatActivity() {

    private val tvGambleMatter: TextView by lazy {
        findViewById(R.id.tv_gamble_matter)
    }

    private val btnOkayGamble: Button by lazy {
        findViewById(R.id.btn_okay_gamble)
    }

    private lateinit var name: String
    private lateinit var age: String
    private var finalScore = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamble_instructions)

        try {
            name = intent.getStringExtra(NAME)!!
            age = intent.getStringExtra(AGE)!!
            finalScore = intent.getFloatExtra(FINAL_SCORE, 0F)
        } catch (e: Exception) {
            Toast.makeText(this, "Number not found, Try Again!", Toast.LENGTH_SHORT).show()
        }

        btnOkayGamble.setOnClickListener {
            val intent = Intent(this, GambleActivity::class.java)
            intent.putExtra(NAME, name)
            intent.putExtra(AGE, age)
            intent.putExtra(FINAL_SCORE, finalScore)
            startActivity(intent)
            finish()
        }

        // TODO: Ask for instructions
        tvGambleMatter.text = """
            Introduction
            
            We are here to serve you at our best ability. So please read this and follow this document so that there will be no doubt regarding our services and their prices. These App Standard Terms and Conditions written on this webpage shall manage your use of this App. If you choose to proceed further, it will be taken as your consent to our terms and services mentioned in this document and elsewhere on this App.
            Other than the content you own, under these Terms, DeepBeej owns all the intellectual property rights and materials contained in this App.
            You are granted limited license only for purposes of viewing the material contained on this App. If you disagree by any of the terms on this page, then please do not participate in our business in any form, either as a seller or any business partner or any other role, with our products and/or services.
            
            Restrictions
            
            You are specifically restricted from all of the following
            • publishing any App material in any other media;
            • selling, sublicensing and/or otherwise commercializing any App material;
            • publicly performing and/or showing any App material;
            • using this App in any way that is or may be damaging to this App;
            • using this App in any way that impacts user access to this App;
            • using this App contrary to applicable laws and regulations, or in any way may cause harm to the App, or to any person or business entity;
            • engaging in any data mining, data harvesting, data extracting or any other similar activity in relation to this App;
            • using this App to engage in any advertising or marketing.
            
            No warranties
            
            The material contained on this App and on the associated web pages is general information and is not intended to be advice on any particular matter and cannot replace or substitute for the services of a trained professionals in any field, including, but not limited to, financial, medical, psychological, or legal matters. Subscribers and readers should seek appropriate professional advice before acting on the basis of any information contained herein. The App, its members, owners, employees, agents, representatives and the authors expressly disclaim any and all liability to any person, whether a subscriber or not, in respect of anything and of the consequences of anything done or omitted to be done by any such person in reliance upon the content of this App and associated web pages. This App is provided “as is,” with all faults, and expresses no representations or warranties, of any kind related to this App or the materials contained on this App.
            
            Limitation of liability
            
            In no event shall App, nor any of its officers, directors and employees, shall be held liable for anything arising out of or in any way connected with your use of this App whether such liability is under contract.
            
            Indemnification
            
            You hereby indemnify to the fullest extent from and against any and/or all liabilities, costs, demands, causes of action, damages and expenses arising in any way related to your breach of any of the provisions of these Terms.
            
            Severability
            
            If any provision of these Terms is found to be invalid under any applicable law, such provisions shall be deleted without affecting the remaining provisions herein.
            
            Variation of Terms
            
            www.deepaksinghkathait.com is permitted to revise these Terms at any time as it sees fit, and by using this App you are expected to review these Terms on a regular basis.
            
            Assignment
            
            The App is allowed to assign, transfer, and subcontract its rights and/or obligations under these Terms without any notification. However, you are not allowed to assign, transfer, or subcontract any of your rights and/or obligations under these Terms.
            
            Entire Agreement
            
            These Terms constitute the entire agreement between www.deepaksinghkathait.com and you in relation to your use of this App, and supersede all prior agreements and understandings.
            
            Governing Law & Jurisdiction
            
            These Terms will be governed by and interpreted in accordance with the laws of the State of INDIA, and you submit to the non-exclusive jurisdiction of the state and federal courts located in saket for the resolution of any disputes.
            
            Content by Mr. DeepBeej in any form is not for sale, by registering for the seminar through App or any other affiliate you agree to abide by the rules and regulations for the seminar and above stated terms of use.
            """.trimIndent()
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                MainActivity.h.sendEmptyMessage(0)
                this@GambleInstructionsActivity.finish()
            }
            .setNegativeButton("No", null)
            .show()
    }
}