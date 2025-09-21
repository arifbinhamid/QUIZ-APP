package com.arif.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arif.quizapp.databinding.ActivityFlutterQuizBinding
import java.util.concurrent.TimeUnit

class flutterQuizActivity : AppCompatActivity() {
    lateinit var binding: ActivityFlutterQuizBinding

    val quizList = listOf<Quiz>(

        Quiz(
            "Who developed the Flutter framework?",
            "Google",
            "Facebook",
            "Microsoft",
            "Apple",
            "Google"
        ),

        Quiz(
            "What programming language is used to write Flutter apps?",
            "Java",
            "Kotlin",
            "Dart",
            "Swift",
            "Dart"
        ),

        Quiz(
            "Which widget is used for layout in Flutter?",
            "Container",
            "Scaffold",
            "Column",
            "All of the above",
            "All of the above"
        ),

        Quiz(
            "What command is used to create a new Flutter project?",
            "flutter init",
            "flutter start",
            "flutter create",
            "flutter new",
            "flutter create"
        ),

        Quiz(
            "Which widget is commonly used to make scrollable lists in Flutter?",
            "ListView",
            "GridView",
            "ScrollView",
            "PageView",
            "ListView"
        ),

        Quiz(
            "What is the entry point of a Flutter app?",
            "main() function",
            "start() function",
            "run() function",
            "init() function",
            "main() function"
        ),

        Quiz(
            "Which widget provides basic material design layout structure?",
            "AppBar",
            "Scaffold",
            "Container",
            "MaterialApp",
            "Scaffold"
        ),

        Quiz(
            "Which keyword is used to make widgets immutable in Flutter?",
            "StatefulWidget",
            "StatelessWidget",
            "ImmutableWidget",
            "FinalWidget",
            "StatelessWidget"
        ),

        Quiz(
            "Which tool is used for debugging and inspecting Flutter apps?",
            "Flutter Doctor",
            "Flutter Inspector",
            "Flutter Debugger",
            "Dart Fixer",
            "Flutter Inspector"
        ),

        Quiz(
            "Which widget is used to add padding around a child widget?",
            "Margin",
            "Padding",
            "Space",
            "Inset",
            "Padding"
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
