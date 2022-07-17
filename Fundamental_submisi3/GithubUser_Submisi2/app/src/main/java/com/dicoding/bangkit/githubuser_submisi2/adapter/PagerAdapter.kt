package com.dicoding.bangkit.githubuser_submisi2.adapter




import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.bangkit.githubuser_submisi2.R
import com.dicoding.bangkit.githubuser_submisi2.main.fragment.fragmentFollowers
import com.dicoding.bangkit.githubuser_submisi2.main.fragment.fragmentFollowing


class PagerAdapter (private val konteks : Context, fm : FragmentManager, data: Bundle) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private var fragmentBundle :  Bundle

    init {
        fragmentBundle = data
    }
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_followers, R.string.tab_following)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
    var fragmen: Fragment? = null
    when (position) {
        0 -> fragmen = fragmentFollowers()
        1 -> fragmen = fragmentFollowing()

    }
        fragmen?.arguments = this.fragmentBundle
    return fragmen as Fragment
}

    override fun getPageTitle(position: Int): CharSequence? {
    return konteks.resources.getString(TAB_TITLES[position])

}
}






