package tw.com.maxting.chocohomework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tw.com.maxting.chocohomework.data.Drama

@Database(entities = [Drama::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dramaDao(): DramaDao

}