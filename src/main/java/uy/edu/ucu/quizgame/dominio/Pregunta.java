package uy.edu.ucu.quizgame.dominio;

public class Pregunta {
    private int id;
    private String enunciado;
    private String[] opciones;
    private int respuestaCorrecta;
    private String categoria;

    public Pregunta(int id, String enunciado, String[] opciones, int respuestaCorrecta, String categoria) {
        this.id = id;
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.categoria = categoria;
    }

    public int getId() { return id; }
    public String getEnunciado() { return enunciado; }
    public String[] getOpciones() { return opciones; }
    public int getRespuestaCorrecta() { return respuestaCorrecta; }
}