package com.dicoding.bangkit.githubusers

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*


    class ListGithubUserAdapter(private val listGithubUser : ArrayList<GithubUser>) :
        RecyclerView.Adapter<ListGithubUserAdapter.ListGithubUserViewHolder>() {


        private lateinit var onItemClickCallback: OnItemClickCallback



        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
                this.onItemClickCallback = onItemClickCallback

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListGithubUserViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
            return ListGithubUserViewHolder(view)
        }

        override fun onBindViewHolder(holder: ListGithubUserViewHolder, position: Int) {
            val git = listGithubUser[position]
            Glide.with(holder.itemView.context)
                .load(git.foto)
                .apply(RequestOptions().override(55, 55))
                .into(holder.imgPhoto)
            holder.tvUname.text = git.nama
            holder.tvName.text = git.username


            holder.itemView.setOnClickListener {
                val descIntent = Intent(holder.itemView.context, DescGitUserActivity::class.java)
                   descIntent.putExtra(DescGitUserActivity.EXTRA_DATA, git)
                    holder.itemView.context.startActivity(descIntent)
                   }

        }

        override fun getItemCount(): Int = listGithubUser.size


        class ListGithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_home)
            var tvUname: TextView = itemView.findViewById(R.id.tv_username)
            var tvName: TextView = itemView.findViewById(R.id.tv_nama)
        }

        interface OnItemClickCallback {
            fun onItemClicked(data: GithubUser)
        }
    }



