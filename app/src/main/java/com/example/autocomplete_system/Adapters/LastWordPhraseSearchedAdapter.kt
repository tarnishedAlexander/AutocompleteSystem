package com.example.autocomplete_system.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autocomplete_system.DataClases.LastWordsOrPhrasesDB
import com.example.autocomplete_system.databinding.ItemLastWordsOrPhrasesSearchedBinding

class LastWordPhraseSearchedAdapter : RecyclerView.Adapter<LastWordPhraseSearchedAdapter.LastWordPhraseSearchedAdapterViewHolder>() {

    private var context: Context? = null
    private var listOfWordsOrPhrases = mutableListOf<LastWordsOrPhrasesDB.WordsOrPhrasesDB>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastWordPhraseSearchedAdapterViewHolder {
        context = parent.context
        return LastWordPhraseSearchedAdapterViewHolder(
            ItemLastWordsOrPhrasesSearchedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: LastWordPhraseSearchedAdapterViewHolder,
        position: Int
    ) {
        holder.binding(listOfWordsOrPhrases[position])
    }

    override fun getItemCount(): Int = listOfWordsOrPhrases.size

    inner class LastWordPhraseSearchedAdapterViewHolder(val binding: ItemLastWordsOrPhrasesSearchedBinding) :
            RecyclerView.ViewHolder(binding.root){

                fun binding(data: LastWordsOrPhrasesDB.WordsOrPhrasesDB){
                    binding.wordOrPhrase.text = data.wordOrPhraseDB
                    binding.imageWordOrPhrase.setImageResource(data.imageOfWordOrPhraseDB)
                }
            }

    fun addWordsOrPhrases(newWordsOrPhrasesDB: List<LastWordsOrPhrasesDB.WordsOrPhrasesDB>){
        listOfWordsOrPhrases.clear()
        listOfWordsOrPhrases.addAll(newWordsOrPhrasesDB)
    }
}