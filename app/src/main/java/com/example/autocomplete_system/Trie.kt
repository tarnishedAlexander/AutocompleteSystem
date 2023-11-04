package com.example.autocomplete_system

class Trie {
    private var root : TrieNode = TrieNode()

    fun insertWord(word:String) {
        var currentNode:TrieNode = root
        //recorre toda la palabra
        for(i in 0 until word.length){
            val character:Int = word[i] - 'a'
            //si en el caracter de la palabra dentro de la casilla del array es null lo agrega
            if(currentNode.children[character] == null){
                currentNode.children[character] = TrieNode()
            }
            //cambia el puntero al nodo con el caracter actualizado
            currentNode = currentNode.children[character]!!
            currentNode.currentCharacter = word[i]
        }
        //termina el bucle significa que llego al final de palabra
        currentNode.isWord = true
    }

    fun searchWord(word: String):Boolean{
        var currentNode:TrieNode = root
        //recorre la palabra
        for(i in 0 until word.length){
            val character:Int = word[i] - 'a'
            //con el indice de la palabra busca la palabra
            //si es null significa que no hay palabra
            if(currentNode.children[character] == null){
                return false
            }
            //apunta al siguiente caracter
            currentNode = currentNode.children[character]!!
        }
        println(currentNode.currentCharacter)
        return currentNode.isWord
    }

    //busca la palabra con los prefijos
    fun autoComplete(prefix:String):List<String>{
        val result: MutableList<String> = mutableListOf()
        var currentNode: TrieNode? = root
        for(i in 0 until prefix.length){
            val character:Int = prefix[i] - 'a'
            if(currentNode?.children?.get(character) == null){
                return result
            }
            currentNode = currentNode.children[character]
        }
        //dfs para buscar por fila de nodos encontrando todas los caracteres que puede matchear
        fun dfs(node:TrieNode, word: String){
            if(node.isWord){
                result.add(prefix+word)
            }

            for(i in 0 until 27){
                val childNode = node.children[i]
                if(childNode != null){
                    dfs(childNode, word + childNode.currentCharacter)
                }
            }
        }
        currentNode?.let { dfs(it, "") }
        return result
    }
}