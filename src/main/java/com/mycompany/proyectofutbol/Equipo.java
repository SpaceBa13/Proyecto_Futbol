package com.mycompany.proyectofutbol;


import javax.swing.*;

public class Equipo {
    private int id;
    private String nombreEquipo;
    private Jugador[] jugadores; // Arreglo fijo de 7 jugadores
    private int contadorJugadores; // Lleva el control de cuántos jugadores hay en el equipo
    private static int idCounter = 100; // ID inicial auto-incrementable para equipos

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

        JOptionPane.showMessageDialog(null, info.toString());
    }
}
