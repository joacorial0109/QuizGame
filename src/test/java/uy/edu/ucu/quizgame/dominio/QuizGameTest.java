package uy.edu.ucu.quizgame.dominio;

import org.junit.Assert;
import org.junit.Test;
import uy.edu.ucu.quizgame.tda.TDALista;

public class QuizGameTest {

    @Test
    public void noDuplicadosJugadorNiPregunta() {
        QuizGame g = new QuizGame();
        Assert.assertTrue(g.registrarJugador(new Jugador(1, "A")));
        Assert.assertFalse(g.registrarJugador(new Jugador(1, "Otro")));
        String[] op = { "x", "y" };
        Assert.assertTrue(g.registrarPregunta(new Pregunta(10, "?", op, 1, "c")));
        Assert.assertFalse(g.registrarPregunta(new Pregunta(10, "!", op, 2, "c")));
    }

    @Test
    public void eliminarPreguntaDelRegistro() {
        QuizGame g = new QuizGame();
        g.registrarPregunta(new Pregunta(5, "x", new String[] { "a" }, 1, "c"));
        Assert.assertTrue(g.eliminarPreguntaPorId(5));
        Assert.assertNull(g.buscarPreguntaPorId(5));
    }

    @Test
    public void partidaTurnosGanadorYPuntajes() {
        QuizGame g = new QuizGame();
        g.registrarJugador(new Jugador(1, "Ana"));
        g.registrarJugador(new Jugador(2, "Luis"));
        String[] op = { "a", "b" };
        g.registrarPregunta(new Pregunta(1, "q1", op, 1, "t"));
        g.registrarPregunta(new Pregunta(2, "q2", op, 2, "t"));

        Assert.assertTrue(g.iniciarPartida());
        Assert.assertTrue(g.hayPartidaEnCurso());
        Assert.assertNotNull(g.jugarTurno(1));
        Assert.assertEquals(10, g.buscarJugadorPorId(1).getPuntajeActual());
        g.jugarTurno(2);
        Assert.assertEquals(10, g.buscarJugadorPorId(2).getPuntajeActual());

        TDALista<String> puntajes = g.resumenPuntajes();
        Assert.assertEquals(2, puntajes.tamaño());

        TDALista<Jugador> gan = g.obtenerGanadores();
        Assert.assertEquals(2, gan.tamaño());
    }

    @Test
    public void deshacerUltimaRespuesta() {
        QuizGame g = new QuizGame();
        g.registrarJugador(new Jugador(1, "Solo"));
        g.registrarPregunta(new Pregunta(1, "p", new String[] { "w", "x" }, 1, "c"));
        g.iniciarPartida();
        g.jugarTurno(1);
        Assert.assertEquals(0, g.getPreguntasPendientes().tamaño());
        Assert.assertFalse(g.getRespuestasParaDeshacer().esVacio());

        Assert.assertTrue(g.deshacerUltimaRespuesta());
        Assert.assertEquals(1, g.getPreguntasPendientes().tamaño());
        Assert.assertEquals(0, g.buscarJugadorPorId(1).getPuntajeActual());
        Assert.assertTrue(g.hayPartidaEnCurso());
    }

    @Test
    public void historialDelJugadorSeActualiza() {
        QuizGame g = new QuizGame();
        g.registrarJugador(new Jugador(1, "A"));
        g.registrarPregunta(new Pregunta(1, "q", new String[] { "a", "b" }, 1, "c"));
        g.iniciarPartida();
        g.jugarTurno(1);
        Assert.assertEquals(1, g.buscarJugadorPorId(1).getHistorialRespuestas().tamanio());
    }
}
