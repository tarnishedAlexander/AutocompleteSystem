package com.example.autocomplete_system

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autocomplete_system.Adapters.WordOrPhraseAdapter
import com.example.autocomplete_system.DataClases.DataClaseWordsOrPhrases
import com.example.autocomplete_system.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var trie: Trie
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var binding: ActivityMainBinding
    private val WordOrPhraseAdapter by lazy { WordOrPhraseAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttomSearch.setOnClickListener {
            Toast.makeText(
                this,
                "Guardado",
                Toast.LENGTH_SHORT
            ).show()
        }

        showLastWordsAndPhrases()

        trie = Trie()
        trie.insertWord("Hello world")
        trie.insertWord("world")
        trie.insertWord("hola")
        trie.insertWord("paul")
        trie.insertWord("paul")
        trie.insertWord("pato")
        trie.insertWord("partido")
        trie.insertWord("patio")
        trie.insertWord("pollo")


        val wordsList = mutableListOf<String>() // Obteniendo las palabras del trie
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView)
        adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, wordsList)
        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.threshold =
            0 //mostrar el menu desplegable cuando ingresamos el primer prefijo

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

    private fun showLastWordsAndPhrases() {
        val word1 = DataClaseWordsOrPhrases(
            wordOrPhraseDC = "queso",
            imageOfWordOrPhraseDC = R.drawable.word
        )

        val phrase1 = DataClaseWordsOrPhrases(
            wordOrPhraseDC = "amo el queso",
            imageOfWordOrPhraseDC = R.drawable.phrase
        )

        val word2 = DataClaseWordsOrPhrases(
            wordOrPhraseDC = "arroz",
            imageOfWordOrPhraseDC = R.drawable.word
        )

        val phrase2 = DataClaseWordsOrPhrases(
            wordOrPhraseDC = "amo el arroz",
            imageOfWordOrPhraseDC = R.drawable.phrase
        )

        val word3 = DataClaseWordsOrPhrases(
            wordOrPhraseDC = "pitahaya",
            imageOfWordOrPhraseDC = R.drawable.word
        )

        val phrase3 = DataClaseWordsOrPhrases(
            wordOrPhraseDC = "amo el pitahaya",
            imageOfWordOrPhraseDC = R.drawable.phrase
        )

        WordOrPhraseAdapter.addWordsAndPhrases(listOf(word1,word2,word3,phrase1,phrase2,phrase3))

        binding.lastWordsOrPhrasesRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = WordOrPhraseAdapter
        }
    }

}
