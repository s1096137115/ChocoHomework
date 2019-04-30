package tw.com.maxting.chocohomework.data

import android.app.Application
import androidx.room.Room
import tw.com.maxting.chocohomework.db.AppDatabase
import tw.com.maxting.chocohomework.db.DramaDao
import tw.com.maxting.chocohomework.util.DatabaseUtils
import tw.com.maxting.chocohomework.util.NetworkUtils

class Repository private constructor(
    private val service: ChocoService,
    private val dramaDao: DramaDao
) {

    companion object {
        private var INSTANCE: Repository? = null

        fun getInstance(application: Application): Repository =
            INSTANCE ?: Repository(
                NetworkUtils.provideChocoServices(),

                Room.databaseBuilder(application, AppDatabase::class.java, "appDatabase.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .dramaDao()

//                DatabaseUtils.provideDramaDao(
//                    DatabaseUtils.provideDb(application)
//                )
            ).also { INSTANCE = it }
    }

    fun fetchDramasFromNetwork() = service.getDramas()

    fun loadDramasFromDb() = dramaDao.getAll()

    fun saveDramas(dramas : List<Drama>) {
        dramaDao.insertDramas(dramas)
    }

}