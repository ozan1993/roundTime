package com.sirius.roundtime

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Activity2 : AppCompatActivity() {
    lateinit var text_view_round_sayisi: TextView
    lateinit var text_view_sayac: TextView
    lateinit var text_view_status: TextView


    lateinit var resume_btn: Button
    lateinit var start_btn: Button
    lateinit var restart_btn: Button

    private var resumeFromMillis: Long = 0
    var saniye_dinlenme : Long = 0
    var saniye_calisma :Long = 0
    var total_zaman_calisma: Long? = 0
    var total_zaman_dinlenme: Long? = 0
    var round_sayisi: Long? = 0
    var round: Long = 1

    var isCancelled = false

    //ben buraya yorum yaptım

    var isPaused = false
    var isRunning = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        var countDownInterval: Long = 1000

        text_view_round_sayisi = findViewById(R.id.text_view_round_sayisi)
        text_view_sayac = findViewById(R.id.text_view_sayac)
        text_view_status = findViewById(R.id.text_view_status)
        start_btn = findViewById(R.id.start_btn)
        restart_btn = findViewById(R.id.restart_btn)
        resume_btn = findViewById(R.id.resume_btn)


        val intent = intent.extras!!
        total_zaman_calisma = intent.getLong("total_zaman_calisma", 0)
        total_zaman_dinlenme = intent.getLong("total_zaman_dinlenme", 0)
        round_sayisi = intent.getLong("round_sayısı", 0)
        saniye_dinlenme = intent.getLong("saniye_dinlenme",0)
        saniye_calisma = intent.getLong("saniye_calisma",0)

        start_btn.setOnClickListener {

            if (isRunning) {


               var  millisInFuture = saniye_calisma!! * 1000
                timerCalisma(millisInFuture, countDownInterval).start()

                restart_btn.isEnabled = true
                resume_btn.isEnabled = false
                isCancelled = false
                isPaused = false
                isRunning = false
                start_btn.text = "pause"
            } else {
                isPaused = true
                isCancelled = false
                restart_btn.isEnabled = true
                resume_btn.isEnabled = true
                start_btn.isEnabled = false
                start_btn.text = "start"

                if (isCancelled) {

                    isCancelled = false
                    isPaused = false
                    start_btn.isEnabled = true
                    isRunning = true

                }


            }


        }

        restart_btn.setOnClickListener {
            isCancelled = true
            isPaused = false
            isRunning = true
            round = 1
            it.isEnabled = false
            start_btn.isEnabled = true

            saniye_calisma!!.toInt().toLong()
            var millisInFuture: Long = saniye_calisma!! * 1000
            timerCalisma(millisInFuture, countDownInterval).start()
        }

        resume_btn.setOnClickListener {

            timerCalisma(resumeFromMillis, countDownInterval).start()

            isPaused = false
            isCancelled = false


            it.isEnabled = false
            start_btn.isEnabled = true
            restart_btn.isEnabled = true
            start_btn.text = "pause"
        }

    }


    private fun timerCalisma(millisInFuture: Long, countDownInterval: Long): CountDownTimer {




        return object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {

                text_view_status.text = "RUN"
                text_view_round_sayisi.text = "$round round"

                var second: Long = (millisUntilFinished / 1000) % 60
                var min: Long = ((millisUntilFinished / 1000) - second) / 60



                if (isPaused) {
                    text_view_sayac.text = "${text_view_sayac.text}\nPaused"

                    resumeFromMillis = millisUntilFinished
                    cancel()
                } else if (isCancelled) {
                    text_view_sayac.text = "Time: 0"
                    cancel()
                    start_btn.text = "start"
                } else {
                    if (second < 10) {
                        text_view_sayac.text = "left time:   " + min + ":0" + second
                    } else {
                        text_view_sayac.text = "left time:   " + min + ":" + second

                    }
                }

            }

            override fun onFinish() {


                var millisInFuture = saniye_dinlenme!!*1000


                timerAraVerme(millisInFuture, countDownInterval).start()


            }
        }
    }


    private fun timerAraVerme(millisInFuture: Long, countDownInterval: Long): CountDownTimer {


        return object : CountDownTimer(millisInFuture, countDownInterval) {


            override fun onTick(millisUntilFinished: Long) {


                text_view_status.text = " Rest"
                text_view_round_sayisi.text = "round:$round"


                var second: Long = (millisUntilFinished / 1000) % 60
                var min: Long = ((millisUntilFinished / 1000) - second) / 60



                if (isPaused) {
                    text_view_sayac.text = "${text_view_sayac.text}\nPaused"

                    resumeFromMillis = millisUntilFinished
                    cancel()
                } else if (isCancelled) {
                    text_view_sayac.text = "Time: 0"
                    cancel()
                    start_btn.text = "start"
                } else {
                    if (second < 10) {
                        text_view_sayac.text = "left time:   " + min + ":0" + second
                    } else {
                        text_view_sayac.text = "left time:   " + min + ":" + second
                    }
                }

            }

            override fun onFinish() {


                round++


                if (round <= round_sayisi!!) {

                    var millisInFuture = saniye_calisma!!*1000
                    timerCalisma(millisInFuture, countDownInterval).start()
                }
                else text_view_sayac.text = "Tamamen Bitti"

            }
        }
    }

}





