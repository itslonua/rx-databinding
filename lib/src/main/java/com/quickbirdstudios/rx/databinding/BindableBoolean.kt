package com.quickbirdstudios.rx.databinding

import android.databinding.ObservableBoolean
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Created by sebastiansellmair on 14.02.18.
 */
class BindableBoolean() : ObservableBoolean() {
    private var subscription: Disposable? = null
    private val subject: Subject<Boolean> = BehaviorSubject.create()

    constructor(source: Observable<Boolean>, initial: Boolean = false) : this() {
        this.set(initial)
        subscription = source.subscribe { newValue ->
            super.set(newValue)
            subject.onNext(newValue)
        }
    }

    override fun set(value: Boolean) {
        super.set(value)
        subject.onNext(value)
    }

    fun toObservable(): Observable<Boolean> = subject
}