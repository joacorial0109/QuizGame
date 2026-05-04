package uy.edu.ucu.quizgame.dominio;

import uy.edu.ucu.quizgame.tda.TDALista;
import uy.edu.ucu.quizgame.tda.ListaEnlazada;

public class Jugador {

    private int idJugador;
    private String nombre;
    private int puntajeActual;
    private int cantidadCorrectas;
    private TDALista<Respuesta> historialRespuestas;

    public Jugador(int idJugador, String nombre) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.puntajeActual = 0;
        this.cantidadCorrectas = 0;
        this.historialRespuestas = new ListaEnlazada<Respuesta>();
    }

    public int getIdJugador() {
        return idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntajeActual() {
        return puntajeActual;
    }

    public int getCantidadCorrectas() {
        return cantidadCorrectas;
    }

    public void sumarPuntaje(int puntos) {
        if (puntos > 0) {
            this.puntajeActual += puntos;
        }
    }

    public void restarPuntaje(int puntos) {
        if (puntos > 0) {
            this.puntajeActual -= puntos;

            if (this.puntajeActual < 0) {
                this.puntajeActual = 0;
            }
        }
    }

    public void incrementarCorrectas() {
        this.cantidadCorrectas++;
    }

    public void decrementarCorrectas() {
        if (this.cantidadCorrectas > 0) {
            this.cantidadCorrectas--;
        }
    }

    public void agregarRespuesta(Respuesta respuesta) {
        if (respuesta != null) {
            this.historialRespuestas.agregar(respuesta);
        }
    }

    public void eliminarUltimaRespuesta() {
        if (!this.historialRespuestas.esVacio()) {
            this.historialRespuestas.remover(this.historialRespuestas.tamaño() - 1);
        }
    }

    public TDALista<Respuesta> getHistorialRespuestas() {
        return historialRespuestas;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "idJugador=" + idJugador +
                ", nombre='" + nombre + '\'' +
                ", puntajeActual=" + puntajeActual +
                ", cantidadCorrectas=" + cantidadCorrectas +
                '}';
    }
}