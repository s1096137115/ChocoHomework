package tw.com.maxting.chocohomework.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(primaryKeys = ["drama_id"])
@Serializable
data class Drama(
    @ColumnInfo(name = "drama_id")
    @SerialName("drama_id")
    val dramaId: Int,
    val name: String,
    @ColumnInfo(name = "total_views")
    @SerialName("total_views")
    val totalViews: Long,
    @ColumnInfo(name = "created_at")
    @SerialName("created_at")
    val createdAt: String,
    val thumb: String,
    val rating: Float
)


@Serializable
data class DramasResponse(
    val data: List<Drama>
)

