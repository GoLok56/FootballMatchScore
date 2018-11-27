package io.github.golok56.footballmatchscore.base

import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class GlideAppModule : AppGlideModule()

fun ImageView.load(url: String, circle: Boolean = false) {
    val glide = GlideApp.with(context).load(url)

    if (circle) glide.apply(RequestOptions.circleCropTransform())

    glide.into(this)
}

fun ImageView.load(id: Int, circle: Boolean = false) {
    val glide = GlideApp.with(context).load(id)

    if (circle) glide.apply(RequestOptions.circleCropTransform())

    glide.into(this)
}
