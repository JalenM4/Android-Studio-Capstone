package com.example.finalproject1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.finalproject1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.monthlyBudget.observe(viewLifecycleOwner) { budget ->
            binding.textViewBudget.text = "Monthly Budget: $%.2f".format(budget)
        }

        homeViewModel.totalExpenses.observe(viewLifecycleOwner) { expenses ->
            binding.textViewExpenses.text = "Total Expenses: $%.2f".format(expenses)
        }

        homeViewModel.remainingBudget.observe(viewLifecycleOwner) { remaining ->
            binding.textViewBudget.text = "Remaining Budget: $%.2f".format(remaining)
        }

        // Example Button (optional)
        binding.buttonAddExpense.setOnClickListener {
            homeViewModel.addExpense(50.0) // Add test expense
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}