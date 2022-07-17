package com.dicoding.bangkit.githubuser_submisi2.aboutDetail

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.bangkit.githubuser_submisi2.adapter.PagerAdapter
import com.dicoding.bangkit.githubuser_submisi2.databinding.ActivityDetailGithubUserBinding
import com.dicoding.bangkit.githubuser_submisi2.viewModel.ViewModelDetailGithubUser


class DetailGithubUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailGithubUserBinding
    private lateinit var vmdetail: ViewModelDetailGithubUser

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailGithubUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //showProgressBar(true)

        val terimaData : String? =  intent.getStringExtra(EXTRA_USERNAME)
        val bundles = Bundle()
        bundles.putString(EXTRA_USERNAME, terimaData)
        vmdetail = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ViewModelDetailGithubUser::class.java
        )
        if (terimaData != null) {
            vmdetail.setDetailGithubUser(terimaData)
            Log.d(TAG, "data tidak terkirim")
        }
            vmdetail.getCariDetailUser().observe(this) {
                if (it != null) {
                    binding.apply {
                        if(it.name != null) tvNamagithub.text = it.name
                        else tvNamagithub.text = "Anonim"
                        if(it.location != null) tvLokasi.text = it.location
                        else tvLokasi.text = "Location Unknown"
                        if(it.company != null) tvCompany.text = it.company
                        else tvCompany.text = "not have company"
                        if(it.bio != null) tvBiogithub.text = it.bio
                        else tvBiogithub.text = "Bio is empty"
                        tvUnamegithub.text = "@${it.login}"
                        tvFollowers.text = "${it.followers} Followers"
                        tvFollowing.text = "${it.following} Following"
                        tvRepos.text = "${it.public_repos} Repository"

                        Glide.with(this@DetailGithubUserActivity)
                            .load(it.avatar_url)
                            .centerCrop()
                            .into(imgDetail)

                    }

                        showProgressBar(true)
                }





                    val sectionPagerAdapter = PagerAdapter(this, supportFragmentManager, bundles)
                    binding.apply {
                        vpDetail.adapter = sectionPagerAdapter
                        tabDetail.setupWithViewPager(vpDetail)

                    }

                }


            }
    private fun showProgressBar(state : Boolean){
        if (state){
            binding.pBar.visibility = View.VISIBLE
        }
        else {
            binding.pBar.visibility = View.GONE
        }
    }
    companion object {
        const val EXTRA_USERNAME = "extra_username"
            }

    }







