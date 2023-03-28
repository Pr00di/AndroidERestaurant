

package fr.isen.ravel.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import java.lang.StringBuilder

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val itemName = intent.getSerializableExtra("itemsList") as Items
        Log.d("DetailActivity", "itemSelectionne: $itemName")

        // Les éléments à afficher lors de la sélection
        val imageViewPager = findViewById<ViewPager>(R.id.imageViewPager)
        val imageAdapter = ImagePagerAdapter(supportFragmentManager, itemName.images)
        imageViewPager.adapter = imageAdapter

        val nameView = findViewById<TextView>(R.id.NamePlats)
        val descriptionView = findViewById<TextView>(R.id.DescriptionPlats)
        //val priceView = findViewById<TextView>(R.id.textViewPrice)

        /*if(itemName.images.isNotEmpty() && itemName.images[0].isNotEmpty()){
            Picasso.get()
                .load(itemName.images[0])
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(imageViewPager)
        } else{
            imageViewPager.setPageMarginDrawable(R.drawable.placeholder_image)
        }*/

        //On met à jour les infos de la selection
        nameView.text = itemName.nameFr
        descriptionView.text = itemName.ingredients.joinToString(",") { it.nameFr.orEmpty()}

        /*
        val IngredientsList = itemName.ingredients
        val sb = StringBuilder()
        for (i in IngredientsList.indices)
        {
            sb.append(IngredientsList[i].nameFr)
            if (i != IngredientsList.size - 1)
            {
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
        */
    }


    class ImagePagerAdapter(fm: FragmentManager, private val imageUrls: List<String>) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

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


