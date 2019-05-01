package tw.com.maxting.chocohomework.data

import io.reactivex.Single
import retrofit2.http.GET

interface ChocoService {

    @GET("v2/5a97c59c30000047005c1ed2")
    fun getDramas(): Single<DramasResponse>

}

object ChocoUrl {
    const val BASE_URL = "http://www.mocky.io/"
}