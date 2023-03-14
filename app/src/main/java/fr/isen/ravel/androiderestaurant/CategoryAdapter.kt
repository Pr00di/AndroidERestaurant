package fr.isen.ravel.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val items:ArrayList<String>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val textView : TextView = itemView.findViewById(R.id.categoryActivityRv)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder
    { // creer un ViewHolder à chaque fois qu'il y a un element de getItemCount
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_category,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int)
    { // mettre les explication de la video youtube https://www.youtube.com/watch?v=e_Morfqqn1I&t=268s
        holder.textView.text = items[position]
    }

    override fun getItemCount(): Int
    {
        return items.size
    }

}
