package com.mycompany.proyectofutbol;


import javax.swing.*;

public class Equipo {
    private int id;
    private String nombreEquipo;
    private Jugador[] jugadores; // Arreglo fijo de 7 jugadores
    private int contadorJugadores; // Lleva el control de cuántos jugadores hay en el equipo
    private static int idCounter = 100; // ID inicial auto-incrementable para equipos

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

    // Constructor
    public Equipo(String nombreEquipo) {
        this.id = idCounter++;
        this.nombreEquipo = nombreEquipo;
        this.jugadores = new Jugador[7]; // Arreglo estático
        this.contadorJugadores = 0;
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

    public int getContadorJugadores() {
        return contadorJugadores;
    }

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

        // Imprimir la información en la consola
        System.out.println(info.toString());
    }
}
