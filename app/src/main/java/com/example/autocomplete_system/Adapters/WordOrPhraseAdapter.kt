package com.example.autocomplete_system.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autocomplete_system.DataClases.DataClaseWordsOrPhrases
import com.example.autocomplete_system.databinding.ItemLastWordsOrPhrasesSearchedBinding

class WordOrPhraseAdapter : RecyclerView.Adapter<WordOrPhraseAdapter.WordOrPhraseAdapterViewHolder>() {

    private var context: Context? = null
    private var listOfWordsAndPhrases = mutableListOf<DataClaseWordsOrPhrases>()
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

                fun binding(data: DataClaseWordsOrPhrases){
                    binding.wordOrPhrase.text = data.wordOrPhraseDC
                    binding.imageWordOrPhrase.setImageResource(data.imageOfWordOrPhraseDC)
                }
            }

    fun addWordsAndPhrases(newDataClaseWordsOrPhrases: List<DataClaseWordsOrPhrases>){
        listOfWordsAndPhrases.clear()
        listOfWordsAndPhrases.addAll(newDataClaseWordsOrPhrases)
    }
}