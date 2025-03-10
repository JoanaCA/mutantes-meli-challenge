# Challenge

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.



Te ha contratado a ti para que desarrolles un proyecto que detecte si un
humano es mutante basándose en su secuencia de ADN.


Para eso te ha pedido crear un programa con un método o funcion con la siguiente firma (En
alguno de los siguiente Ienguajes: Java / Golang / C-C++ / Javascript (node)/ Python/ Ruby):


**boolean isMutant(String[] dna); / Ejemplo Java**


En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T, C,G), las
cuales representa cada base nitrogenada del ADN

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
lguales, de forma oblicua, horizontal o vertical.


Ejemplo (Caso_mutante):


Stringl] dna = ("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
En este caso el lamado a la función isMutant(dna) devuelve "true*.


Desarrolla el algoritmo de la manera más eficiente posible.

## Desafios:


**Nivel 1:**
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por
Magneto.


**Nivel 2:**
Crear una API REST, hostear esa API en un cloud computing Nibre (Google App Engine
Amazon AWS, etc), crear el servicio "/mutant" en donde se pueda detectar si un humano es
mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el
siguiente formato,


POST --> /mutant/


{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG]
}

En caso de verificar un mutante, deberia devolver un HTTP 200-OK, en caso contrario un
403-Forbidden


**Nivel 3:**
Anexar una base de datos, la cual guarde los ADN's verificados con la API.
Solo 1
registro por ADN.
Exponer un servicio extra "/stats" que devuelva un Json con las estadisticas de las
verificaciones de ADN: ("count_mutant, dna":40, "count_human dna":100: "ratio":0.4)


Tener en cuenta que la API puede recibir fluctuaciones agresivas de trafico (Entre 100 y 1
millón de peticiones por segundo).

Test-Automáticos, Code coverage > 80%.