package com.dicoding.bangkit.githubuser_submisi2.data
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "user_fav")
data class UserFav(
    val login: String,
    @PrimaryKey(autoGenerate = true)

    val id: Int,
    val avatar_url : String

) : Serializable
