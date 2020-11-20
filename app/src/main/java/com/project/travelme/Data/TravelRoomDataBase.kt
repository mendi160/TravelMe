package com.project.travelme.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.travelme.Entities.Travel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [Travel::class], version = 1, exportSchema = false)
abstract class TravelRoomDataBase : RoomDatabase() {
    abstract fun itemDao(): TravelDAO

    companion object {
        // marking the instance as volatile to ensure atomic access to the variable
        @Volatile
        private var INSTANCE: TravelRoomDataBase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(
            NUMBER_OF_THREADS)

        fun getDatabase(context: Context): TravelRoomDataBase? {
            if (INSTANCE == null) {
                synchronized(TravelRoomDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder(context.applicationContext,
                                TravelRoomDataBase::class.java, "travel_database")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}