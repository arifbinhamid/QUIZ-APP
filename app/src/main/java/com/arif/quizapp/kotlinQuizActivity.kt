package com.arif.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arif.quizapp.databinding.ActivityKotlinQuizBinding
import java.util.concurrent.TimeUnit

class kotlinQuizActivity : AppCompatActivity() {
    lateinit var binding: ActivityKotlinQuizBinding

    val quizlist = listOf<Quiz>(

        Quiz(
            "Who developed the Kotlin programming language?",
            "JetBrains",
            "Google",
            "Microsoft",
            "Oracle",
            "JetBrains"
        ),
        Quiz(
            "Which keyword is used to declare a read-only variable in Kotlin?",
            "var",
            "val",
            "let",
            "const",
            "val"
        ),
        Quiz(
            "What is the correct file extension for Kotlin files?",
            ".kot",
            ".kotlin",
            ".kt",
            ".kts",
            ".kt"
        ),
        Quiz(
            "Which function is used to output text in Kotlin?",
            "echo()",
            "System.out.println()",
            "write()",
            "println()",
            "println()"
        ),

        Quiz(
            "Which symbol is used to declare a nullable variable?",
            "&",
            "#",
            "?",
            "!",
            "?"
        ),

        Quiz(
            "What is the default visibility modifier in Kotlin?",
            "internal",
            "private",
            "protected",
            "public",
            "public"
        ),

        Quiz(
            "Which Kotlin collection is immutable by default?",
            "ArrayList",
            "MutableList",
            "HashMap",
            "List",
            "List"
        ),

        Quiz(
            "What is a primary constructor in Kotlin?",
            "A constructor in the init block",
            "A constructor with default values",
            "A constructor defined in the class header",
            "A constructor inside a method",
            "A constructor defined in the class header"
        ),

        Quiz(
            "What does the Elvis operator (?:) do in Kotlin?",
            "Checks null and provides default value",
            "Compares two numbers",
            "Throws an error",
            "Loops through a list",
            "Checks null and provides default value"
        ),

        Quiz(
            "Which feature allows avoiding NullPointerException in Kotlin?",
            "Smart Cast",
            "Safe Call Operator",
            "Type Alias",
            "Data Class",
            "Safe Call Operator"
        )

        )
    var updatequestionCount = 1
    var CountDownTimer:CountDownTimer?=null
    var milliSecond = 3000L
    var index = 0
    var hasFinished = false
    var skip = 0
    var correctAnswer = 0
    var wrongAnswer = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityKotlinQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countTV.text = "Count: $updatequestionCount/${quizlist.size}"
        startCountDownTimer()
        initQuestion()
        binding.nextBtn.setOnClickListener {

            nextQuestion()

        }




    }

    private fun nextQuestion() {
         checkAnswer()
        binding.apply {

            if(updatequestionCount<quizlist.size){
                updatequestionCount++
                countTV.text = "$updatequestionCount/${quizlist.size}"
            }
            if (index<=quizlist.size){

                initQuestion()
            }
            else{

            }
            radioGroup.clearCheck()
        }

        milliSecond = 3000L
        CountDownTimer?.cancel()
        startCountDownTimer()

    }

    private fun checkAnswer() {

        binding.apply {
            if(radioGroup.checkedRadioButtonId==-1){
                skip++

            }
            else{
                val checkButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                val checkAnswer = checkButton.text.toString()

                if (checkAnswer==quizlist[index].correctAnswer){
                    correctAnswer++
                    scoreTV.text = "Score: $correctAnswer"
                    CountDownTimer?.cancel()

                }
                else{
                    wrongAnswer++
                    CountDownTimer?.cancel()

                }
            }

        }

        if (index<quizlist.size -1){
             index++

        }
        else{

            val resultIntent = Intent(this@kotlinQuizActivity, ResultActivity::class.java)
            resultIntent.putExtra("score",correctAnswer)
            startActivity(resultIntent)

        }
    }

    private fun initQuestion() {
         val quiz = quizlist[index]
        binding.apply {
           questionTV.text = quiz.question
            option1.text = quiz.option1
            option2.text = quiz.option2
            option3.text = quiz.option3
            option4.text = quiz.option4
        }

    }

    private fun startCountDownTimer() {
        CountDownTimer = object : CountDownTimer(milliSecond, 1000){

            override fun onTick(millisUntilFinished: Long) {
                milliSecond = millisUntilFinished
                val second = TimeUnit.MILLISECONDS.toSeconds(milliSecond).toInt()
                binding.timeleftTV.text = "TimeLeft: $second"

            }


            override fun onFinish() {

            }


        }.start()

    }


}