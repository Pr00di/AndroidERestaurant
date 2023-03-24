package fr.isen.ravel.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class CategoryActivity : AppCompatActivity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val category = intent.getStringExtra("CATEGORY_NAME")
        supportActionBar?.title = category

        recyclerView = findViewById(R.id.categoryActivityRv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //
        categoryAdapter = when (category)
        {
            "Entrées" -> CategoryAdapter(emptyList() ,
                object : CategoryAdapter.OnMenuItemClickListener
                {
                    override fun onItemClick(itemsList: String)
                    {
                        val intent = Intent(this@CategoryActivity , DetailActivity::class.java)
                        intent.putExtra("itemsList" , itemsList)
                        startActivity(intent)
                    }
                })

            "Plats" -> CategoryAdapter(emptyList() ,
                object : CategoryAdapter.OnMenuItemClickListener
                {
                    override fun onItemClick(itemsList: String)
                    {
                        val intent = Intent(this@CategoryActivity , DetailActivity::class.java)
                        intent.putExtra("itemsList" , itemsList)
                        startActivity(intent)
                    }
                })

            "Desserts" -> CategoryAdapter(emptyList() ,
                object : CategoryAdapter.OnMenuItemClickListener
                {
                    override fun onItemClick(itemsList: String)
                    {
                        val intent = Intent(this@CategoryActivity , DetailActivity::class.java)
                        intent.putExtra("itemsList" , itemsList)
                        startActivity(intent)
                    }
                })

            else -> CategoryAdapter(emptyList() , object : CategoryAdapter.OnMenuItemClickListener
            {
                override fun onItemClick(itemsList: String)
                {
                }
            })
        }

        recyclerView.adapter = categoryAdapter

        fetchMenu()
    }

    private fun fetchMenu()
    {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val requestBody = JSONObject().apply {
            put("id_shop" , 1)
        }
        val request = JsonObjectRequest(
            Request.Method.POST , url , requestBody ,
            { response ->
                Log.d("ca marche" , response.toString()) // Message dans le terminal de commande

                // Je parse le JSON avec la librairie GSON afin d'obtenir des Listes de données claires
                val menuResult = Gson().fromJson(response.toString() , ListMenu::class.java)
                // Je viens recuperer la listMenu qui contient l'ensemble des données

                // Je viens définir une variable qui va venir stocker la categorie selectionné
                val selectedCategoryName = intent.getStringExtra("CATEGORY_NAME")

                // Je viens filtrer l'ensemble des données en piochant dans la partie data
                // de la variable menuResult qui contient elle-meme la ListMenu (qui contient elle les data)
                val filteredItems: List<Items> = menuResult.data
                    .flatMap { it.items }
                    .filter { item -> item.categNameFr == selectedCategoryName }

                categoryAdapter.updateItems(filteredItems) // Je viens rafraichir l'adapteur pour qu'il m'affiche
                // que les données comportant le bon cateNameFr

            } ,
            { error ->
                Log.d("404 error" , error.toString())
            })
        Volley.newRequestQueue(this).add(request)
    }
}

    //val selectedCategory = menuResult.data.find { it.nameFr == selectedCategoryName }
    // Filter menu items based on the selected category
    //val selectedCategoryItems = menuResult.data.filter { it.nameFr == selectedCategoryName }

    // Combine items from all categories with the same ID
    //val combinedItems = selectedCategoryItems.flatMap { it.items }

    // Update the adapter with the filtered menu items

    /*val selectedCategory = menuResult.data.find { it.idCategory == selectedCategoryId }
    // Update the adapter with the filtered menu items
       selectedCategory?.let { val filteredItems: List<String> = it.items.mapNotNull { menuItem -> menuItem.nameFr }
       categoryAdapter.updateItems(filteredItems) }*/


