package fr.isen.ravel.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

//la classe Pomme() va etre la declaration des elements desriptifs d'une pomme
// l'instance val pomme = Pomme --> en Kotlin
// l'instance Pomme pomme = New Pomme() --> en Java
// une classe abstraite ne peux pas etre instancier/declarer
// Le fichier XML list_item permet de définir la mise en page de chaque élément de la liste.
// Dans ce fichier, nous avons créé un TextView avec l'identifiant textViewItem, qu'on peut utiliser dans notre CategoryAdapter.

class HomeActivity : AppCompatActivity()
{
    //private lateinit var binding: ActivityHomeBinding // declaration
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)// On définit la vue de la page sur la vue de ctivity_home

        // Trouvez les éléments de menu dans votre mise en page
        val entreesButton = findViewById<TextView>(R.id.Entree)// <TextView> car moi ici j'ai mis du texte et non un bouton sinon on aurai eu <Button>
        entreesButton.setOnClickListener {
            val intent = Intent(this , CategoryActivity::class.java)
            intent.putExtra("CATEGORY_NAME" , "Entrées")
            startActivity(intent)
            Toast.makeText(this, "Liste des Entrées", Toast.LENGTH_SHORT).show()
        }
        val platsButton = findViewById<TextView>(R.id.Plat)
        platsButton.setOnClickListener {
            val intent = Intent(this , CategoryActivity::class.java)
            intent.putExtra("CATEGORY_NAME" , "Plats")
            startActivity(intent)
            Toast.makeText(this, "Liste des Plats", Toast.LENGTH_SHORT).show()
        }
        val dessertsButton = findViewById<TextView>(R.id.Desserts)
        dessertsButton.setOnClickListener {
            val intent = Intent(this , CategoryActivity::class.java)
            intent.putExtra("CATEGORY_NAME" , "Desserts")
            startActivity(intent)
            Toast.makeText(this, "Liste des Desserts", Toast.LENGTH_SHORT).show()
        }
    }

    //Permet d'ajouter un log dans le terminal pour indiquer que l'activité Home est détruite
    override fun onDestroy() {
        super.onDestroy()
        CartManager.clearCart()
        Log.d("MyActivity", "Home Activity destroyed")
    }
}

