package com.quickbirdstudios.rx.databinding

import android.databinding.ObservableField
import com.quickbirdstudios.rx.Optional
import com.quickbirdstudios.rx.extension.box
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by sebastiansellmair on 14.02.18.
 */

open class BindableField<T>
private constructor(delegate: Disposable)
    : ObservableField<T>(), Disposable by delegate {


    private val subject = BehaviorSubject.create<Optional<T>>()


    override fun set(value: T?) {
        super.set(value)
        subject.onNext(value.box())
    }

    fun toObservable(): Observable<Optional<T>> = subject

    companion object {
        @JvmName("fromObservable")
        fun <T> from(source: Observable<T>): BindableField<T> = ObservableUpdater(source).bindableField

        @JvmName("fromOptionalObservable")
        fun <T> from(source: Observable<Optional<T>>): BindableField<T> = OptionalObservableUpdater(source).bindableField

        fun <T> just(t: T?): BindableField<T> = JustObservableUpdater(t).bindableField
    }


    internal interface Updater<T> : Disposable {
        val bindableField: BindableField<T>
    }


    internal class ObservableUpdater<T>(observable: Observable<T>) : Updater<T> {

        private val subscription: Disposable
        override val bindableField: BindableField<T> = BindableField(this)

        init {
            subscription = observable.subscribe(this::update)
        }

        private fun update(obj: T) {
            bindableField.set(obj)
        }

        override fun isDisposed() = subscription.isDisposed
        override fun dispose() = subscription.dispose()

    }

    internal class OptionalObservableUpdater<T>(observable: Observable<Optional<T>>) : Updater<T> {

        private val subscription: Disposable
        override val bindableField = BindableField<T>(this)

        init {
            subscription = observable.subscribe(this::update)
        }

        private fun update(optional: Optional<T>) {
            bindableField.set(optional.raw)
        }

        override fun isDisposed() = subscription.isDisposed
        override fun dispose() = subscription.dispose()

    }

    internal class JustObservableUpdater<T>(just: T?)
        : Updater<T> {

        override val bindableField = BindableField<T>(this)
        private var isDisposed = false

        init {
            bindableField.set(just)
        }

        override fun dispose() {
            synchronized(this) {
                dispose()
                isDisposed = true
            }
        }

        override fun isDisposed(): Boolean {
            synchronized(this) {
                return isDisposed
            }
        }

    }
}