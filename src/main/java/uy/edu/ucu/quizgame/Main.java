package uy.edu.ucu.quizgame;

import uy.edu.ucu.quizgame.dominio.Jugador;
import uy.edu.ucu.quizgame.dominio.Pregunta;
import uy.edu.ucu.quizgame.dominio.QuizGame;
import uy.edu.ucu.quizgame.tda.ListaEnlazada;
import uy.edu.ucu.quizgame.tda.TDALista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        QuizGame quizGame = new QuizGame();

        int opcion;

        do {
            System.out.println("\n==== QUIZGAME ====");
            System.out.println("Opciones:");
            System.out.println("1. Iniciar partida");
            System.out.println("2. Eliminar pregunta");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    cargarJugadoresDesdeCSV(quizGame, "src/main/resources/jugadores.csv");
                    cargarPreguntasDesdeCSV(quizGame, "src/main/resources/preguntas.csv");

                    if (quizGame.iniciarPartida()) {
                        jugarPartida(quizGame, sc);
                        mostrarResultados(quizGame);
                    } else {
                        System.out.println("No se pudo iniciar la partida.");
                    }

                    break;

                case 2:
                    System.out.print("Ingrese id de la pregunta a eliminar: ");
                    int idPregunta = sc.nextInt();

                    if (quizGame.eliminarPreguntaPorId(idPregunta)) {
                        System.out.println("Pregunta eliminada correctamente.");
                    } else {
                        System.out.println("No existe una pregunta con ese id.");
                    }

                    break;

                case 0:
                    System.out.println("Fin del programa.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    private static void cargarJugadoresDesdeCSV(QuizGame quizGame, String archivo) {

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea = br.readLine(); // saltea encabezado

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                int idJugador = Integer.parseInt(datos[0]);
                String nombre = datos[1];

                Jugador jugador = new Jugador(idJugador, nombre);
                quizGame.registrarJugador(jugador);
            }

            System.out.println("Jugadores cargados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al leer jugadores.csv");
        }
    }

    private static void cargarPreguntasDesdeCSV(QuizGame quizGame, String archivo) {

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea = br.readLine(); // saltea encabezado

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                int idPregunta = Integer.parseInt(datos[0]);
                String enunciado = datos[1];

                String[] opciones = new String[4];
                opciones[0] = datos[2];
                opciones[1] = datos[3];
                opciones[2] = datos[4];
                opciones[3] = datos[5];

                int idRespuestaCorrecta = Integer.parseInt(datos[6]);
                String categoria = datos[7];

                Pregunta pregunta = new Pregunta(
                        idPregunta,
                        enunciado,
                        opciones,
                        idRespuestaCorrecta,
                        categoria
                );

                quizGame.registrarPregunta(pregunta);
            }

            System.out.println("Preguntas cargadas correctamente.");

        } catch (IOException e) {
            System.out.println("Error al leer preguntas.csv");
        }
    }

    private static void jugarPartida(QuizGame quizGame, Scanner sc) {

        int turno = 1;

        while (quizGame.hayPartidaEnCurso()) {

            Jugador jugadorActual = quizGame.getTurnosJugadores().frente();
            Pregunta preguntaActual = quizGame.getPreguntasPendientes().frente();

            int respuestaElegida;

            do {

                System.out.println("\n===== TURNO " + turno + " =====");
                System.out.println("Jugador: " + jugadorActual.getNombre());
                System.out.println("Turno: " + turno);

                System.out.println("\nPregunta:");
                System.out.println(preguntaActual.getEnunciado());

                System.out.println("\nOpciones de respuesta:");

                String[] opciones = preguntaActual.getOpciones();

                for (int i = 0; i < opciones.length; i++) {
                    System.out.println((i + 1) + ". " + opciones[i]);
                }

                System.out.print("\nSeleccione respuesta: ");
                respuestaElegida = sc.nextInt();

                if (respuestaElegida < 1 || respuestaElegida > 4) {
                    System.out.println("\nOpción inválida. Debe ingresar un número entre 1 y 4.\n");
                }

            } while (respuestaElegida < 1 || respuestaElegida > 4);

            String resultado = quizGame.jugarTurno(respuestaElegida);

            System.out.println(resultado);

            turno++;
        }
    }

    private static void mostrarResultados(QuizGame quizGame) {

        System.out.println("\n===== FIN DE LA PARTIDA =====");
        System.out.println("Puntajes:");

        TDALista<String> puntajes = quizGame.resumenPuntajes();

        for (int i = 0; i < puntajes.tamaño(); i++) {
            System.out.println(puntajes.obtener(i));
        }

        System.out.println("\nGanador/es:");

        TDALista<Jugador> ganadores = quizGame.obtenerGanadores();

        for (int i = 0; i < ganadores.tamaño(); i++) {
            System.out.println(ganadores.obtener(i).getNombre());
        }
    }
}

