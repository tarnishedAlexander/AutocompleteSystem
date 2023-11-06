package com.example.autocomplete_system.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autocomplete_system.DataClases.DataClaseWordsOrPhrases
import com.example.autocomplete_system.DataClases.Word
import com.example.autocomplete_system.R
import com.example.autocomplete_system.databinding.ItemLastWordsOrPhrasesSearchedBinding

class WordOrPhraseAdapter : RecyclerView.Adapter<WordOrPhraseAdapter.WordOrPhraseAdapterViewHolder>() {

    private var context: Context? = null
    private var listOfWordsAndPhrases = mutableListOf<Word>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordOrPhraseAdapter.WordOrPhraseAdapterViewHolder {
        context = parent.context
        return WordOrPhraseAdapterViewHolder(
            ItemLastWordsOrPhrasesSearchedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: WordOrPhraseAdapter.WordOrPhraseAdapterViewHolder,
        position: Int
    ) {
        holder.binding(listOfWordsAndPhrases[position])
    }

    override fun getItemCount(): Int = listOfWordsAndPhrases.size

    inner class WordOrPhraseAdapterViewHolder(val binding: ItemLastWordsOrPhrasesSearchedBinding) :
            RecyclerView.ViewHolder(binding.root){

                fun binding(data: Word){
                    binding.wordOrPhrase.text = data.word
                    if(data.imageWord == 1){
                        binding.imageWordOrPhrase.setImageResource(R.drawable.word)
                    } else {
                        binding.imageWordOrPhrase.setImageResource(R.drawable.phrase)
                    }
                }
            }

    fun addWordsAndPhrases(newWord: List<Word>){
        listOfWordsAndPhrases.clear()
        listOfWordsAndPhrases.addAll(newWord)
    }
}