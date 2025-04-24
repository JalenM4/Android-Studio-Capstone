package com.example.finalproject1.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    // LiveData to hold the monthly budget
    private val _monthlyBudget = MutableLiveData<Double>()
    val monthlyBudget: LiveData<Double> get() = _monthlyBudget

    // LiveData to hold the total expenses
    private val _totalExpenses = MutableLiveData<Double>()
    val totalExpenses: LiveData<Double> get() = _totalExpenses

    init {
        // Initialize with default values
        _monthlyBudget.value = 1000.00
        _totalExpenses.value = 345.75
    }

    // Method to update the monthly budget
    fun setMonthlyBudget(budget: Double) {
        _monthlyBudget.value = budget
    }

    // Method to update total expenses
    fun setTotalExpenses(expenses: Double) {
        _totalExpenses.value = expenses
    }
}