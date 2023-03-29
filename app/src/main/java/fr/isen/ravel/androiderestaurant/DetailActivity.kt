

package fr.isen.ravel.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    lateinit var itemName: Items
    // Initialiser les variables
    private var quantity = 1
    var price = 0.0// Remplacez cette valeur par le prix du plat
    private lateinit var priceTextView: Button

    // Mettre à jour le prix en fonction de la quantité
    private fun updatePrice() {
        val totalPrice = price * quantity.toDouble()
        val decimalFormat = DecimalFormat("#.00")
        priceTextView.text = "Prix: ${decimalFormat.format(totalPrice)} €"
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        itemName = intent.getSerializableExtra("itemsList") as Items
        Log.d("DetailActivity" , "itemSelectionne: $itemName")
        //price = itemName.prices[0].price.

        // Récupérer les éléments de la vue
        val imageViewPager = findViewById<ViewPager>(R.id.imageViewPager)
        val imageAdapter = ImagePagerAdapter(supportFragmentManager , itemName.images)
        imageViewPager.adapter = imageAdapter
        val nameView = findViewById<TextView>(R.id.NamePlats)
        val descriptionView  = findViewById<TextView>(R.id.DescriptionPlats)
        val decreaseButton = findViewById<Button>(R.id.decreaseButton)
        val increaseButton = findViewById<Button>(R.id.increaseButton)
        val quantityTextView = findViewById<TextView>(R.id.QuantitySelected)
        priceTextView = findViewById<Button>(R.id.buttonPrice)

        //On met à jour les infos de la selection
        nameView.text = itemName.nameFr
        descriptionView.text = itemName.ingredients.joinToString(",") { it.nameFr.orEmpty() }

        // Gérer les événements de clic sur les boutons
        decreaseButton.setOnClickListener {
            if (quantity > 1) {
                quantity -= 1
                quantityTextView.text = quantity.toString()
                updatePrice()
            }
        }

        increaseButton.setOnClickListener {
            if (quantity < 10) {
                quantity += 1
                quantityTextView.text = quantity.toString()
                updatePrice()
            }
        }

        // Initialiser le prix
        updatePrice()
    }


    class ImagePagerAdapter(fm: FragmentManager, private val imageUrls: List<String>) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int = imageUrls.size

        override fun getItem(position: Int): Fragment {
            return ImageFragment.newInstance(imageUrls[position])
        }
    }

    //L'imageFragment est une classe de fragment personnalisée qui va permettre d'afficher
    //une image à l'intérieur d'un ViewPager
    //Dans notre cas, on veut afficher une image à partir d'une liste d'URL d'images.
    //Le ViewPager utilisera plusieurs instances de ce fragment pour afficher chaque image de la liste d'URL
    //permettant à l'utilisateur de faire défiler les images horizontalement.
    class ImageFragment : Fragment()
    {

        override fun onCreateView(
            inflater: LayoutInflater ,
            container: ViewGroup? ,
            savedInstanceState: Bundle?
        ): View?
        {
            return inflater.inflate(R.layout.fragment_image , container , false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val imageUrl = arguments?.getString(ARG_IMAGE_URL)
            val imageView = view.findViewById<ImageView>(R.id.fragmentImage)

            if (imageUrl != null && imageUrl.isNotEmpty()) {
                Picasso.get()
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .error(R.drawable.placeholder_image)
                    .into(imageView)
            } else {
                imageView.setImageResource(R.drawable.placeholder_image)
            }
        }
        companion object
        {
            private const val ARG_IMAGE_URL = "image_url"
            fun newInstance(imageUrl: String): ImageFragment
            {
                val fragment = ImageFragment()
                val args = Bundle()
                args.putString(ARG_IMAGE_URL , imageUrl)
                fragment.arguments = args
                return fragment
            }
        }
    }
}


