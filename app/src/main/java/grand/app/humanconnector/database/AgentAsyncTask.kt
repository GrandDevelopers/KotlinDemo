package grand.app.humanconnector.database

import android.annotation.SuppressLint
import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import grand.app.humanconnector.adapter.ProductsAdapter
import grand.app.humanconnector.fragment.HomeFragment
import grand.app.humanconnector.model.Product
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class AgentAsyncTask(
    activity: Activity,
    //Prevent leak
    private var products: List<Product>
) :
    AsyncTask<Void?, Void?, String?>() {
    lateinit var homeFragment:HomeFragment
    var isWifi: Boolean = true;
    constructor(activity: Activity,products: List<Product>, homeFragment: HomeFragment) : this(activity,products) {
        this.homeFragment = homeFragment
        isWifi = false;
    }

    val db = Room.databaseBuilder(
        activity.applicationContext,
        AppDatabase::class.java, "Human-connector"
    ).build()

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

    @SuppressLint("WrongThread")
    override fun doInBackground(vararg p0: Void?): String? {
        Log.e("result_doInBackground","done")
        if(isWifi) {
            Log.e("result_delete_command","done")
            db.productDao().deleteAll()
            val productsFromDatabase = products.map {
                ProductFromDatabase(
                    0,
                    it.title,
                    it.photoUrl,
                    it.description,
                    it.isOnSale,
                    it.price
                )
            }

            db.productDao().insertAll(*productsFromDatabase.toTypedArray())

        }else{
            val productDataBase : List<ProductFromDatabase> = db.productDao().getAll();
            products = productDataBase.map {
                Product(it.title, it.photoUrl, it.description, it.isOnSale, it.price)
            }
            homeFragment.view?.let { homeFragment.setAdapter(products) }
        }
        val productsCount: Int = db.productDao().getProductsCount()

        Log.e("products_count", productsCount.toString())
        return "Done";
    }


}