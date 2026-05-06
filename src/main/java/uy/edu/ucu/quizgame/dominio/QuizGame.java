package uy.edu.ucu.quizgame.dominio;

import uy.edu.ucu.quizgame.tda.Cola;
import uy.edu.ucu.quizgame.tda.ListaEnlazada;
import uy.edu.ucu.quizgame.tda.Pila;
import uy.edu.ucu.quizgame.tda.TDALista;

public class QuizGame {

    private TDALista<Jugador> jugadores;
    private TDALista<Pregunta> preguntasRegistradas;
    private Cola<Jugador> turnosJugadores;
    private Cola<Pregunta> preguntasPendientes;
    private Pila<Respuesta> respuestasParaDeshacer;
    private boolean partidaEnCurso;

    public QuizGame() {
        jugadores = new ListaEnlazada<Jugador>();
        preguntasRegistradas = new ListaEnlazada<Pregunta>();
        turnosJugadores = new Cola<Jugador>();
        preguntasPendientes = new Cola<Pregunta>();
        respuestasParaDeshacer = new Pila<Respuesta>();
        partidaEnCurso = false;
    }

    public boolean registrarJugador(Jugador jugador) {
        if (jugador == null || buscarJugadorPorId(jugador.getIdJugador()) != null) {
            return false;
        }
        jugadores.agregar(jugador);
        return true;
    }

    public boolean registrarPregunta(Pregunta pregunta) {
        if (pregunta == null || buscarPreguntaPorId(pregunta.getIdPregunta()) != null) {
            return false;
        }
        preguntasRegistradas.agregar(pregunta);
        return true;
    }

    public boolean eliminarPreguntaPorId(int idPregunta) {
        for (int i = 0; i < preguntasRegistradas.tamaño(); i++) {
            if (preguntasRegistradas.obtener(i).getIdPregunta() == idPregunta) {
                preguntasRegistradas.remover(i);
                return true;
            }
        }
        return false;
    }

    public Jugador buscarJugadorPorId(int idJugador) {
        for (int i = 0; i < jugadores.tamaño(); i++) {
            Jugador j = jugadores.obtener(i);
            if (j.getIdJugador() == idJugador) {
                return j;
            }
        }
        return null;
    }

    public Pregunta buscarPreguntaPorId(int idPregunta) {
        for (int i = 0; i < preguntasRegistradas.tamaño(); i++) {
            Pregunta p = preguntasRegistradas.obtener(i);
            if (p.getIdPregunta() == idPregunta) {
                return p;
            }
        }
        return null;
    }
}
