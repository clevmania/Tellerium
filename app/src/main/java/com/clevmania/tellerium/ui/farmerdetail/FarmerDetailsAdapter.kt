package com.clevmania.tellerium.ui.farmerdetail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.clevmania.tellerium.ui.farm.CaptureFarmFragment
import com.clevmania.tellerium.ui.identity.IdentityFragment
import com.clevmania.tellerium.ui.personal.PersonalFragment
import com.clevmania.tellerium.utils.Constants.FARM_PAGE_INDEX
import com.clevmania.tellerium.utils.Constants.IDENTITY_PAGE_INDEX
import com.clevmania.tellerium.utils.Constants.PERSONAL_PAGE_INDEX

/**
 * @author by Lawrence on 10/25/20.
 * for Tellerium
 */
class FarmerDetailsAdapter(fragment: Fragment, viewModel: FarmerDetailViewModel) : FragmentStateAdapter(fragment) {
    private val tabCreators: Map<Int, () -> Fragment> = mapOf(
        PERSONAL_PAGE_INDEX to { PersonalFragment.newInstance(viewModel) },
        IDENTITY_PAGE_INDEX to { IdentityFragment.newInstance(viewModel) },
        FARM_PAGE_INDEX to { CaptureFarmFragment.newInstance(viewModel) }
    )

    override fun getItemCount() = tabCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}