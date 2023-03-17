package fr.isen.ravel.androiderestaurant

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ActionMenuView.OnMenuItemClickListener
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class CategoryAdapter(private val itemsList : List<String>, private val onMenuItemClickListener: OnMenuItemClickListener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{
    // Les : permettent l'heritage

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewItem: TextView = itemView.findViewById(R.id.textViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder
    { // creer autant de onCreateViewHolder qu'il y a d'item dans liste courante getItemCount
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int)
    { // pareil que onCreateViewHolder appeler autant de fois qu'il y a d'item a representer dans la liste courante
        holder.textViewItem.text = itemsList[position]
        holder.itemView.setOnClickListener{
            onMenuItemClickListener.onItemClick(itemsList[position])
        }
        //val dish = dishes[position]
        //holder.dishName.text = dish
        //holder.itemView.setOnClickListener
    }

    override fun getItemCount(): Int
    // il veut savoir combien d'item vous avez besoin de representer dans la liste
    {
        return itemsList.size
    }

    interface OnMenuItemClickListener{
        fun onItemClick(itemsList: String)
    }

    //fun updateDishes(dishesFromAPI:ArrayList<String>) {
    // dishes = dishesFromAPI
    //notifyDataSetChanged()
    //}
}
