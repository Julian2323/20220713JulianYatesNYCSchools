package com.example.a20220713julianyatesnycschools

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a20220713julianyatesnycschools.data.model.SAT
import com.example.a20220713julianyatesnycschools.databinding.FragmentDetailBinding
import com.example.a20220713julianyatesnycschools.viewmodel.SchoolViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {

    private val viewModel: SchoolViewModel by lazy {
        ViewModelProvider(this)[SchoolViewModel::class.java]
    }
    lateinit var sat: SAT
    private val navigationArgs: DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun bind(sat: SAT) {
        binding.apply {
            tvDetailSchoolname.text = sat.school_name
            tvDetailDbn.text = sat.dbn
            tvDetailMath.text = sat.sat_math_avg_score
            tvDetailReading.text = sat.sat_critical_reading_avg_score
            tvDetailWriting.text = sat.sat_writing_avg_score
            tvDetailNumOfStudents.text = sat.num_of_sat_test_takers
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbn = navigationArgs.dbn
        viewModel.getSatScores(dbn).observe(this.viewLifecycleOwner){
            bind(sat)
        }
        binding.backButton.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToSchoolRVFragment()
            this.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}