package com.quickbirdstudios.rx.databinding

import android.databinding.ObservableFloat
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Created by sebastiansellmair on 14.02.18.
 */
class BindableFloat() : ObservableFloat(), Disposable {
    private var subscription: Disposable? = null
    private val subject: Subject<Float> = BehaviorSubject.create()

    constructor(source: Observable<Float>, initial: Float = 0f) : this() {
        this.set(initial)
        subscription = source.subscribe { newValue ->
            super.set(newValue)
            subject.onNext(newValue)
        }
    }

    constructor(initial: Float = 0f) : this() {
        set(initial)
    }

    override fun set(value: Float) {
        super.set(value)
        subject.onNext(value)
    }

    fun toObservable(): Observable<Float> = subject

    override fun isDisposed(): Boolean {
        return subscription?.isDisposed ?: false
    }

    override fun dispose() {
        subscription?.dispose()
    }
}