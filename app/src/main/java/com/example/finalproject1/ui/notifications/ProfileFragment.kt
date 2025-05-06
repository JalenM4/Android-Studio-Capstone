package com.example.finalproject1.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject1.R
import com.example.finalproject1.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.monthlyBudget.observe(viewLifecycleOwner) { budget ->
            binding.textViewMonthlyBudget.text = "Monthly Budget: $%.2f".format(budget)
        }

        profileViewModel.totalExpenses.observe(viewLifecycleOwner) { expenses ->
            binding.textViewTotalExpenses.text = "Total Expenses: $%.2f".format(expenses)
        }

        profileViewModel.setMonthlyBudget(1200.00)
        profileViewModel.setTotalExpenses(450.00)

        binding.buttonAccount.setOnClickListener {
            findNavController().navigate(R.id.navigation_account)
        }

        binding.buttonSettings.setOnClickListener {
            findNavController().navigate(R.id.navigation_settings)
        }

        binding.buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()
            // Optional: Redirect to Login screen
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
