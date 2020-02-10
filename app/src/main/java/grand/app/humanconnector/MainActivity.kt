package grand.app.humanconnector

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Gravity
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import grand.app.humanconnector.cart.CartActivity
import grand.app.humanconnector.database.AppDatabase
import grand.app.humanconnector.database.ProductFromDatabase
import grand.app.humanconnector.fragment.AdminFragment
import grand.app.humanconnector.fragment.HomeFragment
import grand.app.humanconnector.fragment.JeansFragment
import grand.app.humanconnector.fragment.ShortsFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout, HomeFragment()).commit()
                }
                R.id.action_jeans -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout, JeansFragment()).commit()
                }
                R.id.action_shorts -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout, ShortsFragment()).commit()
                }
                R.id.action_admin -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.framelayout, AdminFragment()
                    ).commit()
                }
            }
            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white)
        }

        navigation.setCheckedItem(R.id.action_home)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout, HomeFragment()).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_cart) {
            startActivity(Intent(this,CartActivity::class.java))
            return true
        }
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }
}
