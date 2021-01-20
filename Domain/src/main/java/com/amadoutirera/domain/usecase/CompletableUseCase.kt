package com.amadoutirera.domain.usecase

import com.amadoutirera.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Param> constructor(private val postExecutionThread: PostExecutionThread) {
    private  val disposable = CompositeDisposable()

    abstract fun buildUseCaseCompletable(param: Param? = null): Completable

    open fun execute(observer: DisposableCompletableObserver, param: Param? = null){
        val completable = this.buildUseCaseCompletable(param)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposale(completable.subscribeWith(observer))
    }


    fun addDisposale(dsiposable: Disposable){
       disposable.add(dsiposable)
    }

    fun dispos(){
        if (!disposable.isDisposed()) disposable.dispose()
    }

}