package uy.edu.ucu.quizgame;

import org.junit.jupiter.api.Test;
import uy.edu.ucu.quizgame.dominio.Pregunta;

import static org.junit.jupiter.api.Assertions.*;

public class PreguntaTest {

    @Test
    public void testCrearPreguntaYGetters() {
        String[] opciones = {"Montevideo", "Buenos Aires", "Santiago"};

        Pregunta pregunta = new Pregunta(
                1,
                "¿Cuál es la capital de Uruguay?",
                opciones,
                1,
                "Geografía"
        );

        assertEquals(1, pregunta.getIdPregunta());
        assertEquals("¿Cuál es la capital de Uruguay?", pregunta.getEnunciado());
        assertArrayEquals(opciones, pregunta.getOpciones());
        assertEquals(1, pregunta.getRespuestaCorrecta());
        assertEquals("Geografía", pregunta.getCategoria());
    }

    @Test
    public void testRespuestaCorrecta() {
        String[] opciones = {"Montevideo", "Buenos Aires", "Santiago"};
        Pregunta pregunta = new Pregunta(1, "¿Capital de Uruguay?", opciones, 1, "Geografía");

        assertTrue(pregunta.esCorrecta(1));
    }

    @Test
    public void testRespuestaIncorrecta() {
        String[] opciones = {"Montevideo", "Buenos Aires", "Santiago"};
        Pregunta pregunta = new Pregunta(1, "¿Capital de Uruguay?", opciones, 1, "Geografía");

        assertFalse(pregunta.esCorrecta(2));
    }

    @Test
    public void testToStringMuestraPreguntaYOpciones() {
        String[] opciones = {"Montevideo", "Buenos Aires", "Santiago"};
        Pregunta pregunta = new Pregunta(1, "¿Capital de Uruguay?", opciones, 1, "Geografía");

        String resultado = pregunta.toString();

        assertTrue(resultado.contains("¿Capital de Uruguay?"));
        assertTrue(resultado.contains("1. Montevideo"));
        assertTrue(resultado.contains("2. Buenos Aires"));
        assertTrue(resultado.contains("3. Santiago"));
    }
}
