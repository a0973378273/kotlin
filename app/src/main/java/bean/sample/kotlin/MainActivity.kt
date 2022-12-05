package bean.sample.kotlin

import android.widget.TableRow
import bean.sample.kotlin.databinding.ActivityMainBinding
import priv.jb.base.basic.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(){
    override fun initAction() {
    }

    override fun initView() {
        binding.tableLayout.addView(TableRow(this).apply {

        })
    }

    override fun init() {
    }
}