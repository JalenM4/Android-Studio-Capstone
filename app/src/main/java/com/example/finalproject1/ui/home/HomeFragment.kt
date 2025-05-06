package com.example.finalproject1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject1.databinding.FragmentHomeBinding

data class Expense(val name: String, val amount: Double, val category: String)

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()

    private val expenseList = mutableListOf<Expense>()
    private lateinit var adapter: ExpenseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up categories in spinner
        val categories = listOf("Food", "Shopping", "Transport", "Entertainment", "Others")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = spinnerAdapter

        // Set up RecyclerView
        adapter = ExpenseAdapter(expenseList)
        binding.recyclerViewExpenses.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewExpenses.adapter = adapter

        // Observe LiveData
        homeViewModel.monthlyBudget.observe(viewLifecycleOwner) { budget ->
            binding.textViewBudget.text = "Monthly Budget: $%.2f".format(budget)
        }

        homeViewModel.totalExpenses.observe(viewLifecycleOwner) { expenses ->
            binding.textViewExpenses.text = "Total Expenses: $%.2f".format(expenses)
        }

        homeViewModel.remainingBudget.observe(viewLifecycleOwner) { remaining ->
            binding.textViewBudget.text = "Remaining Budget: $%.2f".format(remaining)
        }

        // Add Expense Button
        binding.buttonAddExpense.setOnClickListener {
            addExpense()
        }
    }

    private fun addExpense() {
        val name = binding.editTextExpenseName.text.toString()
        val amountText = binding.editTextExpenseAmount.text.toString()
        val category = binding.spinnerCategory.selectedItem.toString()

        if (name.isBlank() || amountText.isBlank()) {
            Toast.makeText(requireContext(), "Please enter name and amount", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountText.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            Toast.makeText(requireContext(), "Enter a valid amount", Toast.LENGTH_SHORT).show()
            return
        }

        // Add expense to list
        val expense = Expense(name, amount, category)
        expenseList.add(expense)
        adapter.notifyItemInserted(expenseList.size - 1)

        // Update ViewModel budget
        homeViewModel.addExpense(amount, category)

        // Clear inputs
        binding.editTextExpenseName.text.clear()
        binding.editTextExpenseAmount.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}