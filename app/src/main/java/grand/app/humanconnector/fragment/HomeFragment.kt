package grand.app.humanconnector.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import grand.app.humanconnector.ProductDetails
import grand.app.humanconnector.adapter.ProductsAdapter
import grand.app.humanconnector.model.Product

import grand.app.humanconnector.R
import grand.app.humanconnector.Utils.Companion.verifyAvailableNetwork
import grand.app.humanconnector.adapter.CategoriesAdapter
import grand.app.humanconnector.database.AgentAsyncTask
import grand.app.humanconnector.productDetails.ProductDetailsViewModel
import grand.app.humanconnector.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val categories = listOf("jeans", "socks", "suits", "skirt", "dresses")

        view.rv_category.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }
        return view;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (verifyAvailableNetwork(activity as AppCompatActivity)) {
            viewModel.setup()
            viewModel.products.observe(requireActivity(), Observer {
                activity?.let { it1 -> AgentAsyncTask(it1, it).execute() }
                setAdapter(it)
            })
        } else
            activity?.let { it1 -> AgentAsyncTask(it1, emptyList(), this).execute() }
    }


    fun setAdapter(products: List<Product>) {
        view?.rv_main?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = ProductsAdapter(products) { title, image , photoView ->
                val intent: Intent = Intent(activity, ProductDetails::class.java)
                intent.putExtra("title", title)
                intent.putExtra("photo", image)
                val options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(activity as AppCompatActivity,photoView,"photoToAnimate")
                startActivity(intent,options.toBundle())
            }
            view?.progressBar?.visibility = View.GONE
        }
    }
}
