package fr.isen.ravel.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class CartAdapter(private val cartItems: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        val itemQuantity: TextView = itemView.findViewById(R.id.item_quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.itemName.text = currentItem.item.nameFr
        val itemPrice = currentItem.item.prices[0].price?.toDouble() ?: 0.0
        val decimalFormat = DecimalFormat("#.00")
        holder.itemPrice.text = decimalFormat.format(itemPrice)
        holder.itemQuantity.text = currentItem.quantity.toString()
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}
