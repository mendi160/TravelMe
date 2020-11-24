package com.project.travelme.Data

import android.content.Context
import androidx.lifecycle.LiveData
import com.project.travelme.Entities.Travel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class TravelRepository {
    companion object {
        private var mContext: Context? = null
        var travels: LiveData<Travel>? = null
        var travelDatabase: TravelDataSource? = null
        var database: TravelRoomDataBase? = null
        var dao: TravelDAO = TravelDataSource()
        var itemDAO: TravelDAO? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun initializeDB(context: Context): TravelRoomDataBase {
            return TravelRoomDataBase.getDatabase(context)
        }

        fun add(context: Context, item: Travel) {
            database = initializeDB(context)

            Thread(){
                database!!.travelDao().insertTravel(item)
            }.start()
        }
    }

//    fun getItems(context: Context, username: String): LiveData<Travel>? {
//
//        database = initializeDB(context)
//
//        Travel = database!!.travelDao().getLoginDetails(username)
//
//        return loginTableModel
//    }

    fun remove() {}
    fun update() {}
    fun get() {}
}