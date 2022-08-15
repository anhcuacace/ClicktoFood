package com.tunanh.clicktofood.ui.home.more.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.User
import com.tunanh.clicktofood.databinding.FragmentUpdateProfileBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.util.setOnSingClickListener

class UpdateProfileFragment : BaseFragment<FragmentUpdateProfileBinding, UpdateProfileViewModel>() {
    companion object {
        const val MY_REQUEST_CODE = 10
    }

    private var mUri: Uri? = null
    private var mUser: User? = null
    private val mActivityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data ?: return@ActivityResultCallback
                    val uri = intent.data
                    if (uri != null) {
                        setUri(uri)
                    }
                    context?.let { Glide.with(it).load(uri).into(binding.avt) }
                }
            })

    override fun layoutRes(): Int = R.layout.fragment_update_profile

    override fun viewModelClass(): Class<UpdateProfileViewModel> =
        UpdateProfileViewModel::class.java

    override fun initView() {
        getInfo()
        updateInfo()
    }

    private fun getInfo() {
        viewModel.user.observe(this) {
            mUser = it
            context?.let { it1 ->
                Glide.with(it1).load(Uri.parse(mUser?.image)).error(R.drawable.ic_account_circle)
                    .into(binding.avt)
            }
            binding.name.setText(mUser?.name)
            binding.email.setText(mUser?.email)
            binding.phone.setText(mUser?.phone)
        }
    }

    private fun updateInfo() {

        binding.avt.setOnSingClickListener { onClickRequestRemission() }
        binding.btn.setOnSingClickListener { onClickUpdateProfile() }
    }

    private fun onClickUpdateProfile() {
        val user = FirebaseAuth.getInstance().currentUser ?: return
        val strFullName: String = binding.name.text.toString().trim { it <= ' ' }
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(strFullName)
            .setPhotoUri(mUri)
            .build()
        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Update profile success", Toast.LENGTH_SHORT).show()
                }
            }
        mUser = User(
            email = binding.email.text.toString(),
            name = strFullName,
            image = mUri.toString(),
            phone = binding.phone.text.toString()
        )
        findNavController().navigate(
            R.id.action_updateProfileFragment_to_moreFragment
        )
    }

    private fun setUri(mUri: Uri) {
        this.mUri = mUri
    }

    private fun onClickRequestRemission() {


        //không có phiên bản android nhỏ hơn 6
        if (activity?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            activity?.requestPermissions(permissions, MY_REQUEST_CODE)
        }
    }


    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {

        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                Toast.makeText(activity, "vui long cho phep truy cap", Toast.LENGTH_SHORT).show()
            }
        }
    }
}