package com.dicoding.bangkit.githubusers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    var nama : String?,
    var username : String?,
    var foto : Int = 0,
    var deskripsi : String
): Parcelable

