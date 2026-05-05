package uy.edu.ucu.quizgame.tda;

public class ElementoAVL<T extends Comparable<T>> {

    private T dato;
    private ElementoAVL<T> izq;
    private ElementoAVL<T> der;
    private int altura;

    public ElementoAVL(T dato) {
        this.dato = dato;
        this.altura = 1;
    }

    public int altura(ElementoAVL<T> n) {
        return n == null ? 0 : n.altura;
    }

    public void actualizarAltura() {
        altura = 1 + Math.max(altura(izq), altura(der));
    }

    public int balance() {
        return altura(izq) - altura(der);
    }

    public ElementoAVL<T> balancear() {

        int fb = balance();

        // Izquierda pesada
        if (fb > 1) {
            if (izq.balance() < 0)
                izq = izq.rotarIzquierda();
            return rotarDerecha();
        }

        // Derecha pesada
        if (fb < -1) {
            if (der.balance() > 0)
                der = der.rotarDerecha();
            return rotarIzquierda();
        }

        return this;
    }

    private ElementoAVL<T> rotarDerecha() {
        ElementoAVL<T> x = izq;
        izq = x.der;
        x.der = this;

        this.actualizarAltura();
        x.actualizarAltura();

        return x;
    }

    private ElementoAVL<T> rotarIzquierda() {
        ElementoAVL<T> y = der;
        der = y.izq;
        y.izq = this;

        this.actualizarAltura();
        y.actualizarAltura();

        return y;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public ElementoAVL<T> getHijoIzquierdo() {
        return izq;
    }

    public void setHijoIzquierdo(ElementoAVL<T> insertar) {
        this.izq=insertar;
    }

    public ElementoAVL<T> getHijoDerecho() {
        return der;
    }

    public void setHijoDerecho(ElementoAVL<T> insertar) {
        this.der=insertar;
    }
}


