package tw.com.maxting.chocohomework.util

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import tw.com.maxting.chocohomework.data.ChocoService
import tw.com.maxting.chocohomework.data.ChocoUrl
import tw.com.maxting.chocohomework.db.AppDatabase

object NetworkUtils {

    fun provideChocoServices(): ChocoService {
        val retrofit = Retrofit.Builder()
            .baseUrl(ChocoUrl.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()

        return retrofit.create(ChocoService::class.java)
    }

}

object DatabaseUtils {

    fun provideDb(app: Application) =
        Room.databaseBuilder(app, AppDatabase::class.java, "appDatabase.db")
            .fallbackToDestructiveMigration()
            .build()


    fun provideDramaDao(db: AppDatabase) = db.dramaDao()
}