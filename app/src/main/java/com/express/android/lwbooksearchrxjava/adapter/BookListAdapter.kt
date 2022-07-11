package com.express.android.lwbooksearchrxjava.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.express.android.lwbooksearchrxjava.R
import com.express.android.lwbooksearchrxjava.network.VolumeInfo
import com.bumptech.glide.Glide
import com.express.android.lwbooksearchrxjava.databinding.RecyclerListRowBinding

class BookListAdapter: RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {

    var bookListData = ArrayList<VolumeInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.MyViewHolder {
        return MyViewHolder(
            RecyclerListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookListAdapter.MyViewHolder, position: Int) {
        holder.bind(bookListData[position])
    }

    override fun getItemCount(): Int {
        return bookListData.size
    }

    class MyViewHolder(val binding: RecyclerListRowBinding): RecyclerView.ViewHolder(binding.root) {

        val tvTitle = binding.tvTitle
        val tvPublisher = binding.tvPublisher
        val tvDescription = binding.tvDescription
        val thumbImageView = binding.thumbImageView

        fun bind(data : VolumeInfo){
            tvTitle.text = data.volumeInfo.title
            tvPublisher.text = data.volumeInfo.publisher
            tvDescription.text = data.volumeInfo.description

            val url  = data.volumeInfo?.imageLinks.smallThumbnail
            Glide.with(thumbImageView)
                .load(url)
                .circleCrop()
                .into(thumbImageView)
        }
    }
}