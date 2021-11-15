package com.example.mvvmsampleapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmsampleapp.data.db.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {

        @Volatile //immediately visible to other threads
        private var instance: AppDatabase? = null
        private val LOCK = Any() //so we never create two instance of the AppDatabases

        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK) { //if instance is not null
                instance ?: buildDatabase(context).also {
                    instance=it //it is only relevant inside the lambda expression
                } //also == else part of if
            }



    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyMVVMDatabase.db"
        ).build()



}