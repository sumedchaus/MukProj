package com.example.mukproj.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mukproj.databinding.ItemCarouselBinding
import com.example.mukproj.model.Product
import com.squareup.picasso.Picasso


class CarouselAdapter(private var images: List<Product>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    class CarouselViewHolder(val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        Picasso.get().load(images[position].featuredImage).into(holder.binding.imageView)
    }

    override fun getItemCount() = images.size

    fun updateImages(newImages: List<Product>) {
        images = newImages
        notifyDataSetChanged()
    }
}
