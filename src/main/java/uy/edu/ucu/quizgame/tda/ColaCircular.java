package uy.edu.ucu.quizgame.tda;

public class ColaCircular<T> extends Cola<T> implements TDAColaCircular<T> {

    @Override
    public void rotar() {
        if (esVacio()) {
            return;
        }
        T frente = quitaDeCola();
        poneEnCola(frente);
    }
}
