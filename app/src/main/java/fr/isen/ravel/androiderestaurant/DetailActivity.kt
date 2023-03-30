package fr.isen.ravel.androiderestaurant

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    // Initialiser les variables
    lateinit var itemName: Items
    private lateinit var priceButtonView: Button
    private var quantity = 1
    var price = 0.0

    // Mettre à jour le prix en fonction de la quantité
    @SuppressLint("SetTextI18n")
    private fun updatePrice() {
        val totalPrice = price * quantity.toDouble()
        val decimalFormat = DecimalFormat("#.00")
        priceButtonView.text = "Prix: ${decimalFormat.format(totalPrice)} €"
    }

    private fun addToCart(cartItem: CartItem) {
        val cartItems = getCartItems()
        cartItems.add(cartItem)
        saveCartItems(cartItems)
    }

    private fun getCartItems(): ArrayList<CartItem> {
        val file = File(filesDir, "cart.json")
        if (!file.exists()) {
            return ArrayList()
        }
        val json = file.readText()
        return json.fromJson<ArrayList<CartItem>>() ?: ArrayList()
    }

    private fun saveCartItems(cartItems: ArrayList<CartItem>) {
        val json = cartItems.toJson()
        val file = File(filesDir, "cart.json")
        FileOutputStream(file).use {
            it.write(json.toByteArray())
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Article ajouté")
            .setMessage("L'article a été ajouté au panier.")
            .setPositiveButton("OK", DialogInterface.OnClickListener { _ , _ -> })
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        itemName = intent.getSerializableExtra("itemsList") as Items

        // Récupérer les éléments de la vue
        val imageViewPager = findViewById<ViewPager>(R.id.imageViewPager)
        val imageAdapter = ImagePagerAdapter(supportFragmentManager , itemName.images)
        imageViewPager.adapter = imageAdapter
        val nameView = findViewById<TextView>(R.id.NamePlats)
        val descriptionView  = findViewById<TextView>(R.id.DescriptionPlats)
        val decreaseButton = findViewById<Button>(R.id.decreaseButton)
        val increaseButton = findViewById<Button>(R.id.increaseButton)
        val quantityTextView = findViewById<TextView>(R.id.QuantitySelected)
        priceButtonView = findViewById(R.id.buttonPrice)

        //On met à jour les infos de la selection
        nameView.text = itemName.nameFr
        descriptionView.text = itemName.ingredients.joinToString(",") { it.nameFr.orEmpty() }

        if (itemName.prices[0].price != null)
        {
            price = itemName.prices[0].price!!.toDouble()
        }

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

        priceButtonView.setOnClickListener {
            addToCart(CartItem(itemName, quantity))
            showAlertDialog()
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


