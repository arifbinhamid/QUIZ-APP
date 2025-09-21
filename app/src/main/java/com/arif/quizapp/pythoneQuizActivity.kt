package com.arif.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arif.quizapp.databinding.ActivityPythoneQuizBinding
import java.util.concurrent.TimeUnit

class pythoneQuizActivity : AppCompatActivity() {
    lateinit var binding: ActivityPythoneQuizBinding

    val quizList = listOf<Quiz>(

        Quiz(
            "Who developed the Python programming language?",
            "James Gosling",
            "Dennis Ritchie",
            "Guido van Rossum",
            "Bjarne Stroustrup",
            "Guido van Rossum"
        ),

        Quiz(
            "Which keyword is used to define a function in Python?",
            "function",
            "def",
            "func",
            "define",
            "def"
        ),

        Quiz(
            "What is the correct file extension for Python files?",
            ".pyt",
            ".pt",
            ".py",
            ".python",
            ".py"
        ),

        Quiz(
            "Which function is used to display output in Python?",
            "echo()",
            "print()",
            "write()",
            "output()",
            "print()"
        ),

        Quiz(
            "Which of the following is used to define a block of code in Python?",
            "Curly braces",
            "Semicolons",
            "Indentation",
            "Parentheses",
            "Indentation"
        ),

        Quiz(
            "What data type is the result of: type(5)?",
            "int",
            "str",
            "float",
            "type",
            "int"
        ),

        Quiz(
            "Which keyword is used for a conditional statement in Python?",
            "if",
            "switch",
            "case",
            "when",
            "if"
        ),

        Quiz(
            "How do you insert comments in Python code?",
            "// comment",
            "/* comment */",
            "<!-- comment -->",
            "# comment",
            "# comment"
        ),

        Quiz(
            "What does the 'len()' function do in Python?",
            "Adds two numbers",
            "Returns length of an object",
            "Prints a list",
            "Deletes an element",
            "Returns length of an object"
        ),

        Quiz(
            "Which data structure does not allow duplicate values in Python?",
            "List",
            "Dictionary",
            "Set",
            "Tuple",
            "Set"
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