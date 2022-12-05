package bean.sample.kotlin

import android.util.Log
import bean.sample.kotlin.databinding.FragmentSpaceBinding
import priv.jb.base.basic.BaseFragment

class PrintExceptionFragment : BaseFragment<FragmentSpaceBinding>() {

    override fun init() {}

    override fun initAction() {
        try {
            throw Exception("test print exception")
        } catch (exception: Exception) {
//            Log.d(localClassName, "1")
            exception.printStackTrace()
//            Log.d(localClassName, "2")
//            Log.d(localClassName, exception.stackTraceToString())
//            Log.d(localClassName, "3")
//            Log.d(localClassName, exception.toString())
//            Log.d(localClassName, "4")
//            exception.message?.let { Log.d(localClassName, it) }
        }
    }

    override fun initView() {
    }
}