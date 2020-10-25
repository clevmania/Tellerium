package com.clevmania.tellerium.ui.farmer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clevmania.tellerium.ui.farmer.model.Farmer
import com.clevmania.tellerium.R
import com.clevmania.tellerium.utils.Constants
import com.clevmania.tellerium.utils.loadImage
import kotlinx.android.synthetic.main.item_farmer.view.*

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
class FarmerAdapter(private val farmersList: List<Farmer>): RecyclerView.Adapter<FarmerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(farmer: Farmer) {
            itemView.tvFarmerName.text = itemView.context.getString(
                R.string.farmers_full_name,farmer.first_name,farmer.surname)
            itemView.tvFarmerMobile.text = farmer.mobile_no
            itemView.tvLocale.text = farmer.lga
            itemView.ivFarmerImg.loadImage(prepareImageLink(farmer.passport_photo))
        }

        private fun prepareImageLink(link: String): String{
            return "${Constants.imageBaseUrl}$link"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_farmer,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = farmersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(farmersList[position])
    }
}