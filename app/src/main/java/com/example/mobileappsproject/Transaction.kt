package com.example.mobileappsproject

data class Transaction(
    val amount: Int,
    val type: String,  // "income" or "expense"
    val description: String = "",  // Optional description
    val timestamp: Long = System.currentTimeMillis() // Current time for each transaction
)
