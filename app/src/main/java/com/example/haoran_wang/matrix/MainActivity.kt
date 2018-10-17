package com.example.haoran_wang.matrix

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), MatrixPresenter.MatrixView {

    override fun onCalculated(count: Int) {
        textView.text = "次数：$count"
    }

    private val handler = WeakReference<Handler>(Handler())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MatrixPresenter.onBind(this)
        initListener()
    }

    private fun initListener() {
        button.setOnClickListener {
            calculation()
        }
    }

    private fun calculation() {
        handler.get()?.let {
            MatrixPresenter.calculationContain(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MatrixPresenter.unBind()
    }
}
