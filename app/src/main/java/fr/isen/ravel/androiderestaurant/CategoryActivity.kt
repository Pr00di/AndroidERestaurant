package fr.isen.ravel.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)// permet de selectionner la vue de la page de la categorie selectionner

        val category = intent.getStringExtra("CATEGORY_NAME") // Récupère le nom de la catégorie passé en argument
        supportActionBar?.title = category // Définit le titre de la page comme le nom de la catégorie
    }
}
