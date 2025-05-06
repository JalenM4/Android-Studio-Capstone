package com.example.finalproject1.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.finalproject1.databinding.FragmentProfileBinding

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

        // 🔥 Handle Account click (Change Budget)
        binding.textViewAccount.setOnClickListener {
            showChangeBudgetDialog()
        }

        // 🔥 Handle Settings click (Toast)
        binding.textViewSettings.setOnClickListener {
            Toast.makeText(requireContext(), "Settings clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showChangeBudgetDialog() {
        val editText = EditText(requireContext())
        editText.hint = "Enter new budget"

        AlertDialog.Builder(requireContext())
            .setTitle("Change Monthly Budget")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val input = editText.text.toString()
                val newBudget = input.toDoubleOrNull()
                if (newBudget != null) {
                    profileViewModel.setMonthlyBudget(newBudget)
                    Toast.makeText(requireContext(), "Budget updated!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Invalid input", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}