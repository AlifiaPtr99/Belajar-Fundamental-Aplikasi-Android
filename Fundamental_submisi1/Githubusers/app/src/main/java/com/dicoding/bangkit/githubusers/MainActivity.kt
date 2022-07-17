package com.dicoding.bangkit.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyView: RecyclerView
    private val list = ArrayList<GithubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyView = findViewById(R.id.rv_githubuser)
        recyView.setHasFixedSize(true)
        list.addAll(daftarData)
        showRecyclerList()
    }

    private val daftarData: ArrayList<GithubUser>
        get() {
            val devUname = resources.getStringArray(R.array.uname)
            val devName = resources.getStringArray(R.array.name)
            val devFoto = resources.obtainTypedArray(R.array.avatar)
            val devDeskripsi = resources.getStringArray(R.array.deskripsi)
            val listDaftarData = ArrayList<GithubUser>()
            for (i in devUname.indices) {
                val git1 = GithubUser(
                    devUname[i],
                    devName[i],
                    devFoto.getResourceId(i, -1),
                    devDeskripsi[i]
                )
                listDaftarData.add(git1)
            }
            return listDaftarData
        }


    private fun showRecyclerList() {
        recyView.layoutManager = LinearLayoutManager(this)
        val adapter1 = ListGithubUserAdapter(list)
        recyView.adapter = adapter1

        adapter1.setOnItemClickCallback(
            object : ListGithubUserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: GithubUser) {
                }
            })
    }
 
    }


