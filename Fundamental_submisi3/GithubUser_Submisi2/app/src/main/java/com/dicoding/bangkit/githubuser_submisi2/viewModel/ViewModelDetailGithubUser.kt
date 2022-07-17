package com.dicoding.bangkit.githubuser_submisi2.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.bangkit.githubuser_submisi2.API.RetrofitDec
import com.dicoding.bangkit.githubuser_submisi2.data.DatabaseUser
import com.dicoding.bangkit.githubuser_submisi2.data.UserFav
import com.dicoding.bangkit.githubuser_submisi2.data.UserFavDao
import com.dicoding.bangkit.githubuser_submisi2.dataClass.DetailGithubUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelDetailGithubUser (application: Application) : AndroidViewModel(application) {

    val listDetailUserGithub = MutableLiveData<DetailGithubUser>()

    private var dao: UserFavDao?
    private var dbUser: DatabaseUser?

    init {
        dbUser = DatabaseUser.getDb(application)
        dao = dbUser?.userFavDao()
    }

        fun setDetailGithubUser(username: String) {
            RetrofitDec.api
                .getCariDetailUser(username)
                .enqueue(object : Callback<DetailGithubUser> {
                    override fun onResponse(
                        call: Call<DetailGithubUser>,
                        response: Response<DetailGithubUser>
                    ) {
                        if (response.isSuccessful) {
                            listDetailUserGithub.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<DetailGithubUser>, t: Throwable) {
                        Log.d("Failure", t.message.toString())
                    }

                })
        }

        fun getCariDetailUser(): LiveData<DetailGithubUser> {
            return listDetailUserGithub
        }


    fun add(login : String, id : Int, avatar_url : String){
        CoroutineScope(Dispatchers.IO).launch {
           val userGithubFav = UserFav(
                                login,
                                id,
                                avatar_url
                                )
            dao?.addToListFavorite(userGithubFav)
        }


    }

    suspend fun check(id: Int?) = dao?.checkUserFav(id!!)

    fun remove(id : Int){
        CoroutineScope(Dispatchers.IO).launch {
            dao?.removeFromFav(id)

        }
    }
}









