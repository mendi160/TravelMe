package com.project.travelme.Data

import android.content.Context
import com.project.travelme.Entities.Travel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class TravelRepository {
    var database: TravelRoomDataBase? = null
    private var mContext: Context? = null
    private val NUMBER_OF_THREADS = 4
    var dao: TravelDAO = TravelDataSource()
    var itemDAO: TravelDAO? = null
    val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

    constructor(r: Context) {
        mContext = r
    }
    /*
      WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
     */

    /*
      WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
     */
    fun Repository() {}
    fun Repository(app: Context) {

        //  mContext = getApplicationContext();
        database = TravelRoomDataBase.getDatabase(app)
        itemDAO = database?.itemDao()
    }

    fun add(item: Travel) {
        databaseWriteExecutor.execute(Runnable {
            // Populate the database in the background.
            // If you want to start with more words, just add them.
            itemDAO?.insertTravel(item)
            //   dao.insert(item);
        })
    }

//    fun getItems(): LiveData<Item?>? {
//        return itemDAO.getItems()
//    }


    fun addc(travel: Travel) {
        itemDAO?.insertTravel(travel)
    }

    fun remove() {}
    fun update() {}
    fun get() {}

}