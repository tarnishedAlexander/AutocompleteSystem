<div align="center">
  
  # Proyecto Algorítmica 2: Autocomplete System
  ### Integrantes: 
  Cruz Alexander<br/>
  Zamorano Marian<br/>
  Paricollo Luis<br/>

    
</div>

### introducción 

En el sistema de Autocompletado, se emplea una estructura de Trie para anticipar las expresiones del usuario a partir del texto ingresado, presentando candidatos potenciales a medida que se introducen caracteres. Esto se logra mediante un array del tamaño del alfabeto, que puede ser de 27 para palabras del alfabeto o 52 si se incluyen may\'usculas, y as\'i sucesivamente. Inicialmente, todas las casillas de este array se encuentran como nulas. El \'indice de la palabra se determina utilizando el valor ASCII y se crea un nodo en esa posici\'on. Para un control m\'as preciso, se establece una variable booleana en el nodo para indicar si es el nodo final de una palabra o no.

Para mostrar las posibles predicciones en la pantalla, se utiliza un algoritmo de Depth-First Search (DFS) para recorrer el Trie por filas, siguiendo los caracteres ingresados. De esta manera, se extraen los caracteres uno a uno y se presentan en una lista en pantalla, ofreciendo al usuario opciones de palabras que coinciden con su entrada actual. Esta t\'ecnica proporciona una experiencia de usuario más intuitiva y eficiente al prever las palabras que el usuario podría querer expresar.
La complejidad del uso del Trie para almacenar las palabras es de O(N * M * C) donde "N" es el número total de cadenas, "M" es la longitud m\'axima de una cadena y "C" es el tamaño del alfabeto utilizado, y el uso del DFS para recorrer y extraer las palabras es de  O(V + E) donde "V" es el numero de vertices y "E" el numero de aristas.

Este proyecto sigue las mismas reglas que la de un buscaminas ordinario que si accedemos a una casilla que contenia una mina perdemos el juego.<br/>

### Instalación
