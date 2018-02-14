package com.quickbirdstudios.rx.databinding

import android.databinding.ObservableShort
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Created by sebastiansellmair on 14.02.18.
 */
class BindableShort() : ObservableShort() {
    private var subscription: Disposable? = null
    private val subject: Subject<Short> = BehaviorSubject.create()

    constructor(source: Observable<Short>, initial: Short = 0) : this() {
        this.set(initial)
        subscription = source.subscribe { newValue ->
            super.set(newValue)
            subject.onNext(newValue)
        }
    }

    override fun set(value: Short) {
        super.set(value)
        subject.onNext(value)
    }

    fun toObservable(): Observable<Short> = subject
}