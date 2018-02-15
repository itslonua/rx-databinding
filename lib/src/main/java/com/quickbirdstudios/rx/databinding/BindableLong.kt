package com.quickbirdstudios.rx.databinding

import android.databinding.ObservableLong
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Created by sebastiansellmair on 14.02.18.
 */
class BindableLong() : ObservableLong(), Disposable {
    private var subscription: Disposable? = null
    private val subject: Subject<Long> = BehaviorSubject.create()

    constructor(source: Observable<Long>, initial: Long = 0) : this() {
        this.set(initial)
        subscription = source.subscribe { newValue ->
            super.set(newValue)
            subject.onNext(newValue)
        }
    }

    constructor(initial: Long = 0) : this() {
        set(initial)
    }

    override fun set(value: Long) {
        super.set(value)
        subject.onNext(value)
    }

    fun toObservable(): Observable<Long> = subject

    override fun isDisposed(): Boolean {
        return subscription?.isDisposed ?: false
    }

    override fun dispose() {
        subscription?.dispose()
    }
}