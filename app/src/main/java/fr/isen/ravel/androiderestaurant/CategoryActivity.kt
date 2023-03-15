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
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    // Cela permet d'initialisées ultérieurement ces variables dans le code
    // généralement dans la méthode onCreate() de l'activité
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)// permet de selectionner la vue de la page de la categorie selectionner

        val category =
            intent.getStringExtra("CATEGORY_NAME") // Récupère le nom de la catégorie passé en argument
            supportActionBar?.title = category // Définit le titre de la page comme le nom de la catégorie

        recyclerView = findViewById(R.id.categoryActivityRv)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val menuItems = when (category) { // when permet de selectionner la bonne liste en fonction de la categorie
            "Entrees" -> resources.getStringArray(R.array.Entrees).toList()
            "Plats" -> resources.getStringArray(R.array.Plats).toList()
            "Desserts" -> resources.getStringArray(R.array.Desserts).toList()
            else -> emptyList<String>()
        }

        categoryAdapter = CategoryAdapter(menuItems)
        recyclerView.adapter = categoryAdapter
        // Après avoir récupéré la liste des éléments de la categorie selectionner,
        // nous initialisons CategoryAdapter avec la liste récupérée et
        // définissons l'adaptateur de recyclerView comme étant CategoryAdapter.
    }


}
