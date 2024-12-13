package com.mycompany.proyectofutbol;


public class Equipo {
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

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public void setContadorJugadores(int contadorJugadores) {
        this.contadorJugadores = contadorJugadores;
    }

    public int getContadorJugadores() {
        return contadorJugadores;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public int getPosesionBalon() {
        return posesionBalon;
    }

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

    // Método para agregar jugador
    public boolean agregarJugador(Jugador jugador) {
        if (contadorJugadores < 7) {
            jugadores[contadorJugadores] = jugador;
            contadorJugadores++;

            // Asignar estado según la posición en el arreglo
            if (contadorJugadores <= 5) {
                jugador.setEstado(Estado.TITULAR);
            } else {
                jugador.setEstado(Estado.SUPLENTE);
            }

            // Actualizar equipo del jugador
            jugador.setEquipo(nombreEquipo);
            return true;
        }
        return false; // Equipo completo
    }

    // Método para mostrar la información del equipo
    public void mostrarInformacion() {
        StringBuilder info = new StringBuilder("Equipo: " + nombreEquipo + " (ID: " + id + ")\n");
        info.append("Jugadores:\n");

        for (int i = 0; i < contadorJugadores; i++) {
            Jugador jugador = jugadores[i];
            if (jugador != null) {
                info.append("- ").append(jugador.getNombre())
                        .append(" (").append(jugador.getPosicion()).append(", ")
                        .append(jugador.getEstado()).append(")\n");
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