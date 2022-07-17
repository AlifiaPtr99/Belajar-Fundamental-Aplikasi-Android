package com.dicoding.bangkit.githubuser_submisi2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.bangkit.githubuser_submisi2.API.RetrofitDec
import com.dicoding.bangkit.githubuser_submisi2.dataClass.GithubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFollowers : ViewModel() {
    val listUserGithubFollowers = MutableLiveData<ArrayList<GithubUser>>()

    fun setUserFollowers(dataFollowers : String){
        RetrofitDec.api
            .getCariDetailUserFollowers(dataFollowers )
            .enqueue(object : Callback<ArrayList<GithubUser>> {
                override fun onResponse(
                    call: Call <ArrayList<GithubUser>>,
                    response: Response <ArrayList<GithubUser>>
                ) {
                    if (response.isSuccessful) {
                        listUserGithubFollowers.postValue(response.body())

                    }
                }

                override fun onFailure(call: Call<ArrayList<GithubUser>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }
    fun getCariFollowersUser() : LiveData<ArrayList<GithubUser>> {
        return listUserGithubFollowers
    }

}