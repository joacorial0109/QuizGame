package uy.edu.ucu.quizgame.dominio;

import uy.edu.ucu.quizgame.tda.*;

public class QuizGame {

    private ArbolAVL<Jugador> jugadores;
    private ArbolAVL<Pregunta> preguntasRegistradas;
    private TDACola<Jugador> turnosJugadores;
    private TDACola<Pregunta> preguntasPendientes;
    private Pila<Respuesta> respuestasRegistradas;

    public QuizGame() {
        this.jugadores = new ArbolAVL<Jugador>();
        this.preguntasRegistradas = new ArbolAVL<Pregunta>();
        this.turnosJugadores = new Cola<Jugador>();
        this.preguntasPendientes = new Cola<Pregunta>();
        this.respuestasRegistradas = new Pila<Respuesta>();
    }
}