package com.clevmania.tellerium.ui.update.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.clevmania.tellerium.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.success_fragment.*

class SuccessFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.success_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mbDone.setOnClickListener {
            dismiss()
        }
        mbViewDashboard.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.action_successFragment_to_dashBoardFragment)
        }
    }

}