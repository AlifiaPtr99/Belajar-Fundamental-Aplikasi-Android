package com.dicoding.bangkit.githubuser_submisi2.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bangkit.githubuser_submisi2.R
import com.dicoding.bangkit.githubuser_submisi2.aboutDetail.DetailGithubUserActivity
import com.dicoding.bangkit.githubuser_submisi2.adapter.RecyclerViewAdapter
import com.dicoding.bangkit.githubuser_submisi2.dataClass.GithubUser
import com.dicoding.bangkit.githubuser_submisi2.databinding.FragFollowersFollowingBinding
import com.dicoding.bangkit.githubuser_submisi2.viewModel.ViewModelFollowers


class fragmentFollowers : Fragment(R.layout.frag_followers_following) {
    private var _binding: FragFollowersFollowingBinding? = null
    private val bindingFollow get() = _binding!!
    private lateinit var adapterFoll: RecyclerViewAdapter
    private lateinit var vmFoll: ViewModelFollowers
    private lateinit var uname: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argumen = arguments
        uname = argumen?.getString(DetailGithubUserActivity.EXTRA_USERNAME).toString()

       _binding = FragFollowersFollowingBinding.bind(view)
        adapterFoll = RecyclerViewAdapter()
        adapterFoll.notifyDataSetChanged()
  
        bindingFollow.apply {
            rvHome.setHasFixedSize(true)
            rvHome.layoutManager = LinearLayoutManager(activity)
            rvHome.adapter = adapterFoll
        }
        showProgressBar(true)
        vmFoll = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ViewModelFollowers::class.java)
        vmFoll.setUserFollowers(uname)
        vmFoll.getCariFollowersUser().observe(viewLifecycleOwner,{
            if (it != null)
                adapterFoll.setListUser(it)
                showProgressBar(false)
        })
}


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showProgressBar(state : Boolean){
        if (state){
            bindingFollow.pBar.visibility = View.VISIBLE
        }
        else {
            bindingFollow.pBar.visibility = View.GONE
        }
    }
}
