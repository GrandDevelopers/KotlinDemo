package grand.app.humanconnector.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductFromDatabase(
    @PrimaryKey(autoGenerate = true) val uId : Long,
    @ColumnInfo(name = "name") val title: String,
    @ColumnInfo(name = "photo_url") val photoUrl: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "isOnSale")  val isOnSale: Boolean,
    @ColumnInfo(name = "price")  val price: Double
)