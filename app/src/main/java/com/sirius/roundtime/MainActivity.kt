package com.sirius.roundtime

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity


class MainActivity : AppCompatActivity() {



    lateinit var number_picker_second: NumberPicker


    lateinit var number_picker_dinlenme_second: NumberPicker

    lateinit var number_picker_round: NumberPicker

    lateinit var basla_btn: Button
     lateinit var layout_calisma :LinearLayout
lateinit var  text_baslik : TextView


    var total_zaman_dinlenme: Long = 0
    var total_zaman_calisma: Long = 0

    private var saniye_calisma: Long = 0
    var round_sayisi: Long = 1


    private var saniye_dinlenme: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


layout_calisma =findViewById(R.id.layout_calisma)

        // Get the background, which has been compiled to an AnimationDrawable object.
        val frameAnimation: AnimationDrawable = layout_calisma.background as AnimationDrawable

// Start the animation (looped playback by default).
        frameAnimation.start()




        number_picker_second = findViewById(R.id.number_picker_second)
        number_picker_dinlenme_second = findViewById(R.id.number_picker_dinlenme_second)
        number_picker_round = findViewById(R.id.number_picker_round)
        basla_btn = findViewById(R.id.basla_btn)



        number_picker_second.minValue = 0
        number_picker_second.maxValue = 59




        number_picker_dinlenme_second.minValue = 0
        number_picker_dinlenme_second.maxValue = 59


        number_picker_round.minValue = 1
        number_picker_round.maxValue = 5






        number_picker_second.setOnValueChangedListener { picker, oldVal, newValsec ->
            saniye_calisma = newValsec.toInt().toLong()
        }



        number_picker_dinlenme_second.setOnValueChangedListener { picker, oldVal, newValsec ->
            saniye_dinlenme = newValsec.toInt().toLong()
        }

        number_picker_round.setOnValueChangedListener { picker, oldVal, newValsec ->
            round_sayisi = newValsec.toInt().toLong()
        }









        basla_btn.setOnClickListener {



            var intent = Intent(this@MainActivity, Activity2::class.java)

            intent.putExtra("total_zaman_calisma", total_zaman_calisma)
            intent.putExtra("total_zaman_dinlenme", total_zaman_dinlenme)
            intent.putExtra("round_sayısı", round_sayisi)
            intent.putExtra("saniye_calisma", saniye_calisma)
            intent.putExtra("saniye_dinlenme", saniye_dinlenme)

            startActivity(intent)

        }
    }

}



