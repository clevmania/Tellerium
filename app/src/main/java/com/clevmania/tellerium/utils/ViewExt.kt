package com.clevmania.tellerium.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
fun View.makeVisible(){ this.visibility = View.VISIBLE }

fun View.makeGone(){ this.visibility = View.GONE }

fun View.makeInVisible() { this.visibility = View.INVISIBLE}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun EditText.onActionDone(actionToPerform: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            actionToPerform.invoke()
            true
        }else{
            false
        }
    }
}