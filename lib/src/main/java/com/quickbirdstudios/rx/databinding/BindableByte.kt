package com.quickbirdstudios.rx.databinding

import android.databinding.ObservableByte
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Created by sebastiansellmair on 14.02.18.
 */
class BindableByte() : ObservableByte(), Disposable {
    private var subscription: Disposable? = null
    private val subject: Subject<Byte> = BehaviorSubject.create()

    constructor(source: Observable<Byte>, initial: Byte = 0) : this() {
        this.set(initial)
        subscription = source.subscribe { newValue ->
            super.set(newValue)
            subject.onNext(newValue)
        }
    }

    constructor(initial: Byte = 0) : this() {
        set(initial)
    }

    override fun set(value: Byte) {
        super.set(value)
        subject.onNext(value)
    }

    fun toObservable(): Observable<Byte> = subject

    override fun isDisposed(): Boolean {
        return subscription?.isDisposed ?: false
    }

    override fun dispose() {
        subscription?.dispose()
    }
}