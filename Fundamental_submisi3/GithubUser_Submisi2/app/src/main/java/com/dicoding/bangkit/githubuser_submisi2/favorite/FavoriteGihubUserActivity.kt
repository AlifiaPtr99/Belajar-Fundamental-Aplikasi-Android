package com.dicoding.bangkit.githubuser_submisi2.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bangkit.githubuser_submisi2.R
import com.dicoding.bangkit.githubuser_submisi2.aboutDetail.DetailGithubUserActivity
import com.dicoding.bangkit.githubuser_submisi2.aboutDetail.DetailGithubUserActivity.Companion.EXTRA_USERNAME
import com.dicoding.bangkit.githubuser_submisi2.adapter.RecyclerViewAdapter
import com.dicoding.bangkit.githubuser_submisi2.data.UserFav
import com.dicoding.bangkit.githubuser_submisi2.dataClass.GithubUser
import com.dicoding.bangkit.githubuser_submisi2.databinding.ActivityFavoriteGihubUserBinding
import com.dicoding.bangkit.githubuser_submisi2.main.MainActivity

class FavoriteGihubUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteGihubUserBinding
    private lateinit var adapter1: RecyclerViewAdapter
    private lateinit var vmFav : FavoriteGithubUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavoriteGihubUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter1 = RecyclerViewAdapter()
        adapter1.notifyDataSetChanged()
        vmFav = ViewModelProvider(this).get(FavoriteGithubUserViewModel::class.java)

        adapter1.setOnItemClickCallback(object : RecyclerViewAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GithubUser) {

                val bundleMain = Bundle()
                bundleMain.putString(MainActivity.EXTRA_USERNAME, data.login)
                bundleMain.putInt(DetailGithubUserActivity.EXTRA_ID, data.id)
                bundleMain.putString(DetailGithubUserActivity.EXTRA_AVATAR, data.avatar_url)

                val favIntent = Intent(this@FavoriteGihubUserActivity, DetailGithubUserActivity::class.java)
                favIntent.putExtras(bundleMain)
                startActivity(favIntent)
            }


        })

        binding.apply {
            rvHome.setHasFixedSize(true)
            rvHome.layoutManager = LinearLayoutManager(this@FavoriteGihubUserActivity)
            rvHome.adapter = adapter1
        }

        vmFav.getFavUser()?.observe(this,{
            if (it!=null){
                val listFav = mapList(it)
                adapter1.setListUser(listFav)
            }
        })
    }

    private fun mapList(userFavorite: List<UserFav>): ArrayList<GithubUser>{
        val listGithubUser = ArrayList<GithubUser>()
        for (user in userFavorite){
            val userFavMapped = GithubUser(
                user.login,
                user.avatar_url,
                user.id
            )
            listGithubUser.add(userFavMapped)
        }
        return listGithubUser
    }

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

}