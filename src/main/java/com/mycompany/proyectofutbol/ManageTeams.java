package com.mycompany.proyectofutbol;

import javax.swing.*;

public class ManageTeams {

    public ManageTeams(){}

    void agregarEquipo(Equipo[] equipos, Jugador[] jugadores) {
        // Verificar si hay espacio para agregar un equipo
        int indexEquipo = -1;
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i] == null) {
                indexEquipo = i;
                break;
            }
        }
        if (indexEquipo == -1) {
            JOptionPane.showMessageDialog(null, "No hay espacio disponible para agregar más equipos.");
            return;
        }

        // Solicitar el nombre del nuevo equipo
        String nombreEquipo = JOptionPane.showInputDialog("Ingrese el nombre del nuevo equipo:");
        if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido para el equipo.");
            return;
        }

        // Crear el nuevo equipo
        Equipo nuevoEquipo = new Equipo(nombreEquipo);
        boolean porteroAsignado = false; // Flag para verificar si ya se ha asignado un portero

        // Asignar jugadores al equipo
        while (nuevoEquipo.getContadorJugadores() < 7) {
            // Crear un menú de jugadores disponibles (sin equipo)
            String[] opcionesJugadores = new String[jugadores.length + 1];
            int jugadorDisponible = 0;

            for (int i = 0; i < jugadores.length; i++) {
                if (jugadores[i] != null && (jugadores[i].getEquipo() == null || jugadores[i].getEquipo().isEmpty())) {
                    // Mostrar nombre, ID y posición en las opciones del menú
                    opcionesJugadores[jugadorDisponible++] = jugadores[i].getNombre() + " (ID: " + jugadores[i].getId() + ", Posición: " + jugadores[i].getPosicion() + ")";
                }
            }

            // Agregar opción "Finalizar"
            opcionesJugadores[jugadorDisponible] = "Finalizar";

            if (jugadorDisponible == 0) {
                JOptionPane.showMessageDialog(null, "No hay más jugadores disponibles para agregar.");
                break;
            }

            // Mostrar menú de selección
            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione un jugador para agregar al equipo o 'Finalizar':",
                    "Agregar Jugadores",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcionesJugadores,
                    opcionesJugadores[0]
            );

            if (seleccion == null || seleccion.equals("Finalizar")) {
                break;
            }

            // Buscar el jugador seleccionado y agregarlo al equipo
            for (int i = 0; i < jugadores.length; i++) {
                if (jugadores[i] != null && seleccion.contains("ID: " + jugadores[i].getId())) {
                    Jugador jugadorSeleccionado = jugadores[i];
                    ManagePlayers managePlayers = new ManagePlayers();
                    managePlayers.agregar_jugador_a_equipo(jugadorSeleccionado, nuevoEquipo);
                    break;
                }
            }
        }

        // Registrar el equipo en el sistema
        equipos[indexEquipo] = nuevoEquipo;

        // Mostrar mensaje de confirmación con la información del equipo
        nuevoEquipo.mostrarInformacion();
    }

    void mostrar_equipos(Equipo[] equipos) {
        for (Equipo equipo : equipos) {
            // Verificar si el equipo no es nulo
            if (equipo != null) {
                equipo.mostrarInformacion();
            }
        }
    }

}
