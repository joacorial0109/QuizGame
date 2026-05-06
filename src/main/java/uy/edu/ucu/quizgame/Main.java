package uy.edu.ucu.quizgame;

import uy.edu.ucu.quizgame.dominio.Jugador;
import uy.edu.ucu.quizgame.dominio.Pregunta;
import uy.edu.ucu.quizgame.dominio.QuizGame;
import uy.edu.ucu.quizgame.tda.TDALista;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static QuizGame quizGame = new QuizGame();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Elegí una opción: ");

            switch (opcion) {
                case 1:
                    registrarJugador();
                    break;
                case 2:
                    registrarPregunta();
                    break;
                case 3:
                    eliminarPregunta();
                    break;
                case 4:
                    iniciarPartida();
                    break;
                case 5:
                    jugarTurno();
                    break;
                case 6:
                    mostrarPuntajes();
                    break;
                case 7:
                    mostrarGanadores();
                    break;
                case 8:
                    deshacerUltimaRespuesta();
                    break;
                case 9:
                    cargarDatosDePrueba();
                    break;
                case 0:
                    System.out.println("Saliendo del QuizGame...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }

            System.out.println();

        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("===== QUIZGAME =====");
        System.out.println("1. Registrar jugador");
        System.out.println("2. Registrar pregunta");
        System.out.println("3. Eliminar pregunta");
        System.out.println("4. Iniciar partida");
        System.out.println("5. Jugar turno");
        System.out.println("6. Ver puntajes");
        System.out.println("7. Ver ganador/es");
        System.out.println("8. Deshacer última respuesta");
        System.out.println("9. Cargar datos de prueba");
        System.out.println("0. Salir");
    }

    private static void registrarJugador() {
        int id = leerEntero("ID del jugador: ");
        System.out.print("Nombre del jugador: ");
        String nombre = scanner.nextLine();

        Jugador jugador = new Jugador(id, nombre);

        if (quizGame.registrarJugador(jugador)) {
            System.out.println("Jugador registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar. Puede que el jugador ya exista.");
        }
    }

    private static void registrarPregunta() {
        int id = leerEntero("ID de la pregunta: ");

        System.out.print("Enunciado: ");
        String enunciado = scanner.nextLine();

        String[] opciones = new String[3];

        System.out.print("Opción 1: ");
        opciones[0] = scanner.nextLine();

        System.out.print("Opción 2: ");
        opciones[1] = scanner.nextLine();

        System.out.print("Opción 3: ");
        opciones[2] = scanner.nextLine();

        int respuestaCorrecta = leerEntero("Número de opción correcta (1, 2 o 3): ");

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        Pregunta pregunta = new Pregunta(id, enunciado, opciones, respuestaCorrecta, categoria);

        if (quizGame.registrarPregunta(pregunta)) {
            System.out.println("Pregunta registrada correctamente.");
        } else {
            System.out.println("No se pudo registrar. Puede que la pregunta ya exista.");
        }
    }

    private static void eliminarPregunta() {
        int id = leerEntero("ID de la pregunta a eliminar: ");

        if (quizGame.eliminarPreguntaPorId(id)) {
            System.out.println("Pregunta eliminada correctamente.");
        } else {
            System.out.println("No se encontró una pregunta con ese ID.");
        }
    }

    private static void iniciarPartida() {
        if (quizGame.iniciarPartida()) {
            System.out.println("Partida iniciada correctamente.");
        } else {
            System.out.println("No se pudo iniciar. Tiene que haber al menos un jugador y una pregunta.");
        }
    }

    private static void jugarTurno() {
        if (!quizGame.hayPartidaEnCurso()) {
            System.out.println("No hay una partida en curso. Primero iniciá la partida.");
            return;
        }

        if (quizGame.getPreguntasPendientes().esVacio()) {
            System.out.println("No quedan preguntas pendientes.");
            return;
        }

        if (quizGame.getTurnosJugadores().esVacio()) {
            System.out.println("No hay jugadores en la cola de turnos.");
            return;
        }

        Jugador jugadorActual = quizGame.getTurnosJugadores().frente();
        Pregunta preguntaActual = quizGame.getPreguntasPendientes().frente();

        System.out.println("Turno de: " + jugadorActual.getNombre());
        System.out.println("Pregunta: " + preguntaActual.getEnunciado());

        String[] opciones = preguntaActual.getOpciones();

        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }

        int respuesta = leerEntero("Respuesta: ");

        String resultado = quizGame.jugarTurno(respuesta);

        if (resultado != null) {
            System.out.println(resultado);
        } else {
            System.out.println("No se pudo jugar el turno.");
        }
    }

    private static void mostrarPuntajes() {
        TDALista<String> puntajes = quizGame.resumenPuntajes();

        if (puntajes.esVacio()) {
            System.out.println("No hay jugadores registrados.");
            return;
        }

        System.out.println("===== PUNTAJES =====");

        for (int i = 0; i < puntajes.tamaño(); i++) {
            System.out.println(puntajes.obtener(i));
        }
    }

    private static void mostrarGanadores() {
        TDALista<Jugador> ganadores = quizGame.obtenerGanadores();

        if (ganadores.esVacio()) {
            System.out.println("No hay jugadores registrados.");
            return;
        }

        System.out.println("===== GANADOR/ES =====");

        for (int i = 0; i < ganadores.tamaño(); i++) {
            Jugador ganador = ganadores.obtener(i);
            System.out.println(
                    ganador.getNombre()
                            + " - "
                            + ganador.getPuntajeActual()
                            + " puntos"
            );
        }
    }

    private static void deshacerUltimaRespuesta() {
        if (quizGame.deshacerUltimaRespuesta()) {
            System.out.println("Última respuesta deshecha correctamente.");
        } else {
            System.out.println("No hay respuestas para deshacer.");
        }
    }

    private static void cargarDatosDePrueba() {
        quizGame.registrarJugador(new Jugador(1, "Joaco"));
        quizGame.registrarJugador(new Jugador(2, "Ian"));
        quizGame.registrarJugador(new Jugador(3, "Alfonso"));

        quizGame.registrarPregunta(new Pregunta(
                1,
                "¿Cuál es la capital de Uruguay?",
                new String[]{"Montevideo", "Buenos Aires", "Santiago"},
                1,
                "Geografía"
        ));

        quizGame.registrarPregunta(new Pregunta(
                2,
                "¿Cuánto es 2 + 2?",
                new String[]{"3", "4", "5"},
                2,
                "Matemática"
        ));

        quizGame.registrarPregunta(new Pregunta(
                3,
                "¿Qué estructura se usa para manejar turnos?",
                new String[]{"Pila", "Cola", "Árbol"},
                2,
                "AED"
        ));

        System.out.println("Datos de prueba cargados.");
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número válido.");
            }
        }
    }
}