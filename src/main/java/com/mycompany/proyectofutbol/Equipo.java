package com.mycompany.proyectofutbol;


public class Equipo {

    // Atributos del equipo
    private int id;
    private String nombreEquipo;
    private Jugador[] jugadores; // Arreglo fijo de 7 jugadores
    private int contadorJugadores; // Lleva el control de cuántos jugadores hay en el equipo
    private static int idCounter = 100; // ID inicial auto-incrementable para equipos

    // Atributos adicionales para contadores de partidos y goles
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesAFavor;
    private int golesEnContra;
    private int posesionBalon;

    // Constructor
    public Equipo(String nombreEquipo) {
        this.id = idCounter++;
        this.nombreEquipo = nombreEquipo;
        this.jugadores = new Jugador[7]; // Arreglo estático
        this.contadorJugadores = 0;

        // Inicialización de contadores
        this.partidosJugados = 0;
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
        this.posesionBalon = 0;
    }

    // Getters y Setters
    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public String get_nombre_equipo() {
        return nombreEquipo;
    }

    public void set_nombre_equipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public Jugador[] get_jugadores() {
        return jugadores;
    }

    public void set_jugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public int get_contador_jugadores() {
        return contadorJugadores;
    }

    public void set_contador_jugadores(int contadorJugadores) {
        this.contadorJugadores = contadorJugadores;
    }

    public int get_partidos_jugados() {
        return partidosJugados;
    }

    public void set_partidos_jugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int get_partidos_ganados() {
        return partidosGanados;
    }

    public void set_partidos_ganados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int get_partidos_empatados() {
        return partidosEmpatados;
    }

    public void set_partidos_empatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int get_partidos_perdidos() {
        return partidosPerdidos;
    }

    public void set_partidos_perdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int get_goles_a_favor() {
        return golesAFavor;
    }

    public void set_goles_a_favor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public int get_goles_en_contra() {
        return golesEnContra;
    }

    public void set_goles_en_contra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public int get_posesion_balon() {
        return posesionBalon;
    }

    public void set_posesion_balon(int posesionBalon) {
        this.posesionBalon = posesionBalon;
    }

    // Método para agregar jugador
    public boolean agregar_jugador(Jugador jugador) {
        if (contadorJugadores < 7) {
            jugadores[contadorJugadores] = jugador;
            contadorJugadores++;

            // Asignar estado según la posición en el arreglo
            if (contadorJugadores <= 5) {
                jugador.set_estado(Estado.TITULAR);
            } else {
                jugador.set_estado(Estado.SUPLENTE);
            }

            // Actualizar equipo del jugador
            jugador.set_equipo(nombreEquipo);
            return true;
        }
        return false; // Equipo completo
    }

    // Método para mostrar la información del equipo
    public void mostrar_informacion() {
        StringBuilder info = new StringBuilder("Equipo: " + nombreEquipo + " (ID: " + id + ")\n");
        info.append("Jugadores:\n");

        for (int i = 0; i < contadorJugadores; i++) {
            Jugador jugador = jugadores[i];
            if (jugador != null) {
                info.append("- ").append(jugador.get_nombre())
                        .append(" (").append(jugador.get_posicion()).append(", ")
                        .append(jugador.get_estado()).append(")\n");
            }
        }

        // Agregar los datos adicionales del equipo
        info.append("\nEstadísticas del equipo:\n");
        info.append("Partidos Jugados: ").append(partidosJugados).append("\n");
        info.append("Partidos Ganados: ").append(partidosGanados).append("\n");
        info.append("Partidos Empatados: ").append(partidosEmpatados).append("\n");
        info.append("Partidos Perdidos: ").append(partidosPerdidos).append("\n");
        info.append("Goles a Favor: ").append(golesAFavor).append("\n");
        info.append("Goles en Contra: ").append(golesEnContra).append("\n");
        info.append("Posesión del Balón: ").append(posesionBalon).append("%\n");

        // Imprimir la información en la consola
        System.out.println(info.toString());
    }
}
