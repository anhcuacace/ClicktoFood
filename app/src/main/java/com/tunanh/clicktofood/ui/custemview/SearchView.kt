package com.tunanh.clicktofood.ui.custemview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.RelativeLayout
import androidx.core.widget.addTextChangedListener
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.LayoutSearchViewBinding

class SearchView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    var onClickSearch: ((String) -> (Unit))? = null
    var onClickIconRight: (() -> (Unit))? = null
    var onClickIconLeft: (() -> (Unit))? = null



    private var rightIcon = -1
    private val binding:LayoutSearchViewBinding
    init {
        binding=LayoutSearchViewBinding.inflate(LayoutInflater.from(context))


        binding.edtSearchViewLayoutSearch.addTextChangedListener {
            setImageRightResource()
        }
        binding.edtSearchViewLayoutSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onClickSearch?.invoke(binding.edtSearchViewLayoutSearch.text.toString().trim())
                binding.edtSearchViewLayoutSearch.clearFocus()
                setImageRightResource()
                true
            }
            false
        }
        binding.ivSearchViewLayoutLeft.setOnClickListener {
            onClickIconLeft?.invoke()
        }
        binding.ivSearchViewLayoutRight.setOnClickListener {
            val textSearch = binding.edtSearchViewLayoutSearch.text.toString().trim()
            if (textSearch.isEmpty()) {
                onClickIconRight?.invoke()
            } else {
                onClickSearch?.invoke("")
                binding.edtSearchViewLayoutSearch.setText("")
            }
        }

        attrs?.let {
            init(it)
        }
    }

    fun addTextChange(callback: ((String) -> Unit)) {
        binding.edtSearchViewLayoutSearch.addTextChangedListener {
            callback.invoke(binding.edtSearchViewLayoutSearch.text.toString().trim())
        }
    }

    fun setTextSearch(text: String) {
        try {
            binding.edtSearchViewLayoutSearch.setText(text)
            binding.edtSearchViewLayoutSearch.setSelection(text.length)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getTextSearch(): String {
        return binding.edtSearchViewLayoutSearch.text.toString().trim()
    }

    fun requestFocusSearch() {
        binding.edtSearchViewLayoutSearch.requestFocus()
    }

    override fun clearFocus() {
        binding.edtSearchViewLayoutSearch.clearFocus()
        super.clearFocus()
    }

    private fun init(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchView)

        rightIcon = typedArray.getResourceId(R.styleable.SearchView_search_view_right_icon, -1)
        if (rightIcon != -1) {
            binding.ivSearchViewLayoutRight.visibility = View.VISIBLE
            binding.ivSearchViewLayoutRight.setImageResource(rightIcon)
        } else {
            binding.ivSearchViewLayoutRight.visibility = GONE
        }

        val leftIcon = typedArray.getResourceId(R.styleable.SearchView_search_view_left_icon, -1)
        if (leftIcon != -1) {
            binding.ivSearchViewLayoutLeft.visibility = View.VISIBLE
            binding.ivSearchViewLayoutLeft.setImageResource(leftIcon)
        } else {
            binding.ivSearchViewLayoutLeft.visibility = GONE
        }

        val hint = typedArray.getString(R.styleable.SearchView_search_view_hint) ?: ""
        binding.edtSearchViewLayoutSearch.hint = hint

        typedArray.recycle()
    }

    private fun setImageRightResource() {
        if (binding.edtSearchViewLayoutSearch.text.toString().trim().isEmpty()) {
            if (rightIcon != -1) {
                binding.ivSearchViewLayoutRight.setImageResource(rightIcon)
                binding.ivSearchViewLayoutRight.visibility = View.VISIBLE
            } else {
                binding.ivSearchViewLayoutRight.setImageResource(R.drawable.ic_cancel)
                binding.ivSearchViewLayoutRight.visibility = View.GONE
            }
        } else {
            binding.ivSearchViewLayoutRight.setImageResource(R.drawable.ic_cancel)
            binding.ivSearchViewLayoutRight.visibility = View.VISIBLE
        }
    }
}