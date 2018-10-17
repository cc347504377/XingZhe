package com.example.haoran_wang.matrix

import android.os.Handler

object MatrixPresenter : BasePresenter<MatrixPresenter.MatrixView>() {

    interface MatrixView {
        fun onCalculated(count: Int)
    }

    fun calculationContain(handler: Handler?) {
        MatrixModel.calculationContain {
            handler?.post {
                mView?.onCalculated(it)
            }
        }
    }

}