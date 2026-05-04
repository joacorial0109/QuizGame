package uy.edu.ucu.quizgame.dominio;

import uy.edu.ucu.quizgame.tda.TDALista;
import uy.edu.ucu.quizgame.tda.TDACola;
import uy.edu.ucu.quizgame.tda.ListaEnlazada;
import uy.edu.ucu.quizgame.tda.Cola;
import uy.edu.ucu.quizgame.tda.Pila;

public class QuizGame {

    private TDALista<Jugador> jugadores;
    private TDALista<Pregunta> preguntasRegistradas;
    private TDACola<Jugador> turnosJugadores;
    private TDACola<Pregunta> preguntasPendientes;
    private Pila<Respuesta> respuestasRegistradas;

    public QuizGame() {
        this.jugadores = new ListaEnlazada<Jugador>();
        this.preguntasRegistradas = new ListaEnlazada<Pregunta>();
        this.turnosJugadores = new Cola<Jugador>();
        this.preguntasPendientes = new Cola<Pregunta>();
        this.respuestasRegistradas = new Pila<Respuesta>();
    }
}