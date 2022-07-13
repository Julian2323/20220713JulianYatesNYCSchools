package com.example.a20220713julianyatesnycschools

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20220713julianyatesnycschools.adapter.SchoolAdapter
import com.example.a20220713julianyatesnycschools.databinding.FragmentSchoolRvBinding
import com.example.a20220713julianyatesnycschools.viewmodel.SchoolViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SchoolRVFragment : Fragment() {

    private val viewModel: SchoolViewModel by lazy {
        ViewModelProvider(this)[SchoolViewModel::class.java]
    }

    private var _binding: FragmentSchoolRvBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSchoolRvBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SchoolAdapter {
            val action = SchoolRVFragmentDirections.actionSchoolRVFragmentToDetailFragment(it.dbn)
            this.findNavController().navigate(action)
        }
        binding.schoolRecyclerview.adapter = adapter
        viewModel.allSchools.observe(this.viewLifecycleOwner) {schools ->
            schools.let {
                adapter.submitList(it)
            }
        }
        binding.schoolRecyclerview.layoutManager = LinearLayoutManager(this.context)


        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_SchoolRVFragment_to_SecondFragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}