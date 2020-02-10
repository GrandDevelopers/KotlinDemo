package grand.app.humanconnector.productDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import grand.app.humanconnector.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

//https://bitbucket.org/osama-mohsen/publicurls/raw/d3dfb6e4c34d73a03b7c0513d0f8da3a8eb97479/products
//https://finepointmobile.com/data/products.json
class ProductDetailsViewModel : ViewModel() {
    val productDetailsByName = MutableLiveData<Product>()
    val productDetailsData = MutableLiveData<List<Product>>()
    fun fetchProductDetailsByName(productTitle: String) {
        viewModelScope.launch(Dispatchers.Default) {
            //in background
            val json =
                URL("https://finepointmobile.com/data/products.json").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            val product = products.first { it.title == productTitle }
            productDetailsByName.postValue(product)
        }
    }

    fun fetchProductDetails() {
        viewModelScope.launch(Dispatchers.Default) {
            //in background
            val json =
                URL("https://finepointmobile.com/data/products.json").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            productDetailsData.postValue(products)
        }
    }
}