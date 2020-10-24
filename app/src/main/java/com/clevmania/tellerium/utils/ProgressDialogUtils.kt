package com.clevmania.tellerium.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.clevmania.tellerium.R

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
class ProgressDialogUtils {
    private var mDialog: Dialog? = null

    companion object{
        @Volatile
        private var INSTANCE: ProgressDialogUtils? = null

        fun getInstance(): ProgressDialogUtils {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = ProgressDialogUtils()
                INSTANCE = instance
                return instance
            }
        }
    }

    fun showProgressDialog(context: Context, message: String, cancelable: Boolean) {
        mDialog?.let { if (it.isShowing) { return } }

        mDialog = Dialog(context, R.style.FullScreenDialogStyle)
        mDialog?.let {
            it.requestWindowFeature(Window.FEATURE_NO_TITLE)
            it.setContentView(R.layout.dialog_progress)

            val progressBar = it.findViewById<ProgressBar>(R.id.pb_progress_bar)

            progressBar.indeterminateDrawable.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                ), android.graphics.PorterDuff.Mode.SRC_IN
            )

            val progressText = it.findViewById<TextView>(R.id.tv_progress_report)
            progressText.text = message
            it.setCancelable(cancelable)
            it.setCanceledOnTouchOutside(cancelable)
            it.show()
        }
    }

    fun dismissProgressDialog() {
        mDialog?.let {
            if (it.isShowing) {
                it.dismiss()
                mDialog = null

            }
        }
    }
}