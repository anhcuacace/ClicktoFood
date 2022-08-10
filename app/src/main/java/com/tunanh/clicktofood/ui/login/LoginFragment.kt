package com.tunanh.clicktofood.ui.login


//import com.facebook.CallbackManager


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentLoginBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import timber.log.Timber


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    private var callbackManager= CallbackManager.Factory.create()
    private lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private val time_loading:Long = 3000



    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->

            if (Activity.RESULT_OK == result.resultCode) {

                val task =GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    //google sign in was successful, authenticate with firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Timber.d("FirebaseAuthWithGoogle:" + account.id)
                    firebaseauthWithGoogle(account.idToken!!)
                }catch (e: ApiException) {
                    // google sign in failed, update iu
                    Timber.w(e, "Google sign in failed")
                }
            }
            callbackManager.onActivityResult(Activity.RESULT_OK,result.resultCode, result.data)


        }
    override fun layoutRes(): Int =R.layout.fragment_login

    override fun viewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun initView() {
        transparent()
        logInTransparent()
        loginGoogle()

    }



    //biến hóa
    private fun transparent() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            binding.cardView.visibility=View.VISIBLE
            binding.animationView.visibility=View.GONE
        },time_loading)
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

    private fun loginGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))

            .requestEmail()
            .build()
        googleSignInClient= GoogleSignIn.getClient(this.requireActivity(),gso)
        binding.googleSignIn.setOnClickListener{
            signInGoogle()

        }
        auth = Firebase.auth
    }

    private fun firebaseauthWithGoogle(idToken: String){

        val credential= GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // sign in success, update UI with the signed- in user's information
                    Timber.d("signInwithcredential:success")
                    val user = auth.currentUser
                    (activity as MainActivity).hiddenLoading()
                    updateUI(user)
                }else {
                    //if sign in fails, display a message to the user
                    Timber.w(it.exception, "signinWithCredential:failure")
                    updateUI(null)
                }
            }}

    private fun signInGoogle() {
        (activity as MainActivity).showLoading()
        val signIntent= googleSignInClient.signInIntent
//        startActivityForResult(signIntent, RC_SING_IN)
    startForResult.launch(signIntent)






    }

    private fun updateUI(user: FirebaseUser?){

        if (user!=null){
            findNavController().navigate(
                R.id.action_loginFragment_to_homeFragment
            )
//
        }
    }
}
