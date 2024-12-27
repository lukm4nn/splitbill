package com.qti.splitbill.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.qti.splitbill.R
import com.qti.splitbill.databinding.ActivityLoginBinding
import com.qti.splitbill.entity.DataLogin
import com.qti.splitbill.helper.SessionManager

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 100 // Request code

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
        initEvent()

    }

    private fun initView() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.WEB_TOKEN_CLIENT_ID)) // Required if you need backend verification
            .build()

        // Build a GoogleSignInClient
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun initEvent() {
        binding.btLogin.setOnClickListener {
            signIn()

        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            Log.d("GoogleSignIn", "Signed in as: ${account.photoUrl}")
            val loginData = DataLogin(account?.displayName.toString(), account?.email.toString(),
                account.photoUrl?.toString()
            )
            SessionManager.setLoginData(this,loginData)

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        } catch (e: ApiException) {
            Log.w("GoogleSignIn", "signInResult:failed code=" + e.statusCode)
        }
    }
}