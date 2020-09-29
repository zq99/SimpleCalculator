package com.githubzq99.simplecalculator

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.zq99.simplecalculator.R
import com.zq99.simplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupButtons(binding)
    }

    private fun setupButtons(b: ActivityMainBinding) {
        val buttons = arrayOf(
            b.button1, b.button2, b.button3, b.button4, b.button5, b.button6,
            b.button7, b.button8, b.button9, b.button10, b.button11, b.button12,
            b.button13, b.button14, b.button15, b.button16, b.button17, b.button18,
            b.button20
        )
        for (button in buttons) {
            button.setOnClickListener(this)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                showAbout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAbout() {
        showAlertDialog()
    }

    override fun onClick(v: View?) {
        val calculatorButtonType = getCalculatorButtonType(v?.id)
        processUserInput(calculatorButtonType)
    }

    private fun processUserInput(calculatorButtonType: CalculatorViewModel.BUTTONS?) {
        viewModel.processButtonPress(calculatorButtonType)
        binding.tvDisplay.text = viewModel.getDisplayText()
    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle(getString(R.string.DialogTitle))
        val alert1 = getString(R.string.DialogSource)
        val alert2 = getString(R.string.DialogLicense)
        val msg = alert1 + "\n\n" + alert2 + "\n"
        alertDialog.setMessage(msg)
        alertDialog.setPositiveButton(getString(R.string.DialogOk)) { _, _ -> }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

    private fun getCalculatorButtonType(id: Int?): CalculatorViewModel.BUTTONS? {
        val map = hashMapOf(
            binding.button1.id to CalculatorViewModel.BUTTONS.DIGIT_7,
            binding.button2.id to CalculatorViewModel.BUTTONS.DIGIT_8,
            binding.button3.id to CalculatorViewModel.BUTTONS.DIGIT_9,
            binding.button4.id to CalculatorViewModel.BUTTONS.OPERATOR_DIVIDE,
            binding.button5.id to CalculatorViewModel.BUTTONS.DIGIT_4,
            binding.button6.id to CalculatorViewModel.BUTTONS.DIGIT_5,
            binding.button7.id to CalculatorViewModel.BUTTONS.DIGIT_6,
            binding.button8.id to CalculatorViewModel.BUTTONS.OPERATOR_MULTIPLY,
            binding.button9.id to CalculatorViewModel.BUTTONS.DIGIT_1,
            binding.button10.id to CalculatorViewModel.BUTTONS.DIGIT_2,
            binding.button11.id to CalculatorViewModel.BUTTONS.DIGIT_3,
            binding.button12.id to CalculatorViewModel.BUTTONS.OPERATOR_SUBTRACTION,
            binding.button13.id to CalculatorViewModel.BUTTONS.SIGN,
            binding.button14.id to CalculatorViewModel.BUTTONS.DIGIT_0,
            binding.button15.id to CalculatorViewModel.BUTTONS.DECIMAL,
            binding.button16.id to CalculatorViewModel.BUTTONS.OPERATOR_ADDITION,
            binding.button17.id to CalculatorViewModel.BUTTONS.CLEAR,
            binding.button18.id to CalculatorViewModel.BUTTONS.DELETE,
            binding.button20.id to CalculatorViewModel.BUTTONS.OPERATOR_EQUALS
        )
        return map[id]
    }

}