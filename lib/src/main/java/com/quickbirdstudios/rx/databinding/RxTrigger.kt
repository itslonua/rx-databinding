@file:Suppress("MemberVisibilityCanBePrivate")

package com.quickbirdstudios.rx.databinding

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Created by sebastiansellmair on 14.02.18.
 */

class RxTrigger {

    private val subject: Subject<Unit> = PublishSubject.create()

    fun trigger() = subject.onNext(Unit)

    fun toObservable() = trigger()

    operator fun invoke() {
        this.trigger()
    }
}
