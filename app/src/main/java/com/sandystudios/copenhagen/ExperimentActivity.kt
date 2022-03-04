package com.sandystudios.copenhagen

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.imageview.ShapeableImageView


class ExperimentActivity : AppCompatActivity() {

    private val tvHeading: TextView by lazy {
        findViewById(R.id.tv_heading)
    }

    private val tvAmount: TextView by lazy {
        findViewById(R.id.tv_amount)
    }

    private val ivImage: ShapeableImageView by lazy {
        findViewById(R.id.iv_image)
    }

    private val btnNext: Button by lazy {
        findViewById(R.id.btn_next)
    }

    private val progressBar: ProgressBar by lazy {
        findViewById(R.id.progressBar)
    }

    private var isImage = false
    private var num = 0
    private var repeat = 0
    private var amount = 1000F

    private var mResources = intArrayOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5,
        R.drawable.img6,
        R.drawable.img7,
        R.drawable.img8,
        R.drawable.img9
    )

    private var hashMap: HashMap<Int, Float> = HashMap()

    private lateinit var name: String
    private lateinit var age: String

    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experiment)

        try {
            name = intent.getStringExtra(NAME)!!
            age = intent.getStringExtra(AGE)!!
        } catch (e: Exception) {
            Toast.makeText(this, "Data not found, Try Again!", Toast.LENGTH_SHORT).show()
        }

        hashMap[R.drawable.img1] = 0.45F
        hashMap[R.drawable.img2] = 0.55F
        hashMap[R.drawable.img3] = 0.67F
        hashMap[R.drawable.img4] = 0.82F
        hashMap[R.drawable.img5] = 1F
        hashMap[R.drawable.img6] = 1.22F
        hashMap[R.drawable.img7] = 1.5F
        hashMap[R.drawable.img8] = 1.83F
        hashMap[R.drawable.img9] = 2.24F

        mResources.shuffle()

        btnNext.isEnabled = false

//        addDelay()
        progressBar()

        btnNext.setOnClickListener {
            btnNext.isEnabled = false
            if (num == 9) {
                val intent = Intent(this, GambleInstructionsActivity::class.java)
                intent.putExtra(NAME, name)
                intent.putExtra(AGE, age)
                intent.putExtra(FINAL_SCORE, amount)
                startActivity(intent)
                finish()
            } else {
                isImage = !isImage

                if (!isImage) {
                    tvHeading.text = getString(R.string.new_amount)
                    tvAmount.text = amount.toInt().toString()
                    repeat++
                    if (repeat == 3) {
                        repeat = 0
                        num++
                    }
                } else {
                    tvHeading.text = getString(R.string.symbol)
                }

                tvAmount.isVisible = !isImage
                ivImage.isVisible = isImage
                if (isImage && num < 9) {
                    ivImage.setImageResource(mResources[num])
                    amount *= hashMap[mResources[num]]!!
//                    Log.d(
//                        TAG,
//                        "${mResources[num]} - ${hashMap[mResources[num]]} - ${amount.toInt()}"
//                    )
                }

                if (num == 9) {
                    tvHeading.text = "Final Amount"
                    btnNext.text = getString(R.string.finish)
                }
//                addDelay()
                progressBar()
            }
        }

    }

    private fun progressBar() {
        i = 0
        progressBar.progress = i
        val mCountDownTimer = object : CountDownTimer(5000, 10) {
            override fun onTick(millisUntilFinished: Long) {
//                Log.v("Log_tag", "Tick of Progress$i$millisUntilFinished")
                i++
                progressBar.progress = i * 100 / (5000 / 10)
            }

            override fun onFinish() {
                i++
                progressBar.progress = 100
                btnNext.isEnabled = true
            }
        }
        mCountDownTimer.start()
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                MainActivity.h.sendEmptyMessage(0)
                this@ExperimentActivity.finish()
            }
            .setNegativeButton("No", null)
            .show()
    }
}