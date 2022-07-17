package com.dicoding.bangkit.githubuser_submisi2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserFavDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToListFavorite(userFav: UserFav)

    @Query("SELECT * FROM user_fav")
    fun getFavUser() : LiveData<List<UserFav>>

    @Query("SELECT count(*) FROM user_fav WHERE user_fav.id = :id")
    suspend fun checkUserFav(id: Int): Int

    @Query("DELETE FROM user_fav WHERE user_fav.id = :id")
    suspend fun removeFromFav(id: Int): Int
}