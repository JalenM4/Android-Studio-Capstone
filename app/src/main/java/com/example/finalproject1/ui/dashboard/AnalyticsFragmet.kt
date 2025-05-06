package com.example.finalproject1.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject1.databinding.FragmentAnalyticsBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import android.graphics.Color

class AnalyticsFragment : Fragment() {

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dummy data for pie chart
        val entries = listOf(
            PieEntry(500f, "Food"),
            PieEntry(300f, "Shopping"),
            PieEntry(200f, "Transport")
        )

        val dataSet = PieDataSet(entries, "Expenses")
        dataSet.colors = listOf(Color.BLUE, Color.RED, Color.GREEN)
        dataSet.valueTextSize = 14f

        val data = PieData(dataSet)

        binding.pieChart.data = data
        binding.pieChart.description.isEnabled = false
        binding.pieChart.centerText = "Expenses"
        binding.pieChart.animateY(1000)
        binding.pieChart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}