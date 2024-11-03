package com.example.mobileappsproject

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvBalance: TextView
    private lateinit var btnAddMoney: Button
    private lateinit var btnGiveMoney: Button
    private lateinit var btnSaveMoney: Button
    private lateinit var lvTransactions: ListView
    private var transactionList = mutableListOf<String>() // Changed to String for simplicity
    private lateinit var transactionAdapter: ArrayAdapter<String>

    private var currentBalance: Int = 3500 // Initial balance (example)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        tvBalance = findViewById(R.id.tv_balance)
        btnAddMoney = findViewById(R.id.btn_add_money)
        btnGiveMoney = findViewById(R.id.btn_give_money)
        btnSaveMoney = findViewById(R.id.btn_save_money)
        lvTransactions = findViewById(R.id.lv_transactions)

        // Initialize transaction adapter
        transactionAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, transactionList)
        lvTransactions.adapter = transactionAdapter

        // Display the initial balance
        updateBalanceDisplay()

        // Button listeners
        btnAddMoney.setOnClickListener {
            showAddMoneyDialog()
        }

        btnGiveMoney.setOnClickListener {
            showGiveMoneyDialog()
        }

        btnSaveMoney.setOnClickListener {
            showSaveMoneyDialog()
        }
    }

    private fun updateBalanceDisplay() {
        tvBalance.text = "$currentBalance lv"
    }

    private fun addMoney(amount: Int) {
        currentBalance += amount
        updateBalanceDisplay()
        transactionList.add("Added: $amount lv")
        transactionAdapter.notifyDataSetChanged() // Notify adapter of data change
    }

    private fun giveMoney(amount: Int) {
        if (currentBalance >= amount) {
            currentBalance -= amount
            updateBalanceDisplay()
            transactionList.add("Given: $amount lv")
            transactionAdapter.notifyDataSetChanged() // Notify adapter of data change
        } else {
            Toast.makeText(this, "Insufficient balance", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveMoney(amount: Int) {
        if (currentBalance >= amount) {
            currentBalance -= amount
            updateBalanceDisplay()
            transactionList.add("Saved: $amount lv")
            transactionAdapter.notifyDataSetChanged() // Notify adapter of data change
        } else {
            Toast.makeText(this, "Insufficient balance", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showAddMoneyDialog() {
        val input = EditText(this)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Money")
            .setMessage("Enter the amount to add:")
            .setView(input)
            .setPositiveButton("Add") { _, _ ->
                val amount = input.text.toString().toIntOrNull()
                if (amount != null && amount > 0) {
                    addMoney(amount)
                } else {
                    Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun showGiveMoneyDialog() {
        val input = EditText(this)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Give Money")
            .setMessage("Enter the amount to give:")
            .setView(input)
            .setPositiveButton("Give") { _, _ ->
                val amount = input.text.toString().toIntOrNull()
                if (amount != null && amount > 0) {
                    giveMoney(amount)
                } else {
                    Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun showSaveMoneyDialog() {
        val input = EditText(this)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Save Money")
            .setMessage("Enter the amount to save:")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                val amount = input.text.toString().toIntOrNull()
                if (amount != null && amount > 0) {
                    saveMoney(amount)
                } else {
                    Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }
}
