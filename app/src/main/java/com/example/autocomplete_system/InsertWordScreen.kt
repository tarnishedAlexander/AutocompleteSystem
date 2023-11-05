package com.example.autocomplete_system

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.autocomplete_system.Adapters.LastWordPhraseSearchedAdapter
import com.example.autocomplete_system.databinding.ActivityInsertWordScreenBinding

class InsertWordScreen : AppCompatActivity() {

    private lateinit var binding: ActivityInsertWordScreenBinding
    private val lastWordPhraseSearchedAdapter by lazy { LastWordPhraseSearchedAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertWordScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttomSearch.setOnClickListener{
            Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show())
        }

        showLastWordsOrPhrases()
    }

    private fun showLastWordsOrPhrases() {
        TODO("Not yet implemented")
    }
}