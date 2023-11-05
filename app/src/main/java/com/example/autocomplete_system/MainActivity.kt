package com.example.autocomplete_system

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import com.example.autocomplete_system.DataClases.Word
import com.example.autocomplete_system.DataClases.WordsDB
import com.google.gson.Gson
import java.io.IOException



class MainActivity : AppCompatActivity() {

    private lateinit var trie: Trie
    private lateinit var gson:Gson
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var myImageButton: ImageButton
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trie = Trie()
        gson = Gson()
        val json = loadData("words.json") // Leer el archivo words.json
        var dataBase = gson.fromJson(json, WordsDB::class.java) // convertir de json a una clase para poder leer los datos

        for (i in dataBase.words!!.indices){ //For para insertar las palabra de la base de datos en el trie
            dataBase.words?.get(i)?.let {
                //logs de prueba
                Log.d("RES", it.word)
                Log.d("RES", it.id.toString())
                trie.insertWord(it.word) //insertar en el trie
            }
        }


        autoCompleteTextView = findViewById(R.id.autoCompleteTextView)
        myImageButton = findViewById(R.id.buttom_search)
        myImageButton.setOnClickListener {// Boton para guardar las palabras en el trie
            val word: String = autoCompleteTextView.text.toString()
            trie.insertWord(word)

            val addWord = WordsDB()
            val newWord = Word()
            newWord.id = dataBase.words!!.size + 1
            newWord.word = word
            addWord.words?.add(newWord)


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


    private fun loadData(inFile:String):String{ //Abrir y leer el archivo .json de la "base de datos"
        var content = ""

        try {
            val stream = assets.open(inFile)

            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            content = String(buffer)
        } catch (e: IOException){

        }

        return content
    }


}
