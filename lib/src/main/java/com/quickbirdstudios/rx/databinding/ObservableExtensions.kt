package com.quickbirdstudios.rx.databinding

import com.quickbirdstudios.rx.Optional
import com.quickbirdstudios.rx.extension.filterNotNull
import io.reactivex.Observable

/**
 * Created by sebastiansellmair on 14.02.18.
 */

@JvmName("toBindable")
fun <T> Observable<T>.toBindable(): BindableField<T> = BindableField.from(this)

@JvmName("toBindableOptional")
fun <T> Observable<Optional<T>>.toBindable(): BindableField<T> = BindableField.from(this)


@JvmName("toBindable")
fun Observable<Int>.toBindable() = BindableInt(this)

@JvmName("toBindableOptional")
fun Observable<Optional<Int>>.toBindable() = BindableInt(this.filterNotNull())


@JvmName("toBindable")
fun Observable<Boolean>.toBindable() = BindableBoolean(this)

@JvmName("toBindableOptional")
fun Observable<Optional<Boolean>>.toBindable() = BindableBoolean(this.filterNotNull())


@JvmName("toBindable")
fun Observable<Float>.toBindable() = BindableFloat(this)

@JvmName("toBindableOptional")
fun Observable<Optional<Float>>.toBindable() = BindableFloat(this.filterNotNull())


@JvmName("toBindable")
fun Observable<Double>.toBindable() = BindableDouble(this)

@JvmName("toBindableOptional")
fun Observable<Optional<Double>>.toBindable() = BindableDouble(this.filterNotNull())


@JvmName("toBindable")
fun Observable<Short>.toBindable() = BindableShort(this)


@JvmName("toBindableOptional")
fun Observable<Optional<Short>>.toBindable() = BindableShort(this.filterNotNull())

@JvmName("toBindable")
fun Observable<Long>.toBindable() = BindableLong(this)

@JvmName("toBindableOptional")
fun Observable<Optional<Long>>.toBindable() = BindableLong(this.filterNotNull())

