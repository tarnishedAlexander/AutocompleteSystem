package com.example.autocomplete_system

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import com.google.gson.Gson
import java.io.IOException



class MainActivity : AppCompatActivity() {

    private lateinit var trie: Trie
    //private lateinit var gson:Gson
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var myImageButton: ImageButton
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trie = Trie()
        //gson = Gson()
        //val json = loadData("words.json")
        //Log.d("RES", json)

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView)
        myImageButton = findViewById(R.id.buttom_search)
        myImageButton.setOnClickListener {// Boton para guardar las palabras en el trie
            val word: String = autoCompleteTextView.text.toString()
            trie.insertWord(word)
        }



        val wordsList = mutableListOf<String>() // Obteniendo las palabras del trie
        adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, wordsList)
        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.threshold = 0 //mostrar el menu desplegable cuando ingresamos el primer prefijo

        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // not util
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // not util
            }

            override fun afterTextChanged(s: Editable?) {
                val prefix: String = s.toString()

                //sugerencias del trie
                val autoCompleteResults = trie.autoComplete(prefix)

                //mostrar las sugerencias
                adapter.clear()
                adapter.addAll(autoCompleteResults)
                adapter.notifyDataSetChanged()
            }
        })
    }





}
