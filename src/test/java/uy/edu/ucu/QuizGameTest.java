package uy.edu.ucu;

import org.junit.jupiter.api.Test;
import uy.edu.ucu.quizgame.dominio.Jugador;
import uy.edu.ucu.quizgame.dominio.Pregunta;
import uy.edu.ucu.quizgame.dominio.QuizGame;

import static org.junit.jupiter.api.Assertions.*;

public class QuizGameTest {

    @Test
    public void noPermiteRegistrarJugadoresDuplicados() {
        QuizGame quizGame = new QuizGame();

        Jugador jugador1 = new Jugador(1, "Joaco");
        Jugador jugador2 = new Jugador(1, "Otro Joaco");

        assertTrue(quizGame.registrarJugador(jugador1));
        assertFalse(quizGame.registrarJugador(jugador2));
        assertEquals(1, quizGame.getJugadores().tamaño());
    }

    @Test
    public void noPermiteRegistrarPreguntasDuplicadas() {
        QuizGame quizGame = new QuizGame();

        Pregunta pregunta1 = crearPregunta(1, 1);
        Pregunta pregunta2 = crearPregunta(1, 2);

        assertTrue(quizGame.registrarPregunta(pregunta1));
        assertFalse(quizGame.registrarPregunta(pregunta2));
        assertEquals(1, quizGame.getPreguntasRegistradas().tamaño());
    }

    @Test
    public void noIniciaPartidaSinJugadoresOPreguntas() {
        QuizGame quizGameSinDatos = new QuizGame();
        assertFalse(quizGameSinDatos.iniciarPartida());

        QuizGame quizGameSinPreguntas = new QuizGame();
        quizGameSinPreguntas.registrarJugador(new Jugador(1, "Joaco"));
        assertFalse(quizGameSinPreguntas.iniciarPartida());

        QuizGame quizGameSinJugadores = new QuizGame();
        quizGameSinJugadores.registrarPregunta(crearPregunta(1, 1));
        assertFalse(quizGameSinJugadores.iniciarPartida());
    }

    @Test
    public void iniciarPartidaCargaTurnosYPreguntasPendientes() {
        QuizGame quizGame = new QuizGame();

        quizGame.registrarJugador(new Jugador(1, "Joaco"));
        quizGame.registrarJugador(new Jugador(2, "Ian"));
        quizGame.registrarPregunta(crearPregunta(1, 1));
        quizGame.registrarPregunta(crearPregunta(2, 2));

        assertTrue(quizGame.iniciarPartida());

        assertTrue(quizGame.hayPartidaEnCurso());
        assertEquals(2, quizGame.getTurnosJugadores().tamaño());
        assertEquals(2, quizGame.getPreguntasPendientes().tamaño());
    }

    @Test
    public void respuestaCorrectaSumaPuntajeYCorrectas() {
        QuizGame quizGame = new QuizGame();
        Jugador jugador = new Jugador(1, "Joaco");

        quizGame.registrarJugador(jugador);
        quizGame.registrarPregunta(crearPregunta(1, 1));
        quizGame.iniciarPartida();

        quizGame.jugarTurno(1);

        assertEquals(10, jugador.getPuntajeActual());
        assertEquals(1, jugador.getCantidadCorrectas());
        assertEquals(1, jugador.getHistorialRespuestas().tamaño());
    }

    @Test
    public void respuestaIncorrectaNoSumaPuntajeNiCorrectas() {
        QuizGame quizGame = new QuizGame();
        Jugador jugador = new Jugador(1, "Joaco");

        quizGame.registrarJugador(jugador);
        quizGame.registrarPregunta(crearPregunta(1, 1));
        quizGame.iniciarPartida();

        quizGame.jugarTurno(2);

        assertEquals(0, jugador.getPuntajeActual());
        assertEquals(0, jugador.getCantidadCorrectas());
        assertEquals(1, jugador.getHistorialRespuestas().tamaño());
    }

    @Test
    public void obtieneGanadorConMayorPuntaje() {
        QuizGame quizGame = new QuizGame();

        Jugador joaco = new Jugador(1, "Joaco");
        Jugador ian = new Jugador(2, "Ian");

        quizGame.registrarJugador(joaco);
        quizGame.registrarJugador(ian);

        joaco.sumarPuntaje(10);
        ian.sumarPuntaje(20);

        assertEquals(1, quizGame.obtenerGanadores().tamaño());
        assertEquals(ian, quizGame.obtenerGanadores().obtener(0));
    }

    @Test
    public void deshacerUltimaRespuestaReviertePuntajeCorrectasEHistorial() {
        QuizGame quizGame = new QuizGame();
        Jugador jugador = new Jugador(1, "Joaco");

        quizGame.registrarJugador(jugador);
        quizGame.registrarPregunta(crearPregunta(1, 1));
        quizGame.iniciarPartida();

        quizGame.jugarTurno(1);

        assertEquals(10, jugador.getPuntajeActual());
        assertEquals(1, jugador.getCantidadCorrectas());
        assertEquals(1, jugador.getHistorialRespuestas().tamaño());

        assertTrue(quizGame.deshacerUltimaRespuesta());

        assertEquals(0, jugador.getPuntajeActual());
        assertEquals(0, jugador.getCantidadCorrectas());
        assertEquals(0, jugador.getHistorialRespuestas().tamaño());
        assertEquals(1, quizGame.getPreguntasPendientes().tamaño());
    }

    private Pregunta crearPregunta(int id, int respuestaCorrecta) {
        return new Pregunta(
                id,
                "Pregunta " + id,
                new String[]{"Opcion 1", "Opcion 2", "Opcion 3"},
                respuestaCorrecta,
                "General"
        );
    }
}