package com.express.android.lwbooksearchrxjava.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.express.android.lwbooksearchrxjava.network.BookListModel
import com.express.android.lwbooksearchrxjava.network.RetroInstance
import com.express.android.lwbooksearchrxjava.network.RetroService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {

    lateinit var bookList: MutableLiveData<BookListModel>

    init {
        bookList = MutableLiveData()
    }

    fun getBookListObserver(): MutableLiveData<BookListModel> {
        return bookList
    }

    fun makeApiCall(query: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.getBookListFromApi(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBookListObserverRx())
    }

    fun getBookListObserverRx(): Observer<BookListModel> {
        return object :Observer<BookListModel>{
            override fun onComplete() {

            }

            override fun onError(e: Throwable) {
                bookList.postValue(null)
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BookListModel) {
                bookList.postValue(t)
            }
        }

    }
}