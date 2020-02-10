package grand.app.humanconnector

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import grand.app.humanconnector.productDetails.ProductDetailsViewModel
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetails : AppCompatActivity() {

    lateinit var viewModel : ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        viewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel::class.java)
        val title = intent.getStringExtra("title") ?: ""
//        val photo = intent.getStringExtra("photo")

        viewModel.fetchProductDetailsByName(title)
        progressBar.visibility = View.VISIBLE
        viewModel.productDetailsByName.observe(this, Observer {
            progressBar.visibility = View.GONE
            Picasso.get().load(it.photoUrl).into(img_product_details)
            tv_product_details_title.text = it.title
            tv_description.text = it.description
        })

        btn_product_details_available.setOnClickListener{
            AlertDialog.Builder(this)
                .setMessage("Hey $title is in stock")
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                })
                .create()
                .show()
        }
    }
}
