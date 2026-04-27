package uy.edu.ucu.quizgame.dominio;

public class Jugador {
    private int id;
    private String nombre;
    private int puntaje;

    public Jugador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = 0;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPuntaje() { return puntaje; }

    public void sumarPuntos(int puntos) {
        this.puntaje += puntos;
    }
}