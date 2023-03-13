package fr.isen.ravel.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import fr.isen.ravel.androiderestaurant.databinding.ActivityHomeBinding

//la classe Pomme() va etre la declaration des elements desriptifs d'une pomme
// l'instance val pomme = Pomme --> en Kotlin
// l'instance Pomme pomme = New Pomme() --> en Java
// une classe abstraite ne peux pas etre instancier/declarer

class HomeActivity : AppCompatActivity()
{
    //private lateinit var binding: ActivityHomeBinding // declaration
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Trouvez les éléments de menu dans votre mise en page
        val entreesButton = findViewById<TextView>(R.id.Entree)
        entreesButton.setOnClickListener {
            val intent = Intent(this , CategoryActivity::class.java)
            intent.putExtra("CATEGORY_NAME" , "Entrees")
            startActivity(intent)
        }
        val platsButton = findViewById<TextView>(R.id.Plat)
        platsButton.setOnClickListener {
            val intent = Intent(this , CategoryActivity::class.java)
            intent.putExtra("CATEGORY_NAME" , "Plats")
            startActivity(intent)
        }
        val dessertsButton = findViewById<TextView>(R.id.Desserts)
        dessertsButton.setOnClickListener {
            val intent = Intent(this , CategoryActivity::class.java)
            intent.putExtra("CATEGORY_NAME" , "Desserts")
            startActivity(intent)
        }
    }

    // Méthode pour ouvrir une nouvelle activité en fonction de la catégorie sélectionnée
    private fun openCategoryActivity(category: String) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }

    //Permet d'ajouter un log dans le terminal pour indiquer que l'activité Home est détruite
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyActivity", "Home Activity destroyed")
    }
}
//geeksforgeeks site pour recyclerview
//

// Ajoutez un écouteur de clic pour chaque élément de menu
/*entreesButton.setOnClickListener { openCategoryActivity("Entrees") }
platsButton.setOnClickListener { openCategoryActivity("Plats") }
dessertsButton.setOnClickListener { openCategoryActivity("Desserts") }*/

/*initialisation
binding = ActivityHomeBinding.inflate(layoutInflater) //inflate est une fonction statique
 utilisation
setContentView(binding.root)

binding.Entree.setOnClickListener {
    val intent = Intent(this , CategoryActivity::class.java)
    //startActivity(intent)
    // gerer le clic sur le bouton entree
}

binding.Plat.setOnClickListener {
    val intent = Intent(this , CategoryActivity::class.java)
    //startActivity(intent)
}

binding.Desserts.setOnClickListener {
    val intent = Intent(this , CategoryActivity::class.java)
    //startActivity(intent)
}*/

