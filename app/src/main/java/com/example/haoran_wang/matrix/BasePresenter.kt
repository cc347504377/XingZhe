package com.example.haoran_wang.matrix

open class BasePresenter<T> {

    protected var mView: T? = null

    fun onBind(view: T) {
        mView = view
    }

    fun unBind() {
        mView = null
    }
}