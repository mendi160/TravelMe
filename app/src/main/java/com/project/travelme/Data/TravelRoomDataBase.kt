package com.project.travelme.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.travelme.Entities.Travel

@Database(entities = [Travel::class], version = 1)
abstract class TravelRoomDataBase : RoomDatabase(), TravelDAO {
    abstract fun travelDao(): TravelDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TravelRoomDataBase? = null

        fun getDatabase(context: Context): TravelRoomDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TravelRoomDataBase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}