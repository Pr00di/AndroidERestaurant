package fr.isen.ravel.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.TextView
import fr.isen.ravel.androiderestaurant.databinding.ActivityHomeBinding

//la classe Pomme() va etre la declaration des elements desriptifs d'une pomme
// l'instance val pomme = Pomme --> en Kotlin
// l'instance Pomme pomme = New Pomme() --> en Java
// une classe abstraite ne peux pas etre instancier/declarer

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding // declaration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initialisation
        binding = ActivityHomeBinding.inflate(layoutInflater) //inflate est une fonction statique
        // utilisation
        //setContentView(R.layout.activity_home) // PAS DEUX FOIS CETTE LIGNE !!!
        setContentView(binding.root)

        binding.Entree.setOnClickListener {
            val intent = Intent(this, Entree::class.java)
            startActivity(intent)
            // gerer le clic sur le bouton entree
        }
        //findViewById<TextView>(R.id.Entree).setOnClickListener{
        //  Log.d("home_entree", "Vous avez cliquer sur Entree")
        //}

        findViewById<TextView>(R.id.Plat).setOnClickListener {
            Log.d("Plats", "Vous avez cliquer sur Plats")
        }

        findViewById<TextView>(R.id.Desserts).setOnClickListener {
            Log.d("Desserts", "Vous avez cliquer sur Desserts")
        }
    }
}
//geeksforgeeks site pour recyclerview
//

