package com.yago.coin.ui.binding

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.yago.coin.R
import timber.log.Timber
import javax.inject.Inject

class FragmentBindingAdapters @Inject constructor(private val context: Context) {

    @BindingAdapter("imageUrl")
    fun bindImage(imageView: AppCompatImageView, url: String?) {
        if (url != null) {
            Glide.with(context).load(url).listener(getRequestListener()).into(imageView).waitForLayout()
        } else {
            Glide.with(context).load(R.mipmap.placeholder_empty_image).listener(getRequestListener()).into(imageView).waitForLayout()
        }
    }

    @BindingAdapter("bindImageUrlWithoutPlaceholder")
    fun bindImageUrlWithoutPlaceholder(imageView: AppCompatImageView, url: String?) {
        if (url != null) {
            Glide.with(context).load(url).error(Glide.with(imageView).load(R.mipmap.placeholder_empty_image)).listener(getRequestListener()).into(imageView)
                .waitForLayout()
        }
    }

    private fun getRequestListener(): RequestListener<Drawable> {
        return object : RequestListener<Drawable> {
            override fun onLoadFailed(@Nullable e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                Timber.w(e)
                return false
            }

            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean) = false

        }
    }

}
