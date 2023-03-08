package fr.isen.ravel.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.ravel.androiderestaurant.databinding.ActivityHomeBinding

//la classe Pomme() va etre la declaration des elements desriptifs d'une pomme
// l'instance val pomme = Pomme --> en Kotlin
// l'instance Pomme pomme = New Pomme() --> en Java
// une classe abstraite ne peux pas etre instancier/declarer

class HomeActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityHomeBinding // declaration
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //initialisation
        binding = ActivityHomeBinding.inflate(layoutInflater) //inflate est une fonction statique
        // utilisation
        setContentView(binding.root)

        binding.Entree.setOnClickListener {
            val intent = Intent(this , entree::class.java)
            startActivity(intent)
            // gerer le clic sur le bouton entree
        }

        binding.Plat.setOnClickListener {
            val intent = Intent(this , dishes::class.java)
            startActivity(intent)
        }

        binding.Desserts.setOnClickListener {
            val intent = Intent(this , deserts::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyActivity", "Home Activity destroyed")
    }
}
//geeksforgeeks site pour recyclerview
//

