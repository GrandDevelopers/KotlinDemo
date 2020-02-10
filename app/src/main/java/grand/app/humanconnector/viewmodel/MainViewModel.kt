package grand.app.humanconnector.viewmodel

import androidx.lifecycle.*
import grand.app.humanconnector.model.Product
import grand.app.humanconnector.repos.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val products = MutableLiveData<List<Product>>()

    fun setup(){
        viewModelScope.launch(Dispatchers.Default){
            products.postValue(ProductsRepository().getAllProductsRetrofit())
        }
    }
}