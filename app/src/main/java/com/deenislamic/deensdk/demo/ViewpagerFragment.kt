package com.deenislamic.deensdk.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class ViewpagerFragment : Fragment() {

    private lateinit var mPageDestination: ArrayList<Fragment>
    private lateinit var _viewPager: ViewPager2
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter
    private lateinit var btn1:Button
    private lateinit var btn2:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mainview = inflater.inflate(R.layout.fragment_viewpager, container, false)
        _viewPager = mainview.findViewById(R.id.viewPager)
        btn1 = mainview.findViewById(R.id.btn1)
        btn2 = mainview.findViewById(R.id.btn2)

        return mainview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPageDestination = arrayListOf(
            DeenSdkFragment(),
            DeenSdkAltFragment()
        )

        mainViewPagerAdapter = MainViewPagerAdapter(
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle,
            mPageDestination
        )

        _viewPager.apply {
            adapter = mainViewPagerAdapter
            isUserInputEnabled = true
            overScrollMode = View.OVER_SCROLL_NEVER
            offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT

        }

       /* _viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val fragmentManager = childFragmentManager
                val fragments = fragmentManager.fragments
                fragments.forEachIndexed { index, fragment ->
                    if (index != position && fragment != null) {
                        // Remove fragments not currently visible
                        fragmentManager.beginTransaction().remove(fragment).commit()
                    }
                }

                fragmentManager.executePendingTransactions()
            }
        })*/



        btn1.setOnClickListener {
            _viewPager.setCurrentItem(0)
        }

        btn2.setOnClickListener {
            _viewPager.setCurrentItem(2)
        }


    }

}