package uy.edu.ucu.quizgame.tda;

import java.util.NoSuchElementException;

// Copia aquí tu implementación del TDA Pila.
public class Pila<T> extends ListaEnlazada<T> implements TDAPila<T> {

    @Override
    public T tope() {
        if (this.tamaño() == 0) {
            throw new NoSuchElementException();
        }
        return this.obtener(0);
    }

    @Override
    public T saca() {
        if (this.tamaño() == 0) {
            throw new NoSuchElementException();
        }
        return this.remover(0);
    }

    @Override
    public void mete(T dato) {
        this.agregar(0, dato); // inserta en el tope
    }
    
}
