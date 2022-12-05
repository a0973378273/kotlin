package bean.sample.kotlin

import android.app.Activity
import android.os.Bundle
import android.util.Log
import bean.sample.kotlin.databinding.FragmentSpaceBinding
import priv.jb.base.basic.BaseFragment

open class InheritFragment : BaseFragment<FragmentSpaceBinding>() {

    override fun init() {}

    override fun initAction() {}

    override fun initView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_space)
    }

    open fun inheritFunction() = "inherit"


//    class SubClass : InheritActivity() {
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            Log.d("inheritFunction", "${inheritFunction()}")
//        }
//
//        override fun inheritFunction(): String = "subFunction"
//    }
}