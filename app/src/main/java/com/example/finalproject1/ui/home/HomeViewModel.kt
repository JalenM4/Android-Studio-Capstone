package com.example.finalproject1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _monthlyBudget = MutableLiveData<Double>()
    val monthlyBudget: LiveData<Double> get() = _monthlyBudget

    private val _totalExpenses = MutableLiveData<Double>()
    val totalExpenses: LiveData<Double> get() = _totalExpenses

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
    }

    fun addExpense(amount: Double) {
        _totalExpenses.value = (_totalExpenses.value ?: 0.0) + amount
    }

    fun setMonthlyBudget(budget: Double) {
        _monthlyBudget.value = budget
    }

    fun resetExpenses() {
        _totalExpenses.value = 0.0
    }
}