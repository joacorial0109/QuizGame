package uy.edu.ucu.quizgame.tda;

import java.util.NoSuchElementException;

// Copia aquí tu implementación del TDA Cola.
public class Cola<T> extends ListaEnlazada<T>  implements TDACola<T> {


    @Override
    public T frente() {
        if (this.tamaño() == 0) {
            throw new NoSuchElementException();
        }
        return this.obtener(0);
    }

    @Override
    public boolean poneEnCola(T dato) {
        this.agregar(dato); // al final
        return true;
    }

    @Override
    public T quitaDeCola() {
        if (this.tamaño() == 0) {
            throw new NoSuchElementException();
        }
        return this.remover(0); // saca del frente
    }
    
}
