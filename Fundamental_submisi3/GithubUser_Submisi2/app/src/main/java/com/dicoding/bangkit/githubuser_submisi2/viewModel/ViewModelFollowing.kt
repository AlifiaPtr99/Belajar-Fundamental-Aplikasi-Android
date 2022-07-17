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

class ViewModelFollowing : ViewModel() {
    val listUserGithubFollowing = MutableLiveData<ArrayList<GithubUser>>()

    fun setUserFollowing(dataFollowing : String){
        RetrofitDec.api
            .getCariDetailUserFollowing(dataFollowing )
            .enqueue(object : Callback<ArrayList<GithubUser>> {
                override fun onResponse(
                    call: Call <ArrayList<GithubUser>>,
                    response: Response <ArrayList<GithubUser>>
                ) {
                    if (response.isSuccessful) {
                        listUserGithubFollowing.postValue(response.body())

                    }
                }

                override fun onFailure(call: Call<ArrayList<GithubUser>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }
    fun getCariFollowingUser() : LiveData<ArrayList<GithubUser>> {
        return listUserGithubFollowing
    }

}