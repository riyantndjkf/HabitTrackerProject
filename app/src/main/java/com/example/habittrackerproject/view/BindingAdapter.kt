package com.example.habittrackerproject.view
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.habittrackerproject.R

@BindingAdapter("imageUrl")
fun loadIconImage(imageView: ImageView, iconName: String?) {
    if (iconName.isNullOrEmpty()) return
    val context = imageView.context
    // Usually mapping iconName to a drawable resource or loading via Glide/Picasso
    val resId = context.resources.getIdentifier(iconName.lowercase(), "drawable", context.packageName)
    if (resId != 0) {
        imageView.setImageResource(resId)
    } else {
        imageView.setImageResource(android.R.drawable.ic_menu_gallery)
    }
}
