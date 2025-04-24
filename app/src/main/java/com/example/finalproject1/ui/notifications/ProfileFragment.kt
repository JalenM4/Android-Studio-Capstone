package com.example.finalproject1.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    // Temporary hardcoded values
    private var monthlyBudget = 1000.00
    private var totalExpenses = 345.75

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Update the UI
        binding.textViewMonthlyBudget.text = "Monthly Budget: $%.2f".format(monthlyBudget)
        binding.textViewTotalExpenses.text = "Total Expenses: $%.2f".format(totalExpenses)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}