package tw.com.maxting.chocohomework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import tw.com.maxting.chocohomework.data.Drama

@Dao
interface DramaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDrama(vararg drama: Drama)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDramas(dramas: List<Drama>)

    @Query("SELECT * FROM Drama")
    fun getAll(): Flowable<List<Drama>>

    @Query("SELECT * FROM Drama WHERE drama_id = :id")
    fun getDramaById(id: Int): Flowable<Drama>

}