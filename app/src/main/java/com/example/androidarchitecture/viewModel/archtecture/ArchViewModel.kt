package com.example.androidarchitecture.viewModel.archtecture

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit


class ArchViewModel : ViewModel() {

    val count: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    private var disposable: Disposable = Disposable.empty()

    fun increase() {
        disposable = Observable.interval(0, 1000L, TimeUnit.MILLISECONDS)
            .timeInterval()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("interval", it.value().toString())
                _counter.value = it.value().toInt()
            }
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }
}