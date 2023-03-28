package fr.isen.ravel.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CategoryAdapter(private var itemsList : List<Items> , private val onMenuItemClickListener: OnMenuItemClickListener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{
    // Les : permettent l'heritage

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewItem: TextView = itemView.findViewById(R.id.textViewItem) // Le nom du plat
        val imageViewItem: ImageView = itemView.findViewById(R.id.imageView) // L'image du plat
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice) // Le prix du plat
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder
    { // creer autant de onCreateViewHolder qu'il y a d'item dans liste courante getItemCount
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int)
    {   // pareil que onCreateViewHolder appeler autant de fois qu'il y a d'item a representer
        // dans la liste courante

        val menuItem = itemsList[position]
        holder.textViewItem.text = menuItem.nameFr

        // Affichez le prix le plus bas disponible pour le plat
        val lowestPrice = menuItem.prices.minByOrNull { it.price?.toDoubleOrNull() ?: Double.MAX_VALUE }
            holder.textViewPrice.text = "${lowestPrice?.price}€"

        // Affichage de l'image
        val imageUrl = itemsList[position].images.firstOrNull() // Remplacez cette ligne par la logique d'obtention de l'URL de l'image à partir de vos données

        if (!imageUrl.isNullOrEmpty())
        {
            Picasso.get()
                .load(imageUrl)
                .fit()
                .centerCrop()
                .error(R.drawable.placeholder_image) // Modification ici: ajout de error()
                .into(holder.imageViewItem)
        }
        else{
            holder.imageViewItem.setImageResource(R.drawable.placeholder_image)
        }

        /*if (!imageUrl.isNullOrEmpty()) // le contraire de si elle nul ou vide
        {
            Picasso.get().load(imageUrl).into(holder.imageViewItem) // permet d'afficher les images
        }
        else // Affichage de l'image d'erreur
        {
           holder.imageViewItem.setImageResource(R.drawable.error_icon)
        }*/

        holder.itemView.setOnClickListener{
             onMenuItemClickListener.onItemClick(menuItem)
        }
    }

    override fun getItemCount(): Int
    // il veut savoir combien d'item vous avez besoin de representer dans la liste
    {
        return itemsList.size
    }

    interface OnMenuItemClickListener{
        fun onItemClick(item: Items)
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
