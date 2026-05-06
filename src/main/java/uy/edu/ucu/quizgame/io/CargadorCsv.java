package uy.edu.ucu.quizgame.io;

import uy.edu.ucu.quizgame.dominio.Jugador;
import uy.edu.ucu.quizgame.dominio.Pregunta;
import uy.edu.ucu.quizgame.dominio.QuizGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class CargadorCsv {

    public static int cargarJugadores(Path archivo, QuizGame juego) throws IOException {
        int ok = 0;
        try (BufferedReader br = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                String[] partes = linea.split(";", 2);
                if (partes.length < 2) {
                    continue;
                }
                int id = Integer.parseInt(partes[0].trim());
                String nombre = partes[1].trim();
                if (juego.registrarJugador(new Jugador(id, nombre))) {
                    ok++;
                }
            }
        }
        return ok;
    }

    public static int cargarPreguntas(Path archivo, QuizGame juego) throws IOException {
        int ok = 0;
        try (BufferedReader br = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                String[] partes = linea.split(";", 5);
                if (partes.length < 5) {
                    continue;
                }
                int id = Integer.parseInt(partes[0].trim());
                String enunciado = partes[1].trim();
                String[] opciones = partes[2].split("\\|");
                int correcta = Integer.parseInt(partes[3].trim());
                String categoria = partes[4].trim();
                Pregunta p = new Pregunta(id, enunciado, opciones, correcta, categoria);
                if (juego.registrarPregunta(p)) {
                    ok++;
                }
            }
        }
        return ok;
    }
}
