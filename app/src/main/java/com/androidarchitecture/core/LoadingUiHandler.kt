package com.androidarchitecture.core

/**
 * Created by dmobiledevcore on 6/18/17.
 */
interface LoadingUiHandler  {

    fun showLoadingDialog(message: String)

    fun updateLoadingDialog(message: String)

    fun hideLoadingDialog();
}