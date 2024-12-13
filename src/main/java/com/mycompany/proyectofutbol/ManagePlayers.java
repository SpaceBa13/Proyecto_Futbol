package com.mycompany.proyectofutbol;

import javax.swing.*;

public class ManagePlayers {

    ManagePlayers(){}

    void agregar_jugadores(Jugador jugador, Jugador[] jugadores){
        for(int i = 0; i < jugadores.length; i++) {
            if(jugadores[i] == null){
                jugadores[i] = new Jugador();
                jugadores[i].setAcciones(jugador.getAcciones());
                jugadores[i].setEquipo(jugador.getEquipo());
                jugadores[i].setPosicion(jugador.getPosicion());
                jugadores[i].setNombre(jugador.getNombre());
                jugadores[i].setId(jugador.getId());
                jugadores[i].setGolesAnotados(jugador.getGolesAnotados());
                JOptionPane.showMessageDialog(null, "SE AGREGO UN JUGADOR");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "NO SE AGREGO NINGUN JUGADOR");
    }

    void imprimir_jugadores(Jugador[] jugadores){
        for(int i = 0; i < jugadores.length; i++){
            if (jugadores[i] != null) {
                System.out.print("============================================================");
                System.out.print("ID: " + jugadores[i].getId());
                System.out.print("||");
                System.out.print("Nombre: " + jugadores[i].getNombre());
                System.out.print("||");
                System.out.print("Posicion: " + jugadores[i].getPosicion());
                System.out.print("||");
                System.out.print("Eqipo: " + jugadores[i].getEquipo());
                System.out.print("||");
                System.out.print("ESTADO: " + jugadores[i].getEstado());
                System.out.print("||");
                System.out.print("Goles: " + jugadores[i].getGolesAnotados());
                System.out.println("============================================================\n");
            }
        }

    }

    void eliminar_jugador(Jugador[] jugadores, int id) {
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null && jugadores[i].getId() == id) {
                // Verifica si el jugador está asignado a un equipo
                if (jugadores[i].getEquipo() != null && !jugadores[i].getEquipo().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "No se puede eliminar al jugador con ID " + id + " porque pertenece al equipo: " + jugadores[i].getEquipo());
                    return;
                }

                // Mostrar información del jugador y solicitar confirmación para eliminarlo
                int confirmacion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Está seguro que desea eliminar al jugador con ID " + id + "?\n" +
                                "Nombre: " + jugadores[i].getNombre() + "\n" +
                                "Posición: " + jugadores[i].getPosicion(),
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Eliminar el jugador desplazando los elementos
                    for (int j = i; j < jugadores.length - 1; j++) {
                        jugadores[j] = jugadores[j + 1];
                    }
                    jugadores[jugadores.length - 1] = null; // Limpia la última posición

                    JOptionPane.showMessageDialog(null, "Jugador con ID " + id + " eliminado exitosamente.");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
                    return;
                }
            }
        }

        // Si el jugador no fue encontrado
        JOptionPane.showMessageDialog(null, "Jugador con ID " + id + " no encontrado.");
    }


}
