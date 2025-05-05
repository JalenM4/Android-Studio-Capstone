package com.example.finalproject1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject1.databinding.FragmentHomeBinding

data class Expense(val name: String, val amount: Double)

class ExpenseAdapter(private val expenseList: List<Expense>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(val binding: com.example.finalproject1.databinding.ItemExpenseBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = com.example.finalproject1.databinding.ItemExpenseBinding.inflate(inflater, parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenseList[position]
        holder.binding.textViewExpenseTitle.text = expense.name
        holder.binding.textViewExpenseAmount.text = "$%.2f".format(expense.amount)
    }

    override fun getItemCount(): Int = expenseList.size
}

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

        // Setup RecyclerView
        adapter = ExpenseAdapter(expenseList)
        binding.recyclerViewExpenseList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewExpenseList.adapter = adapter

        homeViewModel.monthlyBudget.observe(viewLifecycleOwner) { budget ->
            binding.textViewBudget.text = "Monthly Budget: $%.2f".format(budget)
        }

        homeViewModel.totalExpenses.observe(viewLifecycleOwner) { expenses ->
            binding.textViewExpenses.text = "Total Expenses: $%.2f".format(expenses)
        }

        homeViewModel.remainingBudget.observe(viewLifecycleOwner) { remaining ->
            binding.textViewBudget.text = "Remaining Budget: $%.2f".format(remaining)
        }

        binding.buttonAddExpense.setOnClickListener {
            val name = binding.editTextExpenseName.text.toString()
            val amountText = binding.editTextExpenseAmount.text.toString()

            if (name.isNotBlank() && amountText.isNotBlank()) {
                val amount = amountText.toDoubleOrNull()
                if (amount != null && amount > 0) {
                    homeViewModel.addExpense(amount)

                    // Add to list and refresh RecyclerView
                    expenseList.add(Expense(name, amount))
                    adapter.notifyItemInserted(expenseList.size - 1)

                    // Clear input fields
                    binding.editTextExpenseName.text.clear()
                    binding.editTextExpenseAmount.text.clear()
                } else {
                    // Optional: Show error for invalid amount
                }
            } else {
                // Optional: Show error for empty fields
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}