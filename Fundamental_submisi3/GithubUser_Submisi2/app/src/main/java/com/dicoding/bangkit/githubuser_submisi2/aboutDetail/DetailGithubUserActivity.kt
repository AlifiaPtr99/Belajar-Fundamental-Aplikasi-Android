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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailGithubUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailGithubUserBinding
    private lateinit var vmdetail: ViewModelDetailGithubUser

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailGithubUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val unameFav: String? = intent.getStringExtra(EXTRA_USERNAME)
        val idFav : Int? = intent.getIntExtra(EXTRA_ID, 0)
        val avatarFav : String? = intent.getStringExtra(EXTRA_AVATAR)

        val bundles = Bundle()
        bundles.putString(EXTRA_USERNAME, unameFav)

        vmdetail = ViewModelProvider(this)[ViewModelDetailGithubUser::class.java]
        if (unameFav != null) {
            vmdetail.setDetailGithubUser(unameFav)
        }
            Log.d(TAG, "data tidak terkirim")
        vmdetail.getCariDetailUser().observe(this) {
            if (it != null) {
                binding.apply {
                    if (it.name != null) tvNamagithub.text = it.name
                    else tvNamagithub.text = "Anonim"
                    if (it.location != null) tvLokasi.text = it.location
                    else tvLokasi.text = "Location Unknown"
                    if (it.company != null) tvCompany.text = it.company
                    else tvCompany.text = "not have company"
                    if (it.bio != null) tvBiogithub.text = it.bio
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
        }

        var checkGithubUserFav = true
        CoroutineScope(Dispatchers.IO).launch {
            val count = vmdetail.check(idFav)
            withContext(Dispatchers.Main)  {
                if (count != null) {
                    if (count > 0) {
                        binding.tgFav.isChecked = true
                        checkGithubUserFav = true
                    }
                } else {
                    binding.tgFav.isChecked = false
                    checkGithubUserFav = false
                }
            }
        }
        binding.tgFav.setOnClickListener {
            checkGithubUserFav =! checkGithubUserFav
            if (checkGithubUserFav){
                vmdetail.add(unameFav!! , idFav!!, avatarFav!!)
            }
            else{
                vmdetail.remove(idFav!!)
            }
            binding.tgFav.isChecked = checkGithubUserFav
        }
        val sectionPagerAdapter = PagerAdapter(this, supportFragmentManager, bundles)
        binding.apply {
            vpDetail.adapter = sectionPagerAdapter
            tabDetail.setupWithViewPager(vpDetail)

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
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"
            }

    }







