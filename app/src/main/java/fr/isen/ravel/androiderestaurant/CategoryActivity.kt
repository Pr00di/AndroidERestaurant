package fr.isen.ravel.androiderestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)// permet de selectionner la vue de la page de la categorie selectionner

        val category =
            intent.getStringExtra("CATEGORY_NAME") // Récupère le nom de la catégorie passé en argument
        supportActionBar?.title =
            category // Définit le titre de la page comme le nom de la catégorie

        val recyclerView : RecyclerView = findViewById(R.id.categoryActivityRv)
        //recyclerView.LayoutManager = LinearLayoutManager()

    }


    object Constants {
        val listeEntrees = listOf(
            R.string.entree1,
            R.string.entree2,
            R.string.entree3,
            R.string.entree4,
            R.string.entree5
        )

        val listePlats = listOf(
            R.string.plats1,
            R.string.plats2,
            R.string.plats3,
            R.string.plats4,
            R.string.plats5
        )

        val listeDesserts = listOf(
            R.string.dessert1,
            R.string.dessert2,
            R.string.dessert3,
            R.string.dessert3,
            R.string.dessert5
        )

    }

}
