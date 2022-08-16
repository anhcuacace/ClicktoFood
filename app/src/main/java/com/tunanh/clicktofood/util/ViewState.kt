package com.tunanh.clicktofood.util

class ViewState {
    companion object CommonState{
        // Với các state dùng chung thì để giá trị âm
        const val SHOW_LOADING: Int = -1
        const val HIDE_LOADING: Int = -2
        const val SHOW_ERROR: Int = -3
        const val EMPTY_DATA: Int = -4
        const val HAVE_DATA: Int = -5
    }
}