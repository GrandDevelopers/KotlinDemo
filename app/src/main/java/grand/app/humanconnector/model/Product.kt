package grand.app.humanconnector.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Product(
    @SerializedName("name")
    @Expose
    val title: String,
    @SerializedName("photo_url")
    @Expose
    val photoUrl: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("isOnSale")
    @Expose
    val isOnSale: Boolean,
    @SerializedName("price")
    @Expose
    val price: Double) {
}