package uy.edu.ucu.quizgame.dominio;

public class RespuestaHistorial {
    private int idJugador;
    private int idPregunta;
    private boolean correcta;

    public RespuestaHistorial(int idJugador, int idPregunta, boolean correcta) {
        this.idJugador = idJugador;
        this.idPregunta = idPregunta;
        this.correcta = correcta;
    }
}