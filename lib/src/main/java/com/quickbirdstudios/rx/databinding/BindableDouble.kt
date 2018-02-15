package com.quickbirdstudios.rx.databinding

import android.databinding.ObservableDouble
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Created by sebastiansellmair on 14.02.18.
 */
class BindableDouble() : ObservableDouble(), Disposable {
    private var subscription: Disposable? = null
    private val subject: Subject<Double> = BehaviorSubject.create()

    constructor(source: Observable<Double>, initial: Double = 0.0) : this() {
        this.set(initial)
        subscription = source.subscribe { newValue ->
            super.set(newValue)
            subject.onNext(newValue)
        }
    }

    constructor(initial: Double = 0.0) : this() {
        set(initial)
    }

    override fun set(value: Double) {
        super.set(value)
        subject.onNext(value)
    }

    fun toObservable(): Observable<Double> = subject

    override fun isDisposed(): Boolean {
        return subscription?.isDisposed ?: false
    }

    override fun dispose() {
        subscription?.dispose()
    }
}