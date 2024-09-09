package com.example.constaraintlayoutkalkulator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.constaraintlayoutkalkulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var operator = ""
    private var firstValue = ""
    private var secondValue = ""
    private var isOperatorSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up button listeners
        binding.apply {
            // Number buttons
            val numberButtons = listOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
            for (button in numberButtons) {
                button.setOnClickListener { onNumberClick(button.text.toString()) }
            }

            // Operator buttons
            buttonPlus.setOnClickListener { onOperatorClick("+") }
            buttonMines.setOnClickListener { onOperatorClick("-") }
            buttonKali.setOnClickListener { onOperatorClick("x") }
            buttonBagi.setOnClickListener { onOperatorClick("/") }
            buttonPersen.setOnClickListener { onOperatorClick("%") }

            // Function buttons
            ButtonAC.setOnClickListener { onClearClick() }
            ButtonEquals.setOnClickListener { onEqualsClick() }
        }
    }

    private fun onNumberClick(number: String) {
        if (isOperatorSelected) {
            secondValue += number
            binding.textView2.text = secondValue
        } else {
            firstValue += number
            binding.textView2.text = firstValue
        }
        binding.textView1.text = "${firstValue} $operator $secondValue"
    }

    private fun onOperatorClick(selectedOperator: String) {
        if (firstValue.isNotEmpty()) {
            operator = selectedOperator
            isOperatorSelected = true
            binding.textView1.text = "$firstValue $operator"
        }
    }

    private fun onClearClick() {
        firstValue = ""
        secondValue = ""
        operator = ""
        isOperatorSelected = false
        binding.textView1.text = ""
        binding.textView2.text = "0"
    }

    private fun onEqualsClick() {
        if (firstValue.isNotEmpty() && secondValue.isNotEmpty()) {
            val result = calculateResult()
            binding.textView2.text = result
            binding.textView1.text = "$firstValue $operator $secondValue"
            firstValue = result
            secondValue = ""
            isOperatorSelected = false
        }
    }

    private fun calculateResult(): String {
        val firstNumber = firstValue.toInt()
        val secondNumber = secondValue.toInt()
        return when (operator) {
            "+" -> (firstNumber + secondNumber).toString()
            "-" -> (firstNumber - secondNumber).toString()
            "x" -> (firstNumber * secondNumber).toString()
            "/" -> (firstNumber / secondNumber).toString()
            "%" -> (firstNumber % secondNumber).toString()
            else -> "0"
        }
    }
}
