package fr.isen.ravel.androiderestaurant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CategoryAdapter(private var itemsList : List<Items> , private val onMenuItemClickListener: OnMenuItemClickListener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{
    // Les : permettent l'heritage

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewItem: TextView = itemView.findViewById(R.id.textViewItem)
        val imageViewItem: ImageView = itemView.findViewById(R.id.imageView) // Ajoutez un ImageView dans votre fichier de mise en page d'élément de liste
        //val PriceViewItem: TextView = itemView.findViewById(R.id.PriceItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder
    { // creer autant de onCreateViewHolder qu'il y a d'item dans liste courante getItemCount
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int)
    {   // pareil que onCreateViewHolder appeler autant de fois qu'il y a d'item a representer
        // dans la liste courante
        //holder.textViewItem.text = itemsList[position]
        val menuItem = itemsList[position]

        val imageUrl = itemsList[position].images.firstOrNull() // Remplacez cette ligne par la logique d'obtention de l'URL de l'image à partir de vos données
        if (!imageUrl.isNullOrEmpty()) { // le contraire de si elle nul ou vide
            Picasso.get().load(imageUrl).into(holder.imageViewItem) // permet d'afficher les images
        } else {
           // Picasso.
            // Affichez une image par défaut ou masquez l'ImageView si aucune image n'est disponible
        }

        holder.textViewItem.text = menuItem.nameFr
        holder.itemView.setOnClickListener{
             onMenuItemClickListener.onItemClick(menuItem.nameFr ?: "")
        }

        // Charger l'image en utilisant Picasso
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
        fun onItemClick(item: String)
    }

    fun updateItems(newItemsList: List<Items>) {
        itemsList = newItemsList
        notifyDataSetChanged()
    }

    //  Le CategoryAdapter utilise maintenant des objets Items
    //  au lieu de simples chaînes de caractères pour afficher les informations sur les éléments du menu.

    //fun updateDishes(dishesFromAPI:ArrayList<String>) {
    // dishes = dishesFromAPI
    //notifyDataSetChanged()
    //}
}
