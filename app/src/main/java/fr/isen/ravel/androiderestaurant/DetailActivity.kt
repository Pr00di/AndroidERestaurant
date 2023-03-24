package fr.isen.ravel.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val itemName = intent.getSerializableExtra("itemsList") as Items

        val nameView = findViewById<TextView>(R.id.NamePlats)
        val descriptionView = findViewById<TextView>(R.id.DescriptionPlats)
        //val priceView = findViewById<TextView>(R.id.textViewPrice)
        val pagerView = findViewById<ViewPager>(R.id.ImagePlats)
        val adapter = ImagePagerAdapter(supportFragmentManager, itemName.images)

        pagerView.adapter = adapter
        nameView.text = itemName.nameFr

        val IngredientsList = itemName.ingredients
        val sb = java.lang.StringBuilder()
        for (i in IngredientsList.indices) {
            sb.append(IngredientsList[i].nameFr)
            if (i != IngredientsList.size - 1) {
                sb.append(", ")
            }
        }
        descriptionView.text = sb.toString()
        //priceView.text = itemName.prices[0].price
        //Picasso.get().load(itemName.images).into(imageView)
        //descriptionView.text = itemNameJson.ingredients
        //prixPlat.text = String.format(Locale.getDefault(), "%.2f", plat.prix)
        //val item = gson.fromJson(itemJson , Items::class.java)

        val itemImageJson = intent.getSerializableExtra("itemsList") as Items

    }
        // permet d'afficher le layout activity_detail avec le detail du plats
        // selectionner, le prix, l'image etc...

        /*val item = intent.getParcelableExtra<Detail_item>("item")
        if (item != null) {
            val textViewName: TextView = findViewById(R.id.NamePlats)
            //val textViewDescription: TextView = findViewById(R.id.textViewDescription)
            //val textViewPrice: TextView = findViewById(R.id.textViewPrice)

            textViewName.text = item.nom
            //textViewDescription.text = item.description
            //textViewPrice.text = item.prix.toString()
        }*/
}

class ImagePagerAdapter(fm: FragmentManager , private val imageUrls: List<String>) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int = imageUrls.size

    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(imageUrls[position])
    }
}

class ImageFragment : Fragment()
{

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View?
    {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        val imageUrl = arguments?.getString(ARG_IMAGE_URL)
        if (imageUrl != null && imageUrl.isNotEmpty())
        {
            Picasso.get().load(imageUrl).into(imageView)
        }
        return imageView
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