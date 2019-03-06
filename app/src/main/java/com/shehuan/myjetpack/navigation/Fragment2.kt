package com.shehuan.myjetpack.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.shehuan.myjetpack.R
import kotlinx.android.synthetic.main.fragment2.*

class Fragment2 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment2Button1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_fragment2_to_fragment3)
        }

        fragment2Button2.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment2()
    }
}
