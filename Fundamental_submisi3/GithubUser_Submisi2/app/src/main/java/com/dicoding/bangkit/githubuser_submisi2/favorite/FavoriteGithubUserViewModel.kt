package com.dicoding.bangkit.githubuser_submisi2.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicoding.bangkit.githubuser_submisi2.data.DatabaseUser
import com.dicoding.bangkit.githubuser_submisi2.data.UserFav
import com.dicoding.bangkit.githubuser_submisi2.data.UserFavDao

class FavoriteGithubUserViewModel (application: Application) : AndroidViewModel(application) {

    private var daoFav: UserFavDao?
    private var dbUserFav: DatabaseUser?

    init {
        dbUserFav = DatabaseUser.getDb(application)
        daoFav = dbUserFav?.userFavDao()
    }

    fun getFavUser() : LiveData<List<UserFav>>?{
        return daoFav?.getFavUser()
    }


}