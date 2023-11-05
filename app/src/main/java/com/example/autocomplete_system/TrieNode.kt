package com.example.autocomplete_system

class TrieNode {
    var currentCharacter: Char = ' '
    var isWord: Boolean = false
    var children: Array<TrieNode?> = arrayOfNulls(27)

    init{
        isWord = false
    }
}