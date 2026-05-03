package uy.edu.ucu.quizgame.dominio;

public class Pregunta {
    private int idPregunta;
    private String enunciado;
    private String[] opciones;
    private int respuestaCorrecta;
    private String categoria;

    public Pregunta(int idPregunta, String enunciado, String[] opciones, int respuestaCorrecta, String categoria) {
        this.idPregunta = idPregunta;
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.categoria = categoria;
    }

    public int getIdPregunta() { return idPregunta; }
    public String getEnunciado() { return enunciado; }
    public String[] getOpciones() { return opciones; }
    public int getRespuestaCorrecta() { return respuestaCorrecta; }
    public String getCategoria(){ return categoria;}

    public boolean esCorrecta(int idRespuestaDada) {
        return respuestaCorrecta==idRespuestaDada;
    }


    @Override
    public String toString() {
        String resultado = enunciado + "\n";
        for (int i = 0; i < opciones.length; i++) {
            resultado += (i + 1) + ". " + opciones[i] + "\n";
        }
        return resultado;
    }
}