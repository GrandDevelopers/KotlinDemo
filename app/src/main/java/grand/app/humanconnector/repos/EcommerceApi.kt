package grand.app.humanconnector.repos

import grand.app.humanconnector.model.Product
import retrofit2.http.GET

interface EcommerceApi {
    @GET("data/products.json")
    suspend fun fetchAllProducts(): List<Product>
}