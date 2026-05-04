package uy.edu.ucu.quizgame.tda;

public interface Lista<T> {

    void agregar(T elemento);

    T obtener(int indice);

    T eliminar(int indice);

    T eliminarUltimo();

    boolean estaVacia();

    int tamanio();

    boolean contiene(T elemento);
}