package com.example.finalproject1.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class ExpenseCategory(val category: String, val amount: Float)

class AnalyticsViewModel : ViewModel() {

    // Simulated data – replace with actual DB-backed LiveData later
    private val _categoryExpenses = MutableLiveData<List<ExpenseCategory>>().apply {
        value = listOf(
            ExpenseCategory("Food", 200f),
            ExpenseCategory("Transport", 75f),
            ExpenseCategory("Entertainment", 120f),
            ExpenseCategory("Utilities", 90f),
            ExpenseCategory("Other", 60f)
        )
    }

    val categoryExpenses: LiveData<List<ExpenseCategory>> = _categoryExpenses
}