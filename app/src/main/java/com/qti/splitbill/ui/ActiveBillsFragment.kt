package com.qti.splitbill.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qti.splitbill.R
import com.qti.splitbill.databinding.FragmentActiveBillsBinding

class ActiveBillsFragment : Fragment() {

    private var _binding: FragmentActiveBillsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentActiveBillsBinding.inflate(inflater, container, false)


        initEvent()
        return binding.root
    }

    private fun initEvent() {
        binding.fbAddBills.setOnClickListener {
            val intent = Intent(requireActivity(), DetailBillActivity::class.java)
            startActivity(intent)
        }
    }

}