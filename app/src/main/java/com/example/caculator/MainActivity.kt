package com.example.caculator

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var result: android.widget.TextView
    private lateinit var func: android.widget.TextView
    private var currentInput = ""
    private var operator = ""
    private var firstOperand = ""
    private var secondOperand = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        result = findViewById(R.id.result)
        func = findViewById(R.id.func)
        val buttons = listOf(
            R.id.b0, R.id.b1, R.id.b2, R.id.b3, R.id.b4,
            R.id.b5, R.id.b6, R.id.b7, R.id.b8, R.id.b9
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener { appendNumber((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.bp).setOnClickListener { chooseOperator("+") }
        findViewById<Button>(R.id.bm).setOnClickListener { chooseOperator("-") }
        findViewById<Button>(R.id.bmul).setOnClickListener { chooseOperator("*") }
        findViewById<Button>(R.id.bdiv).setOnClickListener { chooseOperator("/") }

        findViewById<Button>(R.id.be).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.bCE).setOnClickListener { clearInput() }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        func.text = currentInput
    }

    private fun chooseOperator(selectedOperator: String) {
        currentInput += selectedOperator
        func.text = currentInput
    }

    private fun calculateResult() {
        val res = ExpressionBuilder(func.text.toString()).build().evaluate().toInt()
        result.text = res.toString()

    }

    private fun clearInput() {
        currentInput = ""
        operator = ""
        firstOperand = ""
        secondOperand = ""
        result.text = "0"
        func.text = ""
    }

}