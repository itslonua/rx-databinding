package com.quickbirdstudios.rx.databinding

import com.quickbirdstudios.rx.DisposeBag
import com.quickbirdstudios.rx.Optional
import com.quickbirdstudios.rx.extension.disposedBy
import com.quickbirdstudios.rx.extension.filterNotNull
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Created by sebastiansellmair on 14.02.18.
 */

internal fun <T : Disposable> T.optDisposedBy(disposeBag: DisposeBag?): T {
    return if (disposeBag != null) this.disposedBy(disposeBag) else this
}


@JvmName("toBindable")
fun <T> Observable<T>.toBindable(disposeBag: DisposeBag? = null): BindableField<T> =
        BindableField.from(this).optDisposedBy(disposeBag)


@JvmName("toBindableOptional")
fun <T> Observable<Optional<T>>.toBindable(disposeBag: DisposeBag? = null): BindableField<T> =
        BindableField.from(this).optDisposedBy(disposeBag)


@JvmName("toBindable")
fun Observable<Int>.toBindable(disposeBag: DisposeBag?) =
        BindableInt(this).optDisposedBy(disposeBag)

@JvmName("toBindableOptional")
fun Observable<Optional<Int>>.toBindable(disposeBag: DisposeBag? = null) =
        BindableInt(this.filterNotNull()).optDisposedBy(disposeBag)


@JvmName("toBindable")
fun Observable<Boolean>.toBindable(disposeBag: DisposeBag? = null) =
        BindableBoolean(this).optDisposedBy(disposeBag)


@JvmName("toBindableOptional")
fun Observable<Optional<Boolean>>.toBindable(disposeBag: DisposeBag? = null) =
        BindableBoolean(this.filterNotNull()).optDisposedBy(disposeBag)


@JvmName("toBindable")
fun Observable<Float>.toBindable(disposeBag: DisposeBag? = null) =
        BindableFloat(this).optDisposedBy(disposeBag)

@JvmName("toBindableOptional")
fun Observable<Optional<Float>>.toBindable(disposeBag: DisposeBag? = null) =
        BindableFloat(this.filterNotNull()).optDisposedBy(disposeBag)


@JvmName("toBindable")
fun Observable<Double>.toBindable(disposeBag: DisposeBag? = null) =
        BindableDouble(this).optDisposedBy(disposeBag)

@JvmName("toBindableOptional")
fun Observable<Optional<Double>>.toBindable(disposeBag: DisposeBag? = null) =
        BindableDouble(this.filterNotNull()).optDisposedBy(disposeBag)


@JvmName("toBindable")
fun Observable<Short>.toBindable(disposeBag: DisposeBag? = null) =
        BindableShort(this).optDisposedBy(disposeBag)


@JvmName("toBindableOptional")
fun Observable<Optional<Short>>.toBindable(disposeBag: DisposeBag? = null) =
        BindableShort(this.filterNotNull()).optDisposedBy(disposeBag)

@JvmName("toBindable")
fun Observable<Long>.toBindable(disposeBag: DisposeBag?) =
        BindableLong(this).optDisposedBy(disposeBag)

@JvmName("toBindableOptional")
fun Observable<Optional<Long>>.toBindable(disposeBag: DisposeBag? = null) =
        BindableLong(this.filterNotNull()).optDisposedBy(disposeBag)

