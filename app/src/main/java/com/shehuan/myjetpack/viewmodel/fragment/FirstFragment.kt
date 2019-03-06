package com.shehuan.myjetpack.viewmodel.fragment


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shehuan.myjetpack.R
import com.shehuan.myjetpack.viewmodel.model.IntViewModel
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {
    private var count = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = activity?.run { ViewModelProviders.of(this).get(IntViewModel::class.java) }
            ?: throw Exception("Invalid Activity")
        viewModel.liveData.observe(this, Observer {
            firstTv.text = it.toString()
        })

        firstPlusBtn.setOnClickListener {
            viewModel.liveData.postValue(++count)
        }

        firstMinusBtn.setOnClickListener {
            viewModel.liveData.postValue(--count)
        }
    }
}
