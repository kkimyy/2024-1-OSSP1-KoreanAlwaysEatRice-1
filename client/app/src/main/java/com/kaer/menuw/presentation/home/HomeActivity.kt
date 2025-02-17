package com.kaer.menuw.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.tabs.TabLayout
import com.kaer.menuw.R
import com.kaer.menuw.databinding.ActivityHomeBinding
import com.kaer.menuw.presentation.home.likemenu.LikeMenuListFragment
import com.kaer.menuw.presentation.home.refrigerator.RefrigeratorFragment
import com.kaer.menuw.presentation.mypage.MyPage
import com.kaer.menuw.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSetTabPage()
        clickTabItem()
        clickSetting()
    }

    private fun clickSetting() {
        val intent = Intent(this, MyPage::class.java)
        binding.ivHomeSetting.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun initSetTabPage() {
        supportFragmentManager.commit {
            replace(R.id.fcv_home_tab_main, RefrigeratorFragment())
        }
    }

    private fun clickTabItem() {
        binding.layoutHomeTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> replaceFragment(RefrigeratorFragment())
                    1 -> replaceFragment(LikeMenuListFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}


        })
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fcv_home_tab_main, fragment)
        }
    }
}