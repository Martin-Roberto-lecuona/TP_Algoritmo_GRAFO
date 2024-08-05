# Algoritmo A*
## Definicion
Algoritmo de busqueda eficiente para ir de un punto a a un punto b de manera eficiente mediante una heurustica determinada. 
El algoritmo A* es una búsqueda informada en un grafo que utiliza el principio de la heurística
para guiar la búsqueda hacia las regiones del árbol de decisión que se cree conducen al solución.
A diferencia de otros métodos, como DFS y BFS, no necesita almacenamiento adicional para realizar su trabajo.
Esto lo convierte en uno de los más rápidos entre todos los algoritmos de búsqueda.
No referiremos a los nodos como casillas 
## Usos
Su uso en juegos es mas que obvio y claro debido a que es una manera rapida de encontrar en un mapa lleno de casillas y obstaculos como ir del punto donde esta el personaje hacia donde tiene que ir de la manera mas eficiente. Juegos como LOL, Habbo y muchos mas con mobilidad automatica
## Explicacion Algoritmo
+ El mapa esta compuesto de casillas por donde puede pasar y por donde no.
Cada casilla tendra un identificador un peso total F, una distacncia al destino H y un peso de movimieto G. "F = G + H". Recordar el tema de distancia de manhantan

+ El moviento horizontal MH y vertical MV tendran un peso, que puede ser el mismo y los Diagonales seran sqrt(MH^2+MV^2) 

+ Habra una listaAbierta donde se coloca los vecinos de la casilla donde estoy parado. Para el origen poner el la listaCerrada la casilla y la abierta sus vecinos
+ Se le calcula la F a cada vecino. Sabiendo q el G sea en relacion al camino que ya vengo haciendo desde el origen mas el costo del movimiento.
+ Al encontrar la mejor posicion con menor F lo coloco a la listaCerrada
+ Desde el nuevo nodo analizo si hay mejoras en las G de sus vecinosl, siempre y cuando no sea un obstaculo o no este en la listaCerrada. Si sus vecinos no estan en la listaAbierta se colocan en la lista
+ Los casilleros al encontrar un camino mejor pueden cambiar su padre que seria el nodo actual y su G cambiando asi su F. G = G_padre + peso movimiento.
+ Si en los vecinos esta el destino ese el final y termina el algoritmo


## heuristica usada
+ Se usara la heuristica distancia de diagonal. Se movera com el rey en el ajedrez 
+ Distancia =  
    + dx = abs(current_cell.x – goal.x)
    + dy = abs(current_cell.y – goal.y)
    + distanciaDxDy = (dx + dy) + (sqrt(2) - 2) * min(dx, dy)
