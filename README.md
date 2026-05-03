# QuizGame

Clases:

///// Clase Pregunta (IAN) : representa una pregunta del juego.
-Atributos:
  idPregunta
  enunciado
  opciones
  respuestaCorrecta (int id)
  categoria

- Métodos:
  int getIdPregunta() → devuelve el identificador de la pregunta
  String getEnunciado() → devuelve el enunciado
  String[] getOpciones() → devuelve las opciones posibles
  int getRespuestaCorrecta() → devuelve la respuesta correcta
  String getCategoria() → devuelve la categoría
  boolean esCorrecta(int idRespuestaDada) → indica si la respuesta ingresada es correcta

///// Clase Jugador: 
- Atributos:
  idJugador
  nombre
  puntajeActual
  cantidadCorrectas
  historialRespuestas

- Métodos:
  int getIdJugador() → devuelve el identificador del jugador
  String getNombre() → devuelve el nombre
  int getPuntajeActual() → devuelve el puntaje acumulado
  int getCantidadCorrectas() → devuelve la cantidad de respuestas correctas
  void sumarPuntaje(int puntos) → suma puntos al jugador
  void restarPuntaje(int puntos) → resta puntos (útil para deshacer)
  void incrementarCorrectas() → incrementa la cantidad de respuestas correctas
  void decrementarCorrectas() → decrementa la cantidad (para deshacer)
  void agregarRespuesta(Respuesta respuesta) → agrega una respuesta al historial
  void eliminarUltimaRespuesta() → elimina la última respuesta registrada
  Lista<Respuesta> getHistorialRespuestas() → devuelve el historial del jugador

///// Clase Respuesta: representa una respuesta dada por un jugador a una pregunta.
- Atributos:
  jugador
  pregunta
  respuestaDada
  correcta
  puntajeOtorgado
  
- Métodos:
  Jugador getJugador() → devuelve el jugador que respondió
  Pregunta getPregunta() → devuelve la pregunta respondida
  String getRespuestaDada() → devuelve la respuesta ingresada
  boolean esCorrecta() → indica si la respuesta fue correcta
  int getPuntajeOtorgado() → devuelve los puntos obtenidos

///// Clase QuizGame:
- Atributos:
  jugadores
  preguntasRegistradas
  turnosJugadores (cola circular)
  preguntasPendientes (cola)
  respuestasRegistradas (pila)

- Métodos:
  void registrarJugador(Jugador jugador) → agrega un jugador al sistema
  boolean existeJugador(int idJugador) → verifica si ya existe
  void registrarPregunta(Pregunta pregunta) → agrega una pregunta
  boolean existePregunta(int idPregunta) → evita duplicados
  void eliminarPregunta(int idPregunta) → elimina una pregunta
  void iniciarTurnos() → carga la cola circular de jugadores
  Jugador obtenerJugadorActual() → devuelve el jugador en turno
  Pregunta obtenerPreguntaActual() → devuelve la siguiente pregunta
  void procesarRespuesta(String respuestaDada) → valida la respuesta, actualiza puntaje e historial
  int calcularPuntajeTotal(Jugador jugador) → devuelve el puntaje total
  Jugador determinarGanador() → retorna el jugador con mayor puntaje
  void deshacerUltimaRespuesta() → revierte la última respuesta registrada

///// Clase Main: ejecución de flujo del juego
crear instancia de QuizGame
cargar jugadores
cargar preguntas
iniciar la partida
mostrar resultados por consola


//////////////////////////////////////////////////////////////////

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
