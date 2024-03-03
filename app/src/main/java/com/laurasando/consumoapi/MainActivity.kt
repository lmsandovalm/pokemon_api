package com.laurasando.consumoapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.laurasando.consumoapi.model.Pokemon
import com.laurasando.consumoapi.pokeApi.PokemonApiService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val pokemonNames = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listado)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pokemonNames)
        listView.adapter = adapter

        //val text = findViewById<TextView>(R.id.txt1)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PokemonApiService::class.java)

        val call = service.getPokemons()

        call.enqueue(object : Callback<Listado>{
            override fun onResponse(call: Call<Listado>, response: Response<Listado>) {
                if (!response.isSuccessful){
                    Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    return
                }

                val pokemonResponse = response.body()
                if (pokemonResponse != null) {
                    for (pokemon in pokemonResponse.pokemons) {
                        pokemonNames.add(pokemon.name)
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Listado>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error${t.message}", Toast.LENGTH_SHORT).show()
            }

        })


        /*val img = findViewById<ImageView>(R.id.Img1)

        Picasso.get()
            .load("https://i.blogs.es/2528f2/dragon-ball-el-super-saiyajin-3-no-deberia-usarse-en-la-tierra-y-no-es-por-la-razon-que-imaginas/1366_2000.jpeg")
            .into(img)

        val img2 = findViewById<ImageView>(R.id.Img2)

        Glide.with(this)
            .load("https://depor.com/resizer/vTDdHePFh0xiKUS2e07kwa_HSp4=/580x330/smart/filters:format(jpeg):quality(90)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/NRFDECK2RBDCTKHGOOT5XJQEDI.jpg")
            .into(img2)*/

    }
}

private fun <T> Call<T>.enqueue(callback: Callback<Listado>) {

}

