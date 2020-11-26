package com.project.travelme.Data

import android.content.Context
import com.project.travelme.Entities.Travel


class TravelRepository {
    companion object {
        private var mContext: Context? = null
        var travelDatabase: TravelDataSource? = null
        var database: TravelDAO? = null
        var dao: TravelDAO = TravelDataSource()
        var itemDAO: TravelDAO? = null
//        private const val NUMBER_OF_THREADS = 4
//        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        private fun initializeDB(context: Context): TravelDAO {
            if (true) {
                return TravelDataSource()
            }
            return TravelRoomDataBase.getDatabase(context) as TravelDAO
        }

        fun insert(context: Context, item: Travel) {
            database = initializeDB(context)
            Thread() {
                dao.insertTravel(item)
            }.start()
        }
    }
}