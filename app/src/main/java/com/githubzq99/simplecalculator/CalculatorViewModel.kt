package com.githubzq99.simplecalculator

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    enum class BUTTONS(val value: Int){
        DIGIT_0(0), DIGIT_1(1), DIGIT_2(2), DIGIT_3(3), DIGIT_4(4),
        DIGIT_5(5), DIGIT_6(6), DIGIT_7(7), DIGIT_8(8), DIGIT_9(9),
        OPERATOR_DIVIDE(10), OPERATOR_MULTIPLY(11), OPERATOR_SUBTRACTION(12),
        OPERATOR_ADDITION(13), OPERATOR_EQUALS(14),
        DELETE(15), CLEAR(16), DECIMAL(17), SIGN(18),NONE(19)
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
        if (calculatorButton != null) {
            calculatorDisplay.addDigit(calculatorButton.value)
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