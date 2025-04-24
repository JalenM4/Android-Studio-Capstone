package com.example.finalproject1.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.finalproject1.databinding.FragmentAnalyticsBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class AnalyticsFragment : Fragment() {

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AnalyticsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categoryExpenses.observe(viewLifecycleOwner) { expenses ->
            val entries = expenses.map { PieEntry(it.amount, it.category) }
            val dataSet = PieDataSet(entries, "Expenses by Category")
            dataSet.colors = listOf(
                Color.parseColor("#FFA726"),
                Color.parseColor("#66BB6A"),
                Color.parseColor("#29B6F6"),
                Color.parseColor("#EF5350"),
                Color.parseColor("#AB47BC")
            )

            val pieData = PieData(dataSet)
            binding.pieChartExpenses.data = pieData
            binding.pieChartExpenses.description.isEnabled = false
            binding.pieChartExpenses.animateY(1000)
            binding.pieChartExpenses.invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}