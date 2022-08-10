package com.tunanh.clicktofood.ui.main

import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.ActivityMainBinding
import com.tunanh.clicktofood.ui.base.BaseActivity
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun layoutRes(): Int = R.layout.activity_main
//    private lateinit var googleSignInClient: GoogleSignInClient
//    private var callbackManager= CallbackManager.Factory.create()
    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java
//    val startForResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//            if (Activity.RESULT_OK == result.resultCode) {
//                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//                try {
//                    //google sign in was successful, authenticate with firebase
//                    val account = task.getResult(ApiException::class.java)!!
//                    Timber.d("FirebaseAuthWithGoogle:" + account.id)
//                    firebaseauthWithGoogle(account.idToken!!)
//                }catch (e: ApiException) {
//                    // google sign in failed, update iu
//                    Timber.w(e, "Google sign in failed")
//                }
//            }
//            callbackManager.onActivityResult(Activity.RESULT_OK,result.resultCode, result.data)
//
//
//        }
//    private lateinit var auth: FirebaseAuth
//    private fun firebaseauthWithGoogle(idToken: String){
//
//        val credential= GoogleAuthProvider.getCredential(idToken,null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    // sign in success, update UI with the signed- in user's information
//                    Timber.d("signInwithcredential:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                }else {
//                    //if sign in fails, display a message to the user
//                    Timber.w(it.exception, "signinWithCredential:failure")
//                    updateUI(null)
//                }
//            }}
    override fun initView() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.your_web_client_id))
//            .requestEmail()
//            .build()
//        googleSignInClient= GoogleSignIn.getClient(this.requireActivity(),gso)
    }
}