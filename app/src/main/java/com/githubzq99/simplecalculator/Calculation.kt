package com.githubzq99.simplecalculator

class Calculation(private var value1: Double, private var value2: Double) {
    fun getAddition() : Double{
        return value1 + value2
    }

    fun getSubtraction() : Double{
        return value1 - value2
    }

    fun getProduct() : Double{
        return value1 * value2
    }

    fun getDivision() : Double{
        if(!isDivideByZero()){
            return value1 / value2
        }
        return 0.0
    }

    fun isDivideByZero() : Boolean{
        return value2 == 0.0
    }
}