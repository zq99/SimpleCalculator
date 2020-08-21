package com.githubzq99.simplecalculator

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    enum class BUTTONS{
        DIGIT_0, DIGIT_1, DIGIT_2, DIGIT_3, DIGIT_4, DIGIT_5, DIGIT_6, DIGIT_7, DIGIT_8, DIGIT_9,
        OPERATOR_DIVIDE, OPERATOR_MULTIPLY, OPERATOR_SUBTRACTION, OPERATOR_ADDITION, OPERATOR_EQUALS,
        DELETE, CLEAR, DECIMAL, SIGN,NONE
    }

    private val operators = arrayOf(BUTTONS.OPERATOR_ADDITION,BUTTONS.OPERATOR_SUBTRACTION,
        BUTTONS.OPERATOR_MULTIPLY,BUTTONS.OPERATOR_DIVIDE)

    private var memory : Double = 0.0
    private var selectedOperator = BUTTONS.NONE
    private val calculatorDisplay = CalculatorDisplay()

    private fun clear(){
        memory = 0.0
        selectedOperator = BUTTONS.NONE
        calculatorDisplay.reset()
    }

    private fun delete(){
        calculatorDisplay.removeLastDigit()
    }

    private fun addDecimal(){
        calculatorDisplay.addDecimal()
    }

    private fun addSignToNumber(){
        calculatorDisplay.addSign()
    }

    private fun addDigitToDisplay(calculatorButton: BUTTONS?){
        when (calculatorButton) {
            BUTTONS.DIGIT_0 -> calculatorDisplay.addDigit(0)
            BUTTONS.DIGIT_1 -> calculatorDisplay.addDigit(1)
            BUTTONS.DIGIT_2 -> calculatorDisplay.addDigit(2)
            BUTTONS.DIGIT_3 -> calculatorDisplay.addDigit(3)
            BUTTONS.DIGIT_4 -> calculatorDisplay.addDigit(4)
            BUTTONS.DIGIT_5 -> calculatorDisplay.addDigit(5)
            BUTTONS.DIGIT_6 -> calculatorDisplay.addDigit(6)
            BUTTONS.DIGIT_7 -> calculatorDisplay.addDigit(7)
            BUTTONS.DIGIT_8 -> calculatorDisplay.addDigit(8)
            BUTTONS.DIGIT_9 -> calculatorDisplay.addDigit(9)
            else -> return
        }
    }

    fun processButtonPress(calculatorButton : BUTTONS?){
        when {
            calculatorButton == BUTTONS.DELETE -> {
                delete()
            }
            calculatorButton == BUTTONS.CLEAR -> {
                clear()
            }
            calculatorButton == BUTTONS.DECIMAL -> {
                addDecimal()
            }
            calculatorButton == BUTTONS.SIGN -> {
                addSignToNumber()
            }
            calculatorButton == BUTTONS.OPERATOR_EQUALS -> {
                processEquals()
            }
            operators.contains(calculatorButton) -> {
                processOperator(calculatorButton)
            }
            else -> {
                addDigitToDisplay(calculatorButton)
            }
        }
    }

    private fun processEquals(){
        if(selectedOperator!=BUTTONS.NONE) {
            val result: Double
            val calculation = Calculation(memory,calculatorDisplay.getDisplayAsValue())
            if(calculation.isDivideByZero() && selectedOperator == BUTTONS.OPERATOR_DIVIDE){
                calculatorDisplay.setError()
                return
            }

            result = when (selectedOperator) {
                BUTTONS.OPERATOR_ADDITION -> {
                    calculation.getAddition()
                }
                BUTTONS.OPERATOR_SUBTRACTION -> {
                    calculation.getSubtraction()
                }
                BUTTONS.OPERATOR_DIVIDE -> {
                    calculation.getDivision()
                }
                BUTTONS.OPERATOR_MULTIPLY -> {
                    calculation.getProduct()
                }
                else -> calculatorDisplay.getDisplayAsValue()
            }

            calculatorDisplay.setResetDisplay(true)
            calculatorDisplay.addNumberToDisplay(result)
            selectedOperator = BUTTONS.NONE
            memory = 0.0
        }
    }

    private fun processOperator(calculatorButton: BUTTONS?){
        if(!calculatorDisplay.isErrorDisplayed()){
            calculatorDisplay.setResetDisplay(true)
            memory = calculatorDisplay.getDisplayAsValue()
            if (calculatorButton != null) {
                selectedOperator = calculatorButton
            }
        }
    }

    fun getDisplayText() : String {
        return calculatorDisplay.getDisplayText()
    }


}