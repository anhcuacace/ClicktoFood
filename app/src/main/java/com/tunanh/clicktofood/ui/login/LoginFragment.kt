package com.tunanh.clicktofood.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentLoginBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import timber.log.Timber
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils


import android.util.Patterns
import android.view.View
import android.widget.Toast


import com.facebook.CallbackManager


import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    private var callbackManager= CallbackManager.Factory.create()
    private lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private val time_loading:Long = 2000
    private var dialog: Dialog?=null

    private var TAG="Login"
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (Activity.RESULT_OK == result.resultCode) {
                val task =GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    //google sign in was successful, authenticate with firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Timber.d("FirebaseAuthWithGoogle:" + account.id)
                    firebaseauthWithGoogle(account.idToken!!)
                }catch (e: ApiException){
                    // google sign in failed, update iu
                    Log.w(ContentValues.TAG,"Google sign in failed",e)
                }
            }
            callbackManager.onActivityResult(Activity.RESULT_OK,result.resultCode, result.data)
        }
    override fun layoutRes(): Int =R.layout.fragment_login

    override fun viewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun initView() {

    }
    private fun firebaseauthWithGoogle(idToken: String){

        val credential= GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    // sign in success, update UI with the signed- in user's information
                    Log.d(ContentValues.TAG,"signInwithcredential:success")
                    val user= auth.currentUser
                    updateUI(user)
                }else{
                    //if sign in fails, display a message to the user
                    Log.w(ContentValues.TAG,"signinWithCredential:failure",task.exception)
                    updateUI(null)
                }
            }
    }
    private fun updateUI(user: FirebaseUser?){

        if (user!=null){

//            val intent= Intent(applicationContext,MainActivity::class.java)
//            intent.putExtra(EXTRA_NAME,user.displayName)
//            intent.putExtra(EXTRA_EMAIL,user.email)
//            intent.putExtra(IMAGE,user.photoUrl)
//            startActivity(intent)
//            finish()
        }
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun logInTransparent() {
        binding.tvSignUp.setOnClickListener {
            binding.tvSignUp.background = resources.getDrawable(R.drawable.switch_trcks, null)
            binding.tvSignUp.setTextColor(resources.getColor(R.color.white, null))
            binding.tvLogIn.background = null
            binding.signUpLayout.visibility = View.VISIBLE
            binding.logInLayout.visibility = View.GONE
            binding.tvLogIn.setTextColor(resources.getColor(R.color.a1, null))
            binding.btnLogIn.visibility = View.GONE
            binding.btnSignUp.visibility = View.VISIBLE
        }
        binding.tvLogIn.setOnClickListener {
            binding.tvLogIn.background = resources.getDrawable(R.drawable.switch_trcks, null)
            binding.tvLogIn.setTextColor(resources.getColor(R.color.white, null))
            binding.tvSignUp.background = null
            binding.logInLayout.visibility = View.VISIBLE
            binding.signUpLayout.visibility = View.GONE
            binding.tvSignUp.setTextColor(resources.getColor(R.color.a1, null))
            binding.btnSignUp.visibility = View.GONE
            binding.btnLogIn.visibility = View.VISIBLE

        }


    }


}