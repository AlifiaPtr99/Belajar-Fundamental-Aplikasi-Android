package com.dicoding.bangkit.githubuser_submisi2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.bangkit.githubuser_submisi2.API.RetrofitDec
import com.dicoding.bangkit.githubuser_submisi2.dataClass.DetailGithubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelDetailGithubUser : ViewModel(){

    val listDetailUserGithub = MutableLiveData<DetailGithubUser>()

    fun setDetailGithubUser(username : String){
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
    fun getCariDetailUser() : LiveData<DetailGithubUser> {
        return listDetailUserGithub
    }
}






