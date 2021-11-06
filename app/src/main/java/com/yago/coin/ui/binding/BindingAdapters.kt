package com.yago.coin.ui.binding

import android.view.View
import android.webkit.WebView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("loadHtmlString")
    fun loadHtmlString(webView: WebView, html: String?) {
        html?.let {
            webView.loadData(html, "text/html", "UTF-8")
        }
    }

}