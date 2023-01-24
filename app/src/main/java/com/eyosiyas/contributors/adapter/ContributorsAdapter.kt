package com.eyosiyas.contributors.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.eyosiyas.contributors.R
import com.eyosiyas.contributors.databinding.ContributorItemBinding
import com.eyosiyas.contributors.model.Contributor

/* It's a RecyclerView.Adapter that takes a list of Contributor objects and displays them in a
RecyclerView */
class ContributorsAdapter(private val contributors: List<Contributor>) :
    RecyclerView.Adapter<ContributorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contributor_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contributors[position])
    }

    override fun getItemCount(): Int = contributors.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ContributorItemBinding.bind(itemView)

        /**
         * > The function takes a Contributor object and binds it to the view
         *
         * @param contributor Contributor - The data model that will be used to populate the view.
         */
        fun bind(contributor: Contributor) {
            binding.txtLoginName.text = contributor.login
            binding.imgAvatar.load(contributor.avatar) {
                crossfade(true)
                placeholder(R.mipmap.ic_launcher_round)
                memoryCachePolicy(CachePolicy.ENABLED)
                diskCachePolicy(CachePolicy.ENABLED)
            }
        }
    }
}