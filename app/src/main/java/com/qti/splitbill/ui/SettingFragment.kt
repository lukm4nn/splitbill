package com.qti.splitbill.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.qti.splitbill.R
import com.qti.splitbill.databinding.FragmentActiveBillsBinding
import com.qti.splitbill.databinding.FragmentSettingBinding
import com.qti.splitbill.entity.DataLogin
import com.qti.splitbill.helper.SessionManager

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataLogin: DataLogin
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)


        initView()
        initEvent()
        return binding.root
    }

    private fun initEvent() {
        binding.llout.setOnClickListener { v ->
            signout()
        }
    }

    private fun signout() {
        googleSignInClient.signOut()

        val intent = Intent(requireActivity(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun initView() {
        dataLogin = SessionManager.getLoginData(requireContext())!!

        // Configure Google Sign-In options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.WEB_TOKEN_CLIENT_ID)) // Required if you need backend verification
            .build()

        // Build a GoogleSignInClient
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.tvEmail.text = dataLogin.email
        binding.tvAccountName.text = dataLogin.username
        if (dataLogin.avatar != null){
            Glide.with(requireActivity()).load(dataLogin.avatar).into(binding.ivProfile)
        }
    }
}