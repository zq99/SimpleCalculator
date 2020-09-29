package com.githubzq99.simplecalculator

class CalculatorDisplay {

    private var displayText  = "0"
    private val maxDigits  = 16
    private var resetDisplay = false
    private var isErrorDisplayed = false
    private val errorText = "ERROR"

    fun reset(){
        displayText="0"
        isErrorDisplayed = false
    }

    fun removeLastDigit(){
        displayText = if(isDisplaySignedNegative() && displayText.length == 2){
            "0"
        }else{
            if (displayText.length > 1) displayText.substring(0,displayText.length - 1) else "0"
        }
    }

    fun addDecimal(){
        if (!isNumberDecimal()){
            displayText += "."
        }
    }

    private fun isNumberDecimal() : Boolean{
        return displayText.contains(".")
    }

    private fun isDisplaySignedNegative() : Boolean{
        return displayText.first() == '-'
    }

    fun addSign(){
        if (displayText != "0"){
            displayText = if (isDisplaySignedNegative()){
                displayText.replace("-","")
            }else{
                "-$displayText"
            }
        }
    }

    fun getDisplayAsValue() : Double{
        return (if(displayText.last() == '.') displayText + "0" else displayText).toDouble()
    }

    fun addDigit(digit : Int){
        if(resetDisplay){
            displayText="0"
            resetDisplay =false
        }
        if(isErrorDisplayed){
            displayText="0"
        }
        if(isDisplayLengthBelowLimit()) {
            if(digit == 0){
                displayText += if (displayText != "0") "0" else ""
            }else{
                displayText = if (displayText == "0") digit.toString() else (displayText + digit.toString())
            }
        }
    }

    private fun isDisplayLengthBelowLimit() : Boolean{
        return displayText.length<maxDigits
    }

    fun isErrorDisplayed() : Boolean{
        return isErrorDisplayed
    }

    fun setError(){
        displayText = errorText
        isErrorDisplayed = true
    }

    fun addNumberToDisplay(number : Double){
        val numText = number.toString()
        displayText = if(numText.takeLast(2) == ".0"){
            numText.substring(0,numText.length - 2)
        }else{
            numText
        }
    }

    fun getDisplayText() : String {
        return displayText
    }

    fun setResetDisplay(value : Boolean){
        resetDisplay = value
    }
}
