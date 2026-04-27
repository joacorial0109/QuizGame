# QuizGame

Gente tenemos que hacer: 

Registrar jugadores (sin duplicados)
Registrar y eliminar preguntas
Manejar los turnos con una cola circular
Manejar las preguntas con una cola
Guardar un historial de respuestas
Calcular puntajes
Determinar el ganador

Pense que la estructura es la que ya esta en el repo y en el dominio estaria las clases: clases como Jugador, Pregunta, etc
despues en el de estructuras poner: cola, cola circular, lista, pila, en servicios poner lógica del juego

Tendriamos que separarnos en que unoo haga y las siguientes funciones 
Clase Jugador
GestorJugadores 
Funciones:
registrarJugador()
buscarJugadorPorId()
listarJugadores()
evitar duplicados

Otro tiene que hacer 
Clase Pregunta
GestorPreguntas
Funciones: 
egistrarPregunta()
eliminarPregunta()
buscarPreguntaPorId()
listarPreguntas()
evitar duplicados

Otro
Cola
ColaCircular
Lista
Funciones: 
encolar()
desencolar()
estaVacia()
tamanio()
avanzar() (cola circular)

Y el ultimo 
JuegoQuiz
Main 
Funciones: 
iniciarPartida()
responderPregunta()
pasarTurno()
actualizarPuntaje()
guardarHistorial()
mostrarGanador()
