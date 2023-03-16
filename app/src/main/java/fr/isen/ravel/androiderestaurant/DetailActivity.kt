package fr.isen.ravel.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val item = intent.getParcelableExtra<Detail_item>("item")
        if (item != null) {
            val textViewName: TextView = findViewById(R.id.NamePlats)
            //val textViewDescription: TextView = findViewById(R.id.textViewDescription)
            //val textViewPrice: TextView = findViewById(R.id.textViewPrice)

            textViewName.text = item.nom
            //textViewDescription.text = item.description
            //textViewPrice.text = item.prix.toString()
        }
    }


}