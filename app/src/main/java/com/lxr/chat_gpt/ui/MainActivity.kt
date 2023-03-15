package com.lxr.chat_gpt.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.dyne.mj.ext.errorMsg
import com.dyne.myktdemo.base.BaseActivity
import com.lxr.chat_gpt.R
import com.lxr.chat_gpt.constants.URL
import com.lxr.chat_gpt.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.awaitResult

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val fragments = listOf<Fragment>(
        HomeFragment(),
        SettingFragment()
    )

    override fun initView() {
        binding.vp2.adapter = object : FragmentStateAdapter(this@MainActivity) {
            override fun getItemCount(): Int = fragments.size

            override fun createFragment(position: Int): Fragment = fragments[position]
        }

        binding.vp2.isUserInputEnabled = false
        binding.bottomNav.setOnNavigationItemSelectedListener {
            binding.vp2.currentItem = it.order
            true
        }
    }

    var exitTime = 0L
    override fun onBackPressed() {
        // 是主页
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showShort("再按一次退出程序")
            exitTime = System.currentTimeMillis()
        } else {
            ActivityUtils.finishAllActivities(true)
        }
    }
}
