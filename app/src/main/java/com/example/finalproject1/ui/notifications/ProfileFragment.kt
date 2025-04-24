package com.example.finalproject1.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.finalproject1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    // Declare View Binding
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    // Using the ProfileViewModel
    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using ViewBinding
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe LiveData from the ViewModel and update the UI when data changes
        profileViewModel.monthlyBudget.observe(viewLifecycleOwner, { budget ->
            binding.textViewMonthlyBudget.text = "Monthly Budget: $%.2f".format(budget)
        })

        profileViewModel.totalExpenses.observe(viewLifecycleOwner, { expenses ->
            binding.textViewTotalExpenses.text = "Total Expenses: $%.2f".format(expenses)
        })

        // Example of updating the values from ViewModel
        // These can be triggered by user actions or events, e.g., a button click
        profileViewModel.setMonthlyBudget(1200.00)  // Update monthly budget
        profileViewModel.setTotalExpenses(450.00)   // Update total expenses
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up the binding when the view is destroyed
        _binding = null
    }
}