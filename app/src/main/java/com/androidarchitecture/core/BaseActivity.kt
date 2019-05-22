package com.androidarchitecture.core

import android.arch.lifecycle.LifecycleActivity
import android.os.Handler
import android.os.Message
import android.app.ProgressDialog
import android.content.Context
import android.view.KeyEvent
import com.androidarchitecture.App
import com.androidarchitecture.di.application.AppComponent
import android.support.v4.app.Fragment
import com.androidarchitecture.di.HasComponent
import com.androidarchitecture.utility.toast


/**
 * Base activity for the whole project.
 */
abstract class BaseActivity : LifecycleActivity(), Handler.Callback, LoadingUiHandler {

    private val MSG_SHOW_LOADING_DIALOG = 0x1000      //4096 in dec
    private val MSG_UPDATE_LOADING_MESSAGE = 0x1001   //4097 in dec
    private val MSG_HIDE_LOADING_DIALOG = 0x1002      //4098 in dec

    private val mUIHandler = Handler(this)
    private var mProgressDialog: ProgressDialog? = null

    //region BaseActivity
    /**
     * Returns instance of {@link AppComponent}.
     */
    protected fun getAppComponent() : AppComponent = (applicationContext as HasComponent<*>).getComponent()
            as AppComponent
    //endregion

    //region Callback
    override fun handleMessage(msg: Message?): Boolean {
        var result = false
        when (msg?.what) {
            MSG_SHOW_LOADING_DIALOG -> { handleShowLoadingDialog(msg); result = true}
            MSG_UPDATE_LOADING_MESSAGE -> {handleUpdateLoadingDialog(msg); result = true}
            MSG_HIDE_LOADING_DIALOG -> {handleHideLoadingDialog(); result = true}
        }
        return result
    }
    //endregion

    //region LoadingUiHandler
    override fun showLoadingDialog(message: String) {
        val showMessage = Message()
        showMessage.what = MSG_SHOW_LOADING_DIALOG
        showMessage.obj = showMessage
        mUIHandler.sendMessage(showMessage)
    }

    override fun updateLoadingDialog(message: String) {
        val updateMessage = Message()
        updateMessage.what = MSG_UPDATE_LOADING_MESSAGE
        updateMessage.obj = updateMessage
        mUIHandler.sendMessage(updateMessage)
    }

    override fun hideLoadingDialog() {
        val hideMessage = Message()
        hideMessage.what = MSG_HIDE_LOADING_DIALOG
        mUIHandler.sendMessage(hideMessage)
    }
    //endregion

    //region FragmentNavigation
    fun addFragment(containerViewId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment::class.java.name)
        }
        fragmentTransaction.commit()
    }

    fun addFragment(containerViewId: Int, fragment: Fragment, tag: String, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment::class.java.name)
        }
        fragmentTransaction.commit()
    }

    fun replaceFragment(containerViewId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment::class.java.name)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun removeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
    }

    fun removeFragment(tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(supportFragmentManager.findFragmentByTag(tag)).commit()
    }
    //endregion


    //region Utility API
    private fun isProgressShowing(): Boolean = null != mProgressDialog && mProgressDialog!!.isShowing

    private fun handleHideLoadingDialog() {
        if (isProgressShowing()) {
            mProgressDialog?.dismiss()
        }
    }

    private fun handleUpdateLoadingDialog(message: Message) {
        if (isProgressShowing()) {
            mProgressDialog?.setMessage(message.obj as String)
        }
    }

    private fun handleShowLoadingDialog(message: Message): Boolean {
        if (null == mProgressDialog) {
            mProgressDialog = ProgressDialog(this@BaseActivity)
        }
        mProgressDialog?.let {
            it.setMessage(message.obj as String)
            it.setCancelable(false)
            it.setOnKeyListener { dialog, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss()
                }
                false
            }
            if (!it.isShowing) {
                it.show()
            }
        }

        return true
    }
    //endregion
}