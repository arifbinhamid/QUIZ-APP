package com.arif.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arif.quizapp.databinding.ActivityJavaQuizBinding
import java.util.concurrent.TimeUnit

class javaQuizActivity : AppCompatActivity() {

    lateinit var binding: ActivityJavaQuizBinding
    val quizList = listOf<Quiz>(

        Quiz(
            "Who invented the Java programming language?",
            "Guido van Rossum",
            "James Gosling",
            "Bjarne Stroustrup",
            "Dennis Ritchie",
            "James Gosling"
        ),

        Quiz(
            "What is the extension of Java source code files?",
            ".js",
            ".java",
            ".class",
            ".jav",
            ".java"
        ),

        Quiz(
            "Which method is the entry point of a Java program?",
            "start()",
            "run()",
            "main()",
            "init()",
            "main()"
        ),

        Quiz(
            "Which keyword is used to create a subclass in Java?",
            "super",
            "this",
            "extends",
            "inherits",
            "extends"
        ),

        Quiz(
            "Which concept allows you to reuse code in Java?",
            "Abstraction",
            "Inheritance",
            "Encapsulation",
            "Polymorphism",
            "Inheritance"
        ),

        Quiz(
            "Which of the following is not a primitive data type in Java?",
            "int",
            "float",
            "boolean",
            "String",
            "String"
        ),

        Quiz(
            "Which keyword is used to define a constant in Java?",
            "static",
            "final",
            "const",
            "immutable",
            "final"
        ),

        Quiz(
            "Which exception is thrown when a divide by zero occurs?",
            "IOException",
            "ArithmeticException",
            "NullPointerException",
            "NumberFormatException",
            "ArithmeticException"
        ),

        Quiz(
            "Which of the following is used to handle exceptions in Java?",
            "try-catch",
            "if-else",
            "switch-case",
            "throw-catch",
            "try-catch"
        ),

        Quiz(
            "Which package contains the Scanner class?",
            "java.util",
            "java.io",
            "java.lang",
            "java.net",
            "java.util"
        )
    )
    var updatequestionCount = 1
    var CountDownTimer: CountDownTimer?=null
    var milliSecond = 3000L
    var index = 0
    var hasFinished = false
    var skip = 0
    var correctAnswer = 0
    var wrongAnswer = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJavaQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.countTV.text = "Count: $updatequestionCount/${quizList.size}"
        startCountDownTimer()
        initQuestion()
        binding.nextBtn.setOnClickListener {

            nextQuestion()

    }
}
    private fun nextQuestion() {
        checkAnswer()
        binding.apply {

            if(updatequestionCount<quizList.size){
                updatequestionCount++
                countTV.text = "$updatequestionCount/${quizList.size}"
            }
            if (index<=quizList.size){

                initQuestion()
            }
            else{
                hasFinished = true
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

                if (checkAnswer==quizList[index].correctAnswer){
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

        if (index<quizList.size -1){
            index++
        }
        else{
            val intent = Intent(this,ResultActivity::class.java)
            startActivity(intent)

        }
    }

    private fun initQuestion() {

        val quiz = quizList[index]
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
