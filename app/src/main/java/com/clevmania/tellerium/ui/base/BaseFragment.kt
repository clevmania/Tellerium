package com.clevmania.tellerium.ui.base

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.clevmania.tellerium.R
import com.clevmania.tellerium.utils.ProgressDialogUtils

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
abstract class BaseFragment : Fragment() {
    private fun showBlockingProgress(message: String = getString(R.string.processing)) {
        ProgressDialogUtils.getInstance()
            .showProgressDialog(requireContext(), message, cancelable = false)
    }

    private fun hideBlockingProgress() {
        ProgressDialogUtils.getInstance().dismissProgressDialog()
    }

    fun toggleBlockingProgress(value: Boolean) {
        if (value) {
            showBlockingProgress()
        } else {
            hideBlockingProgress()
        }
    }

    fun showAlertDialog(message: String, title: String){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        builder.create().show()
    }

    fun showErrorDialog(message: String, title: String = getString(R.string.title_api_error)){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        builder.create().show()
    }
}