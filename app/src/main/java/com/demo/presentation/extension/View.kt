package com.demo.presentation.extension

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.lang.StringBuilder

fun View.setViewVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.show() { this.visibility = View.VISIBLE }

fun View.gone() { this.visibility = View.GONE }

fun ViewGroup.show() { this.visibility = View.VISIBLE }

fun ViewGroup.gone() { this.visibility = View.GONE }

fun TextView.setTextWithVisibility(str: String?) {
    text = if (str.isNullOrEmpty() || str.isNullOrBlank()) {
        gone()
        ""
    } else {
        show()
        str
    }
}

fun TextView.setModelData(tittle: String, value: String?) {
    this.text = SpannableStringBuilder(tittle).apply {
        value?.let {
            if (it.isNotEmpty() && it.isNotBlank()) {
                append(it)
            }
        }
        setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            tittle.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
    }
}

fun TextView.setDataUpToTwoDigits(num: Double) {
    num.toString().apply {
        if (contains(".")) {
            val sb = StringBuilder(substringBefore("."))
            val charArray = split(".")
            if (charArray.size == 2) {
                sb.append(".")
                when {
                    charArray[1].length > 2 -> {
                        sb.append(charArray[1].substring(0, 2))
                    }
                    else -> {
                        sb.append(charArray[1])
                    }
                }
            }
            text = sb
        }
    }

}











