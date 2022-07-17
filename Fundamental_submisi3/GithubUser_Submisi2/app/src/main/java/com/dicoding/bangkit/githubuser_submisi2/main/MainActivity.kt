package com.dicoding.bangkit.githubuser_submisi2.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bangkit.githubuser_submisi2.R
import com.dicoding.bangkit.githubuser_submisi2.dataClass.GithubUser
import com.dicoding.bangkit.githubuser_submisi2.adapter.RecyclerViewAdapter
import com.dicoding.bangkit.githubuser_submisi2.viewModel.ViewModelMain
import com.dicoding.bangkit.githubuser_submisi2.aboutDetail.DetailGithubUserActivity
import com.dicoding.bangkit.githubuser_submisi2.aboutDetail.DetailGithubUserActivity.Companion.EXTRA_AVATAR
import com.dicoding.bangkit.githubuser_submisi2.aboutDetail.DetailGithubUserActivity.Companion.EXTRA_ID
import com.dicoding.bangkit.githubuser_submisi2.databinding.ActivityMainBinding
import com.dicoding.bangkit.githubuser_submisi2.favorite.FavoriteGihubUserActivity
import com.dicoding.bangkit.githubuser_submisi2.theme.*
import com.dicoding.bangkit.githubuser_submisi2.viewModel.ViewModelDetailGithubUser

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: ViewModelMain
    private lateinit var adapter1: RecyclerViewAdapter


    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        adapter1 = RecyclerViewAdapter()
        adapter1.setOnItemClickCallback(object : RecyclerViewAdapter.OnItemClickCallback {
            override fun onItemClicked(data : GithubUser) {

                val bundleMain = Bundle()
                bundleMain.putString(EXTRA_USERNAME, data.login)
                bundleMain.putInt(EXTRA_ID, data.id)
                bundleMain.putString(EXTRA_AVATAR, data.avatar_url)

                val detailIntent = Intent(this@MainActivity, DetailGithubUserActivity::class.java)
                detailIntent.putExtras(bundleMain)
                startActivity(detailIntent)


        adapter1.notifyDataSetChanged()

            }
        })

        vm = ViewModelProvider(this).get(ViewModelMain::class.java)

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
            theme()
    }
    private fun cariUser(){
        binding.apply{
            val txt = etEdit.text.toString()
            if (txt != null)
                showProgressBar(true)
            else
                showProgressBar(false)
            vm.setGithubUser(txt)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_fav-> {
                Intent(this, FavoriteGihubUserActivity::class.java).also{
                    startActivity(it)
                }
                true
            }
            R.id.menu_theme->{
                Intent(this, ThemeActivity::class.java).also{
                    startActivity(it)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun theme() {
        val pref = SettingPreferences.getInstance(dataStore)
        val vmTheme =
            ViewModelProvider(this, ViewModelFactory(pref)).get(ThemeViewModel::class.java)

        vmTheme.getThemeSettings().observe(
            this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}



