package com.project.travelme.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.travelme.Entities.Travel
import com.project.travelme.Utils.AddressConverter


//@Database(entities = [Travel::class], version = 1, exportSchema = false)
//@TypeConverters(AddressConverter::class)
abstract class TravelRoomDataBase : RoomDatabase() {
    abstract fun travelDao(): TravelDAO

    companion object {
        // marking the instance as volatile to ensure atomic access to the variable
        @Volatile
        private var INSTANCE: TravelRoomDataBase? = null
//        private const val NUMBER_OF_THREADS = 4
//        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(
//            NUMBER_OF_THREADS)

        fun getDatabase(context: Context): TravelRoomDataBase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, TravelRoomDataBase::class.java, "Travel")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }
}