package fr.isen.ravel.androiderestaurant

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley


class CategoryActivity : AppCompatActivity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val category = intent.getStringExtra("CATEGORY_NAME")
        supportActionBar?.title = category

        recyclerView = findViewById(R.id.categoryActivityRv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        categoryAdapter = when (category) {
            "Entrees" -> CategoryAdapter(resources.getStringArray(R.array.Entrees).toList(),object : CategoryAdapter.OnMenuItemClickListener
            {
                override fun onItemClick(itemsList:String){
                    val intent = Intent(this@CategoryActivity, DetailActivity::class.java)
                    intent.putExtra("itemsList", itemsList)
                    startActivity(intent)}
            })

            "Plats" -> CategoryAdapter(resources.getStringArray(R.array.Plats).toList(),object : CategoryAdapter.OnMenuItemClickListener
            {
                override fun onItemClick(itemsList:String){
                    val intent = Intent(this@CategoryActivity, DetailActivity::class.java)
                    intent.putExtra("itemsList", itemsList)
                    startActivity(intent)}
            })

            "Desserts" -> CategoryAdapter(resources.getStringArray(R.array.Desserts).toList(),object : CategoryAdapter.OnMenuItemClickListener
            {
                override fun onItemClick(itemsList:String){
                    val intent = Intent(this@CategoryActivity, DetailActivity::class.java)
                    intent.putExtra("itemsList", itemsList)
                    startActivity(intent)}
            })

            else -> CategoryAdapter(emptyList(), object : CategoryAdapter.OnMenuItemClickListener {
                override fun onItemClick(itemsList: String) {}
            })
        }

        recyclerView.adapter = categoryAdapter

        fetchMenu()
    }

    private fun fetchMenu() {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val requestBody = JSONObject().apply {
            put("id_shop" , 1)
        }
        val request = JsonObjectRequest(Request.Method.POST , url , requestBody ,
            { response ->
                Log.d("ca marche", response.toString())
            } ,
            { error ->
                Log.d("404 error", error.toString())
            })
        Volley.newRequestQueue(this).add(request)
    }

}