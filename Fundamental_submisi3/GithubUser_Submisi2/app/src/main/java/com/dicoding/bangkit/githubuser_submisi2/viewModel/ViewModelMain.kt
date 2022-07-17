package com.dicoding.bangkit.githubuser_submisi2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.bangkit.githubuser_submisi2.API.RetrofitDec
import com.dicoding.bangkit.githubuser_submisi2.dataClass.GithubUser
import com.dicoding.bangkit.githubuser_submisi2.dataClass.GithubUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class ViewModelMain : ViewModel() {
    val listUserGithub = MutableLiveData<ArrayList<GithubUser>>()

    fun setGithubUser(person : String){
        RetrofitDec.api
            .getCariUser(person)
            .enqueue(object : Callback<GithubUserResponse>{
                override fun onResponse(
                    call: Call<GithubUserResponse>,
                    response: Response<GithubUserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUserGithub.postValue(response.body()?.items)

                    }
                }

                override fun onFailure(call: Call<GithubUserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }
    fun getCariGithubUser() : LiveData<ArrayList<GithubUser>>{
        return listUserGithub
    }


}