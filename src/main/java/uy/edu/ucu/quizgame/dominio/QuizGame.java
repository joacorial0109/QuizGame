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

    public boolean iniciarPartida() {
        if (jugadores.tamaño() == 0 || preguntasRegistradas.tamaño() == 0) {
            return false;
        }
        for (int i = 0; i < jugadores.tamaño(); i++) {
            jugadores.obtener(i).reiniciarParaNuevaPartida();
        }
        turnosJugadores.vaciar();
        preguntasPendientes.vaciar();
        respuestasParaDeshacer.vaciar();
        for (int i = 0; i < preguntasRegistradas.tamaño(); i++) {
            preguntasPendientes.poneEnCola(preguntasRegistradas.obtener(i));
        }
        for (int i = 0; i < jugadores.tamaño(); i++) {
            turnosJugadores.poneEnCola(jugadores.obtener(i));
        }
        partidaEnCurso = true;
        return true;
    }

    public String jugarTurno(int numeroOpcion) {
        if (!partidaEnCurso) {
            return null;
        }
        if (preguntasPendientes.esVacio() || turnosJugadores.esVacio()) {
            partidaEnCurso = false;
            return "No hay más preguntas o turnos.";
        }
        Jugador actual = turnosJugadores.frente();
        Pregunta pregunta = preguntasPendientes.quitaDeCola();
        Respuesta respuesta = new Respuesta(actual, pregunta, numeroOpcion);
        if (respuesta.esCorrecta()) {
            actual.sumarPuntaje(respuesta.getPuntajeOtorgado());
            actual.incrementarCorrectas();
        }
        actual.agregarRespuesta(respuesta);
        respuestasParaDeshacer.mete(respuesta);
        turnosJugadores.rotar();
        if (preguntasPendientes.esVacio()) {
            partidaEnCurso = false;
        }
        return actual.getNombre() + ": "
                + (respuesta.esCorrecta() ? "correcto (+10)" : "incorrecto")
                + " | " + pregunta.getEnunciado();
    }

    public TDALista<String> resumenPuntajes() {
        TDALista<String> lineas = new ListaEnlazada<String>();
        for (int i = 0; i < jugadores.tamaño(); i++) {
            Jugador j = jugadores.obtener(i);
            lineas.agregar(j.getNombre() + ": " + j.getPuntajeActual() + " pts");
        }
        return lineas;
    }

    public TDALista<Jugador> obtenerGanadores() {
        TDALista<Jugador> ganadores = new ListaEnlazada<Jugador>();
        if (jugadores.tamaño() == 0) {
            return ganadores;
        }
        int max = jugadores.obtener(0).getPuntajeActual();
        for (int i = 1; i < jugadores.tamaño(); i++) {
            int p = jugadores.obtener(i).getPuntajeActual();
            if (p > max) {
                max = p;
            }
        }
        for (int i = 0; i < jugadores.tamaño(); i++) {
            Jugador j = jugadores.obtener(i);
            if (j.getPuntajeActual() == max) {
                ganadores.agregar(j);
            }
        }
        return ganadores;
    }

    public boolean deshacerUltimaRespuesta() {
        if (respuestasParaDeshacer.esVacio()) {
            return false;
        }
        Respuesta ultima = respuestasParaDeshacer.saca();
        deshacerRotacionTurno();
        preguntasPendientes.devolverAlFrente(ultima.getPregunta());
        Jugador j = ultima.getJugador();
        j.restarPuntaje(ultima.getPuntajeOtorgado());
        if (ultima.esCorrecta()) {
            j.decrementarCorrectas();
        }
        j.eliminarUltimaRespuesta();
        partidaEnCurso = true;
        return true;
    }

    private void deshacerRotacionTurno() {
        int n = turnosJugadores.tamaño();
        if (n <= 1) {
            return;
        }
        for (int k = 0; k < n - 1; k++) {
            turnosJugadores.rotar();
        }
    }

    public boolean hayPartidaEnCurso() {
        return partidaEnCurso;
    }

    public void finalizarPartida() {
        partidaEnCurso = false;
    }

    public TDALista<Jugador> getJugadores() {
        return jugadores;
    }

    public TDALista<Pregunta> getPreguntasRegistradas() {
        return preguntasRegistradas;
    }

    public Cola<Jugador> getTurnosJugadores() {
        return turnosJugadores;
    }

    public Cola<Pregunta> getPreguntasPendientes() {
        return preguntasPendientes;
    }

    public Pila<Respuesta> getRespuestasParaDeshacer() {
        return respuestasParaDeshacer;
    }

}
