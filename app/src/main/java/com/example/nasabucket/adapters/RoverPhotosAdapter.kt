package com.example.nasabucket.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasabucket.R

import com.example.nasabucket.data.Photos

import kotlinx.android.synthetic.main.fragment_photos_item.view.*

class RoverPhotosAdapter(
) : RecyclerView.Adapter<RoverPhotosAdapter.ViewHolder>() {


    var data = listOf<Photos>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_photos_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.mIdView.text = item.rover.name
        holder.cameraName.text = item.camera.full_name
        Glide.with(holder.mImageView.context).load(item.img_src).into(holder.mImageView)

        holder.mListener.setOnClickListener {
            Log.d("click", "clickclickclick")
        }

    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val cameraName: TextView = mView.item_camera_name
        val mImageView: ImageView = mView.item_image
        val mListener: CardView = mView.cardViewItem

        override fun toString(): String {
            return super.toString() + " '" + cameraName.text + "'"
        }
    }
}
