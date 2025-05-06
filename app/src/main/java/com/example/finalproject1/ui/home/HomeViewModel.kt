package com.example.finalproject1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _monthlyBudget = MutableLiveData<Double>()
    val monthlyBudget: LiveData<Double> get() = _monthlyBudget

    private val _totalExpenses = MutableLiveData<Double>()
    val totalExpenses: LiveData<Double> get() = _totalExpenses

    // NEW: map of categories ➔ amount spent
    private val _categoryExpenses = MutableLiveData<MutableMap<String, Double>>()
    val categoryExpenses: LiveData<MutableMap<String, Double>> get() = _categoryExpenses

    val remainingBudget: LiveData<Double> = object : MutableLiveData<Double>() {
        init {
            _monthlyBudget.observeForever {
                value = (_monthlyBudget.value ?: 0.0) - (_totalExpenses.value ?: 0.0)
            }
            _totalExpenses.observeForever {
                value = (_monthlyBudget.value ?: 0.0) - (_totalExpenses.value ?: 0.0)
            }
        }
    }

    init {
        _monthlyBudget.value = 1000.00
        _totalExpenses.value = 200.00

        // Initialize category expenses with empty map
        _categoryExpenses.value = mutableMapOf()
    }

    // Now expenses are added with category
    fun addExpense(amount: Double, category: String) {
        _totalExpenses.value = (_totalExpenses.value ?: 0.0) + amount

        val currentMap = _categoryExpenses.value ?: mutableMapOf()
        val existingAmount = currentMap[category] ?: 0.0
        currentMap[category] = existingAmount + amount
        _categoryExpenses.value = currentMap
    }

    fun setMonthlyBudget(budget: Double) {
        _monthlyBudget.value = budget
    }

    fun resetExpenses() {
        _totalExpenses.value = 0.0
        _categoryExpenses.value = mutableMapOf()
    }
}