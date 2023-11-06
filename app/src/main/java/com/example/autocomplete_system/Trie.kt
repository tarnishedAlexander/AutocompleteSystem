package com.example.autocomplete_system

class Trie {
    private var root: TrieNode = TrieNode()

    fun insertWord(word: String) {
        var currentNode: TrieNode = root
        //Recorre toda la palabra
        for (i in 0 until word.length) {
            //accede al indice de los caracteres, agregando un indice al espacio
            val character: Int =
                if (word[i] == ' ') 26 else word[i].toLowerCase() - 'a' //le da un indice al espacio o convierte a minúsculas y calcula su indice

            //Si en el carácter de la palabra dentro de la casilla del array es null, lo agrega
            if (currentNode.children[character] == null) {
                currentNode.children[character] = TrieNode()
            }

            // Cambia el puntero al nodo con el caracter actualizado
            currentNode = currentNode.children[character]!!
            currentNode.currentCharacter = word[i]
        }

        // Termina el bucle significa que llegó al final de la palabra
        currentNode.isWord = true
    }

    //busca la palabra con los prefijos
    fun autoComplete(prefix: String): List<String> {
        //aca se guardaran los resultados
        val result: MutableList<String> = mutableListOf()
        var currentNode: TrieNode? = root

        for (i in 0 until prefix.length) {
            //accede al indice de los caracteres, agregando un indice al espacio
            val character: Int = if (prefix[i] == ' ') 26 else prefix[i].toLowerCase() - 'a'
            //si el nodo actual ya no tiene hijos entonces no retorna nada ya que deja de coincidir
            if (currentNode?.children?.get(character) == null) {
                return result
            }
            //el cambio de puntero al nodo actual
            currentNode = currentNode.children[character]
        }
        //llamando a la funcion dfs para encontrar el matcheo, pero verificando que no sea null(let), it es una referencia al nodo no null
        currentNode?.let { dfs(it, prefix, result) }
        return result
    }

    //dfs para matchear palabras
    fun dfs(node: TrieNode?, prefix: String, result: MutableList<String>) {
        //verifica que no sea null
        node?.let {
            //el caracter se va agregando a la lista
            if (it.isWord) {
                result.add(prefix)
            }
            //dfs como tal
            for (i in 0 until 27) {
                val childNode = it.children[i]
                if (childNode != null) {
                    dfs(childNode, prefix + childNode.currentCharacter, result)
                }
            }
        }
    }
}