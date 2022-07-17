package com.dicoding.bangkit.githubusers


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.bangkit.githubusers.databinding.ActivityDescGitUserBinding


class DescGitUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescGitUserBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescGitUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val result = intent.getParcelableExtra<GithubUser>(EXTRA_DATA) as GithubUser
        binding.txtDeskripsi.text = result.deskripsi
        binding.imgItemDesc.setImageResource(result.foto)

    }
    }



