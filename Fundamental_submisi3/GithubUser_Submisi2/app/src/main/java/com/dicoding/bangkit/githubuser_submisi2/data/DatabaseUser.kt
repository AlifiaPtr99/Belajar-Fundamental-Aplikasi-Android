package com.dicoding.bangkit.githubuser_submisi2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [UserFav::class],
    version = 2,
)
abstract class DatabaseUser : RoomDatabase() {
    companion object{
        private var INSTANCE : DatabaseUser? = null

        fun getDb(context: Context) : DatabaseUser?{
            if (INSTANCE==null){
                synchronized(DatabaseUser::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseUser::class.java, "database_user").fallbackToDestructiveMigration().build()


                }
            }
            return INSTANCE
        }
    }
            abstract fun userFavDao(): UserFavDao
}