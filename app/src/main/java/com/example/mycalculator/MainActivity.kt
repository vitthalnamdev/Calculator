package com.example.mycalculator

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.mariuszgromada.math.mxparser.Expression

import android.text.Editable

import androidx.core.content.ContextCompat
import com.example.mycalculator.databinding.ActivityMainBinding

import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var  binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonClear.setOnClickListener {
            binding.input.text = ""
            binding.output.text = ""
        }

        binding.buttonBracket.setOnClickListener {

            binding.input.text = addToInputText("(")

        }
        binding.buttonBracket.setOnClickListener {

           binding.input.text = addToInputText(")")

        }

        binding.buttonCroxx.setOnClickListener {
            val removedLast = binding.input.text.toString().dropLast(1)
            binding.input.text = removedLast
        }

        binding.button0.setOnClickListener {
            binding.input.text = addToInputText("0")
        }
        binding.button1.setOnClickListener {
            binding.input.text = addToInputText("1")
        }
        binding.button2.setOnClickListener {
            binding.input.text = addToInputText("2")
        }
        binding.button3.setOnClickListener {
            binding.input.text = addToInputText("3")
        }
        binding.button4.setOnClickListener {
            binding.input.text = addToInputText("4")
        }
        binding.button5.setOnClickListener {
            binding.input.text = addToInputText("5")
        }
        binding.button6.setOnClickListener {
            binding.input.text = addToInputText("6")
        }
        binding.button7.setOnClickListener {
            binding.input.text = addToInputText("7")

        }
        binding.button8.setOnClickListener {
            binding.input.text = addToInputText("8")
        }
        binding.button9.setOnClickListener {
            binding.input.text = addToInputText("9")
        }
        binding.buttonDot.setOnClickListener {
            binding.input.text = addToInputText(".")
        }
        binding.buttonDivision.setOnClickListener {
            binding.input.text = addToInputText("÷") // ALT + 0247
        }
        binding.buttonMultiply.setOnClickListener {
            binding.input.text = addToInputText("×") // ALT + 0215
        }

        binding.buttonSubtraction.setOnClickListener {
            binding.input.text = addToInputText("-")
        }
        binding.buttonAddition.setOnClickListener {
            binding.input.text = addToInputText("+")
        }

        binding.buttonEquals.setOnClickListener {
            showResult()
        }
    }

    private fun addToInputText(buttonValue: String): String {

        return binding.input.text.toString() + "" + buttonValue
    }

    private fun getInputExpression(): String {
        var expression = binding.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result =  Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                binding.output.text = ""
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show Result
                binding.output.text = DecimalFormat("0.######").format(result).toString()
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        } catch (e: Exception) {
            // Show Error Message
            binding.output.text = ""
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }
}