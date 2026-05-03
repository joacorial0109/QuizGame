package uy.edu.ucu.quizgame.dominio;

public class Respuesta {

    private Jugador jugador;
    private Pregunta pregunta;
    private int idRespuestaDada;
    private boolean correcta;
    private int puntajeOtorgado;

    public Respuesta(Jugador jugador, Pregunta pregunta, int idRespuestaDada) {
        this.jugador = jugador;
        this.pregunta = pregunta;
        this.idRespuestaDada = idRespuestaDada;
        this.correcta = pregunta.esCorrecta(idRespuestaDada);

        if (correcta) {
            this.puntajeOtorgado = 10;
        } else {
            this.puntajeOtorgado = 0;
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public int getIdRespuestaDada() {
        return idRespuestaDada;
    }

    public boolean esCorrecta() {
        return correcta;
    }

    public int getPuntajeOtorgado() {
        return puntajeOtorgado;
    }
}
