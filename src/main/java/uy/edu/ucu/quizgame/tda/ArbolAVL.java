package uy.edu.ucu.quizgame.tda;

import java.util.function.Consumer;

public class ArbolAVL<T extends Comparable<T>> {

    private ElementoAVL<T> raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public void insertar(T dato) {
        raiz = insertar(raiz, dato);
    }

    private ElementoAVL<T> insertar(ElementoAVL<T> nodo, T dato) {
        if (nodo == null) {
            return new ElementoAVL<>(dato);
        }

        int cmp = dato.compareTo(nodo.getDato());

        if (cmp < 0) {
            nodo.setHijoIzquierdo(insertar(nodo.getHijoIzquierdo(), dato));
        } else if (cmp > 0) {
            nodo.setHijoDerecho(insertar(nodo.getHijoDerecho(), dato));
        } else {
            return nodo; // No inserta duplicados
        }

        nodo.actualizarAltura();
        return nodo.balancear();
    }

    public T buscar(T dato) {
        ElementoAVL<T> encontrado = buscar(raiz, dato);
        return encontrado != null ? encontrado.getDato() : null;
    }

    private ElementoAVL<T> buscar(ElementoAVL<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        }

        int cmp = dato.compareTo(nodo.getDato());

        if (cmp == 0) {
            return nodo;
        } else if (cmp < 0) {
            return buscar(nodo.getHijoIzquierdo(), dato);
        } else {
            return buscar(nodo.getHijoDerecho(), dato);
        }
    }

    public boolean eliminar(T dato) {
        if (buscar(dato) == null) {
            return false;
        }

        raiz = eliminar(raiz, dato);
        return true;
    }

    private ElementoAVL<T> eliminar(ElementoAVL<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        }

        int cmp = dato.compareTo(nodo.getDato());

        if (cmp < 0) {
            nodo.setHijoIzquierdo(eliminar(nodo.getHijoIzquierdo(), dato));
        } else if (cmp > 0) {
            nodo.setHijoDerecho(eliminar(nodo.getHijoDerecho(), dato));
        } else {

            if (nodo.getHijoIzquierdo() == null) {
                return nodo.getHijoDerecho();
            }

            if (nodo.getHijoDerecho() == null) {
                return nodo.getHijoIzquierdo();
            }

            ElementoAVL<T> sucesor = minimo(nodo.getHijoDerecho());
            nodo.setDato(sucesor.getDato());
            nodo.setHijoDerecho(eliminar(nodo.getHijoDerecho(), sucesor.getDato()));
        }

        nodo.actualizarAltura();
        return nodo.balancear();
    }

    private ElementoAVL<T> minimo(ElementoAVL<T> nodo) {
        while (nodo.getHijoIzquierdo() != null) {
            nodo = nodo.getHijoIzquierdo();
        }
        return nodo;
    }

    public void inOrder(Consumer<T> consumidor) {
        inOrder(raiz, consumidor);
    }

    private void inOrder(ElementoAVL<T> nodo, Consumer<T> consumidor) {
        if (nodo != null) {
            inOrder(nodo.getHijoIzquierdo(), consumidor);
            consumidor.accept(nodo.getDato());
            inOrder(nodo.getHijoDerecho(), consumidor);
        }
    }

    public void preOrder(Consumer<T> consumidor) {
        preOrder(raiz, consumidor);
    }

    private void preOrder(ElementoAVL<T> nodo, Consumer<T> consumidor) {
        if (nodo != null) {
            consumidor.accept(nodo.getDato());
            preOrder(nodo.getHijoIzquierdo(), consumidor);
            preOrder(nodo.getHijoDerecho(), consumidor);
        }
    }

    public void postOrder(Consumer<T> consumidor) {
        postOrder(raiz, consumidor);
    }

    private void postOrder(ElementoAVL<T> nodo, Consumer<T> consumidor) {
        if (nodo != null) {
            postOrder(nodo.getHijoIzquierdo(), consumidor);
            postOrder(nodo.getHijoDerecho(), consumidor);
            consumidor.accept(nodo.getDato());
        }
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public ElementoAVL<T> obtenerRaiz() {
        return raiz;
    }
}
