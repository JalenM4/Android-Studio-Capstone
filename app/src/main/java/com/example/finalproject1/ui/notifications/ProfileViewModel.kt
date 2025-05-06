package com.example.finalproject1.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    // LiveData to hold the monthly budget
    private val _monthlyBudget = MutableLiveData<Double>()
    val monthlyBudget: LiveData<Double> get() = _monthlyBudget

    // LiveData to hold the total expenses
    private val _totalExpenses = MutableLiveData<Double>()
    val totalExpenses: LiveData<Double> get() = _totalExpenses

    // LiveData to compute remaining budget
    private val _remainingBudget = MediatorLiveData<Double>()
    val remainingBudget: LiveData<Double> get() = _remainingBudget

    init {
        // Initialize with default values
        _monthlyBudget.value = 1000.00
        _totalExpenses.value = 345.75

        // Automatically calculate remaining budget when budget or expenses change
        _remainingBudget.addSource(_monthlyBudget) { calculateRemainingBudget() }
        _remainingBudget.addSource(_totalExpenses) { calculateRemainingBudget() }
    }

    // Method to update the monthly budget
    fun setMonthlyBudget(budget: Double) {
        _monthlyBudget.value = budget
    }

    // Method to update total expenses
    fun setTotalExpenses(expenses: Double) {
        _totalExpenses.value = expenses
    }

    // Private method to compute remaining budget
    private fun calculateRemainingBudget() {
        val budget = _monthlyBudget.value ?: 0.0
        val expenses = _totalExpenses.value ?: 0.0
        _remainingBudget.value = budget - expenses
    }
}