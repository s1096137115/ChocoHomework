package tw.com.maxting.chocohomework.data

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Drama(
    val dramaId: Int,
    val totalViews: Int,
    val createdAt: Date,
    val thumb: String,
    val rating: Float
)

//"drama_id": 1,
//"name": "致我們單純的小美好",
//"total_views": 23562274,
//"created_at": "2017-11-23T02:04:39.000Z",
//"thumb": "https://i.pinimg.com/originals/61/d4/be/61d4be8bfc29ab2b6d5cab02f72e8e3b.jpg",
//"rating": 4.4526
