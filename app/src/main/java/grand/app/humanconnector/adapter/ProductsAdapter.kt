package grand.app.humanconnector.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import grand.app.humanconnector.model.Product
import grand.app.humanconnector.R

class ProductsAdapter(private val products: List<Product>,
                      private val onProductClick: (title: String,photoUrl: String,photoView: View) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.product_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = products[position].title
        holder.price.text = products[position].price.toString()+"$"
        if(products[position].isOnSale)
            holder.imageSale.visibility = View.VISIBLE
        else
            holder.imageSale.visibility = View.GONE
        Picasso.get().load(products[position].photoUrl).into(holder.image)
        holder.image.setOnClickListener {
            onProductClick.invoke(products[position].title,products[position].photoUrl,holder.image)
        }
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val image: ImageView  = view.findViewById(R.id.img_product)
        val imageSale: ImageView  = view.findViewById(R.id.img_product_sale)
        val title : TextView  = view.findViewById(R.id.tv_product)
        val price : TextView  = view.findViewById(R.id.tv_price)
    }
}