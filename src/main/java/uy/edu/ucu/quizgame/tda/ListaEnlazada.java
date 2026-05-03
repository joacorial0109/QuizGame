package uy.edu.ucu.quizgame.tda;

import java.util.Comparator;
import java.util.function.Predicate;

// Copia aquí tu implementación con nodos y referencias del TDA Lista.
public class ListaEnlazada<T> implements TDALista<T> {

    private Nodo<T> primero;
    private int tamaño;

    public ListaEnlazada(){
        this.primero=null;
        this.tamaño=0;

    }


    @Override
    public void agregar(T elem) {
        Nodo<T> nuevo = new Nodo<>(elem);

        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo<T> actual = primero;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }

        tamaño++;
    }

    @Override
    public void agregar(int index, T elem) {
        if (index < 0 || index > tamaño) {
            throw new IndexOutOfBoundsException();
        }

        Nodo<T> nuevo = new Nodo<>(elem);

        if (index == 0) {
            nuevo.siguiente = primero;
            primero = nuevo;
        } else {
            Nodo<T> actual = primero;

            for (int i = 0; i < index - 1; i++) {
                actual = actual.siguiente;
            }

            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }

        tamaño++;

    }

    @Override
    public T obtener(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException();
        }

        Nodo<T> actual = primero;

        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }

        return actual.dato;
    }

    @Override
    public T remover(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException();
        }

        Nodo<T> actual = primero;
        T datoEliminado;

        if (index == 0) {
            datoEliminado = primero.getDato();
            primero = primero.getSiguiente();
        } else {
            for (int i = 0; i < index - 1; i++) {
                actual = actual.getSiguiente();
            }

            Nodo<T> nodoAEliminar = actual.getSiguiente();
            datoEliminado = nodoAEliminar.getDato();

            actual.setSiguiente(nodoAEliminar.getSiguiente());
        }

        tamaño--;
        return datoEliminado;

    }

    @Override
    public boolean remover(T elem) {
        if (primero == null) return false;

        if (primero.getDato().equals(elem)) {
            primero = primero.getSiguiente();
            tamaño--;
            return true;
        }

        Nodo<T> actual = primero;

        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(elem)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }

    @Override
    public boolean contiene(T elem) {
        Nodo<T> actual = primero;

        while (actual != null) {
            if (actual.getDato().equals(elem)) return true;
            actual = actual.getSiguiente();
        }

        return false;
    }

    @Override
    public int indiceDe(T elem) {
        Nodo<T> actual = primero;
        int index = 0;

        while (actual != null) {
            if (actual.getDato().equals(elem)) {
                return index;
            }
            actual = actual.getSiguiente();
            index++;
        }

        return -1; // no encontrado
    }

    @Override
    public T buscar(Predicate<T> criterio) {
        Nodo<T> actual = primero;

        while (actual != null) {
            if (criterio.test(actual.getDato())) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator) { // Complejidad O(n^2)
        if (tamaño < 2) return this;

        boolean swapped;

        do {
            swapped = false;
            Nodo<T> actual = primero;

            while (actual.getSiguiente() != null) {
                Nodo<T> siguiente = actual.getSiguiente();

                if (comparator.compare(actual.getDato(), siguiente.getDato()) > 0) {
                    // swap
                    T temp = actual.getDato();
                    actual.dato = siguiente.getDato();
                    siguiente.dato = temp;

                    swapped = true;
                }

                actual = actual.getSiguiente();
            }

        } while (swapped);

        return this;
    }

    @Override
    public int tamaño() {
      return tamaño;
    }

    @Override
    public boolean esVacio() {
        return tamaño==0;
    }

    @Override
    public void vaciar() {
        primero=null;
        tamaño=0;
    }

}
