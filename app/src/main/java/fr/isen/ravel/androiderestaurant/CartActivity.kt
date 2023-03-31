package fr.isen.ravel.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class CartActivity : AppCompatActivity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val cartItems = getCartItems()

        // Configurer le RecyclerView
        recyclerView = findViewById(R.id.cart_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cartAdapter = CartAdapter(cartItems)
        recyclerView.adapter = cartAdapter

    }

    private fun getCartItems(): ArrayList<CartItem> {
        val file = File(filesDir, "cart.json")
        if (!file.exists()) {
            return ArrayList()
        }
        val json = file.readText()
        return json.fromJson<ArrayList<CartItem>>() ?: ArrayList()
    }
}
