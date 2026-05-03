package uy.edu.ucu.quizgame.dominio;

public class QuizGame {

    private TDALista<Jugador> jugadores;
    private TDALista<Pregunta> preguntasRegistradas;
    private TDAColaCircular<Jugador> turnosJugadores;
    private TDACola<Pregunta> preguntasPendientes;
    private TDAPila<Respuesta> respuestasRegistradas;


}
