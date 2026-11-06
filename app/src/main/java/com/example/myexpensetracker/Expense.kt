package com.example.myexpensetracker

data class Expense(
    val id: Int,
    val amount: Double,
    val currency: String,
    val category: String,
    val date: String,
    val remark: String? = null
)