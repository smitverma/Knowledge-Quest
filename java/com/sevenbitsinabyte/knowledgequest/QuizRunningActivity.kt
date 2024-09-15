package com.sevenbitsinabyte.knowledgequest

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://dummyjson.com/"
val questionsList = mutableListOf<String>()
val optionsList = mutableListOf<List<String>>()
val answerList = mutableListOf<String>()

private lateinit var titleText : TextView
private lateinit var questionText : TextView

class QuizRunningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_running)

        //<editor-fold desc="findViewbyIDs">
        val topCard = findViewById<CardView>(R.id.running_topCard)
        val quizCard = findViewById<CardView>(R.id.running_quizCard)
        val picCard = findViewById<ImageView>(R.id.running_picCard)
        titleText = findViewById<TextView>(R.id.running_titleText)
        questionText = findViewById<TextView>(R.id.running_questionText)
        //</editor-fold>

        //<editor-fold desc="Theme Config">
        val theme = intent.getStringExtra("theme")

        if (theme == "geo") {
            topCard.setCardBackgroundColor(resources.getColor(R.color.green))
            quizCard.setCardBackgroundColor(resources.getColor(R.color.green))
            picCard.setImageResource(R.drawable.img_geo1)
            titleText.setTextColor(resources.getColor(R.color.white))
            questionText.setTextColor(resources.getColor(R.color.white))
            titleText.text = "Geograpny"
        } else if (theme == "chem") {
            topCard.setCardBackgroundColor(resources.getColor(R.color.yellow))
            quizCard.setCardBackgroundColor(resources.getColor(R.color.yellow))
            picCard.setImageResource(R.drawable.img_chem1)
            titleText.setTextColor(resources.getColor(R.color.black))
            questionText.setTextColor(resources.getColor(R.color.black))
            titleText.text = "Chemistry"
        } else if (theme == "phy") {
            topCard.setCardBackgroundColor(resources.getColor(R.color.red))
            quizCard.setCardBackgroundColor(resources.getColor(R.color.red))
            picCard.setImageResource(R.drawable.img_phy1)
            titleText.setTextColor(resources.getColor(R.color.white))
            questionText.setTextColor(resources.getColor(R.color.white))
            titleText.text = "Physics"
        } else if (theme == "astro") {
            topCard.setCardBackgroundColor(resources.getColor(R.color.spaceblue))
            quizCard.setCardBackgroundColor(resources.getColor(R.color.spaceblue))
            picCard.setImageResource(R.drawable.img_astro1)
            titleText.setTextColor(resources.getColor(R.color.white))
            questionText.setTextColor(resources.getColor(R.color.white))
            titleText.text = "Astronomy"
        }

        findViewById<ImageButton>(R.id.running_back).setOnClickListener {
            val intent = Intent(this, QuizStarterActivity::class.java)
            startActivity(intent)
        }
        //</editor-fold>

        checkCachedData()

    }

    private fun checkCachedData() {
        getUpdatedData()
    }

    private fun getUpdatedData() {
        val retrofitBuilder =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(getQuestionsInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<Question>?> {
            override fun onResponse(
                call: Call<List<Question>?>,
                response: Response<List<Question>?>
            ) {
                val responseBody = response.body()!!

                for (myData in responseBody) {
                    questionsList.add(myData.question)
                    optionsList.add(myData.options)
                    answerList.add(myData.answer)
                }
                questionText.text = questionsList[0]
            }

            override fun onFailure(call: Call<List<Question>?>, t: Throwable) {
                Toast.makeText(applicationContext, "Data loading failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {

        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("audioPlaying", false)
        editor.apply()

        super.onDestroy()
    }
}