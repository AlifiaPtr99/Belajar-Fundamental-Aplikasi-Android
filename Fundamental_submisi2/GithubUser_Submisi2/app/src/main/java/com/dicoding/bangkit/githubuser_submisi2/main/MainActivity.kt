package com.dicoding.bangkit.githubuser_submisi2.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bangkit.githubuser_submisi2.dataClass.GithubUser
import com.dicoding.bangkit.githubuser_submisi2.adapter.RecyclerViewAdapter
import com.dicoding.bangkit.githubuser_submisi2.viewModel.ViewModelMain
import com.dicoding.bangkit.githubuser_submisi2.aboutDetail.DetailGithubUserActivity
import com.dicoding.bangkit.githubuser_submisi2.databinding.ActivityMainBinding
import com.dicoding.bangkit.githubuser_submisi2.viewModel.ViewModelFollowers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: ViewModelMain
    private lateinit var adapter1: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        adapter1 = RecyclerViewAdapter()
        adapter1.setOnItemClickCallback(object : RecyclerViewAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GithubUser) {

                val bundleMain = Bundle()
                bundleMain.putString(EXTRA_USERNAME, data.login)

                val detailIntent = Intent(this@MainActivity, DetailGithubUserActivity::class.java)
                detailIntent.putExtras(bundleMain)
                startActivity(detailIntent)


        adapter1.notifyDataSetChanged()

            }
        })

        vm = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ViewModelMain::class.java)

        binding.apply {
            rvHome.layoutManager = LinearLayoutManager(this@MainActivity)
            rvHome.setHasFixedSize(true)
            rvHome.adapter = adapter1


            btnCari.setOnClickListener {
               cariUser()
            }
            etEdit.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    cariUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }

        }
        vm.getCariGithubUser().observe(this, {
            if (it != null){
                adapter1.setListUser(it)
            showProgressBar(false)
        }
        })
    }
    fun cariUser(){
        binding.apply{
            val kataBenda = etEdit.text.toString()
            if (kataBenda != null)
                showProgressBar(true)
            else
                showProgressBar(false)
            vm.setGithubUser(kataBenda)
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



