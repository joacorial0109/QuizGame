package uy.edu.ucu.quizgame;

import org.junit.jupiter.api.Test;
import uy.edu.ucu.quizgame.dominio.Jugador;
import uy.edu.ucu.quizgame.dominio.Pregunta;
import uy.edu.ucu.quizgame.dominio.Respuesta;

import static org.junit.jupiter.api.Assertions.*;

public class RespuestaTest {

    @Test
    public void deberiaCrearRespuestaCorrectaYAsignarPuntaje() {
        String[] opciones = {"A", "B", "C"};
        Pregunta pregunta = new Pregunta(1, "Test", opciones, 2, "Categoria");
        Jugador jugador = new Jugador(1, "Ian");

        Respuesta respuesta = new Respuesta(jugador, pregunta, 2);

        assertEquals(jugador, respuesta.getJugador());
        assertEquals(pregunta, respuesta.getPregunta());
        assertEquals(2, respuesta.getIdRespuestaDada());
        assertTrue(respuesta.esCorrecta());
        assertEquals(10, respuesta.getPuntajeOtorgado());
    }

    @Test
    public void deberiaCrearRespuestaIncorrectaYAsignarCeroPuntaje() {
        String[] opciones = {"A", "B", "C"};
        Pregunta pregunta = new Pregunta(1, "Test", opciones, 2, "Categoria");
        Jugador jugador = new Jugador(1, "Ian");

        Respuesta respuesta = new Respuesta(jugador, pregunta, 1);

        assertFalse(respuesta.esCorrecta());
        assertEquals(0, respuesta.getPuntajeOtorgado());
    }

    @Test
    public void deberiaAsociarCorrectamenteJugadorYPregunta() {
        String[] opciones = {"A", "B", "C"};
        Pregunta pregunta = new Pregunta(1, "Test", opciones, 1, "Cat");
        Jugador jugador = new Jugador(1, "Ian");

        Respuesta respuesta = new Respuesta(jugador, pregunta, 1);

        assertSame(jugador, respuesta.getJugador());
        assertSame(pregunta, respuesta.getPregunta());
    }
}
