package com.quickbirdstudios.rx.databinding

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by sebastiansellmair on 14.02.18.
 */
@BindingAdapter("quick:onClick")
fun onClick(view: View, trigger: RxTrigger) {
    view.setOnClickListener {
        trigger()
    }
}