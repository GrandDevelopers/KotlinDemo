package grand.app.humanconnector.repos

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import grand.app.humanconnector.model.Product
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

//https://finepointmobile.com/data/products.json
class ProductsRepository {

    private fun retrofit() : EcommerceApi{
        return Retrofit.Builder()
            .baseUrl("https://finepointmobile.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(EcommerceApi::class.java)
    }

    suspend fun getAllProductsRetrofit(): List<Product> {
        return retrofit().fetchAllProducts()
    }

    fun getAllProducts() : Single<List<Product>>{
        return Single.create<List<Product>> {
            val json = URL("https://bitbucket.org/osama-mohsen/publicurls/raw/d3dfb6e4c34d73a03b7c0513d0f8da3a8eb97479/products").readText()
            val products = Gson().fromJson(json,Array<Product>::class.java).toList()
            it.onSuccess(products)
        }
    }
}