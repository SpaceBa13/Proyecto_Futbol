package com.mycompany.proyectofutbol;

import javax.swing.JOptionPane;

public class Manejador_de_Jugadores {

    public Manejador_de_Jugadores() {}

    // ----- Funciones de Impresión -----

    // Imprimir información de los jugadores
    void imprimir_jugadores(Jugador[] jugadores) {
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null) {
                StringBuilder info = new StringBuilder("============================================================\n");
                info.append("ID: ").append(jugadores[i].get_id()).append(" || ");
                info.append("Nombre: ").append(jugadores[i].get_nombre()).append(" || ");
                info.append("Posición: ").append(jugadores[i].get_posicion()).append(" || ");
                info.append("Equipo: ").append(jugadores[i].get_equipo()).append(" || ");
                info.append("Estado: ").append(jugadores[i].get_estado()).append(" || ");
                info.append("Goles: ").append(jugadores[i].get_goles_anotados()).append("\n");
                info.append("============================================================\n");

                // Imprimir la información en la consola
                System.out.print(info.toString());
            }
        }
    }

    // ----- Funciones de Agregar Jugadores -----

    // Agregar jugador a la lista de jugadores
    void agregar_jugadores(Jugador jugador, Jugador[] jugadores) {
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] == null) {
                jugadores[i] = new Jugador();
                jugadores[i].set_acciones(jugador.get_acciones());
                jugadores[i].set_equipo(jugador.get_equipo());
                jugadores[i].set_posicion(jugador.get_posicion());
                jugadores[i].set_nombre(jugador.get_nombre());
                jugadores[i].set_id(jugador.get_id());
                jugadores[i].set_goles_anotados(jugador.get_goles_anotados());
                JOptionPane.showMessageDialog(null, "SE AGREGO UN JUGADOR");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "NO SE AGREGO NINGUN JUGADOR");
    }

    // Agregar un jugador a un equipo
    public void agregar_jugador_a_equipo(Jugador jugador, Equipo equipo) {
        boolean porteroAsignado = false;
        for (Jugador j : equipo.get_jugadores()) {
            if (j != null && j.get_posicion() == Posicion.PORTERO) {
                porteroAsignado = true;
                break;
            }
        }

        if (jugador.get_posicion() == Posicion.PORTERO && porteroAsignado) {
            JOptionPane.showMessageDialog(null, "No se puede agregar otro portero al equipo.");
            return;
        }

        // Asignar estado según las posiciones disponibles
        int posicionesDisponibles = 7 - equipo.get_contador_jugadores();
        if (posicionesDisponibles == 2) {
            jugador.set_estado(Estado.SUPLENTE);
        } else {
            jugador.set_estado(Estado.TITULAR);
        }

        if (equipo.agregar_jugador(jugador)) {
            JOptionPane.showMessageDialog(null, "Jugador agregado exitosamente al equipo: " + equipo.get_nombre_equipo() + ". Estado: " + jugador.get_estado());
        } else {
            JOptionPane.showMessageDialog(null, "El equipo " + equipo.get_nombre_equipo() + " ya está completo.");
        }
    }

    // Agregar un jugador a equipos disponibles
    public void agregar_jugador_a_equipos_disponibles(Jugador jugador, Equipo[] equipos) {
        String[] opcionesEquipos = new String[equipos.length + 1];
        int index = 0;

        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i] != null && equipos[i].get_contador_jugadores() < 7) {
                opcionesEquipos[index++] = equipos[i].get_nombre_equipo() + " (ID: " + equipos[i].get_id() + ")";
            }
        }

        if (index == 0) {
            opcionesEquipos[0] = "Ningún equipo";
        } else {
            opcionesEquipos[index] = "Ningún equipo";
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el equipo al que desea agregar al jugador o 'Ningún equipo':",
                "Agregar Jugador al Equipo",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesEquipos,
                opcionesEquipos[0]
        );

        if (seleccion == null || seleccion.equals("Ningún equipo")) {
            jugador.set_equipo("");
            jugador.set_estado(Estado.LIBRE);
            JOptionPane.showMessageDialog(null, "El jugador no se unió a ningún equipo. Su estado es Libre.");
            return;
        }

        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i] != null && seleccion.contains(equipos[i].get_nombre_equipo())) {
                agregar_jugador_a_equipo(jugador, equipos[i]);
                return;
            }
        }
    }

    // ----- Funciones de Eliminar Jugadores -----

    // Eliminar jugador de la lista
    void eliminar_jugador(Jugador[] jugadores, int id) {
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null && jugadores[i].get_id() == id) {
                if (jugadores[i].get_equipo() != null && !jugadores[i].get_equipo().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "No se puede eliminar al jugador con ID " + id + " porque pertenece al equipo: " + jugadores[i].get_equipo());
                    return;
                }

                int confirmacion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Está seguro que desea eliminar al jugador con ID " + id + "?\n" +
                                "Nombre: " + jugadores[i].get_nombre() + "\n" +
                                "Posición: " + jugadores[i].get_posicion(),
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmacion == JOptionPane.YES_OPTION) {
                    for (int j = i; j < jugadores.length - 1; j++) {
                        jugadores[j] = jugadores[j + 1];
                    }
                    jugadores[jugadores.length - 1] = null;
                    JOptionPane.showMessageDialog(null, "Jugador con ID " + id + " eliminado exitosamente.");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
                    return;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Jugador con ID " + id + " no encontrado.");
    }

    // ----- Funciones de Edición de Jugador -----

    // Editar datos de un jugador
    public void editar_jugador(Jugador[] jugadores, int idJugador) {
        Jugador jugadorSeleccionado = null;
        for (Jugador jugador : jugadores) {
            if (jugador != null && jugador.get_id() == idJugador) {
                jugadorSeleccionado = jugador;
                break;
            }
        }

        if (jugadorSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Jugador no encontrado.");
            return;
        }

        String[] opciones = {"Editar Nombre", "Editar Posición"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(
                null,
                "Seleccione qué desea editar:",
                "Editar Jugador",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcionSeleccionada == -1) return; // Si el usuario cancela la selección, salir

        if (opcionSeleccionada == 0) {
            String nombre_edit = JOptionPane.showInputDialog("Ingrese el nuevo nombre del jugador:");
            if (nombre_edit != null && !nombre_edit.trim().isEmpty()) {
                jugadorSeleccionado.set_nombre(nombre_edit);
                JOptionPane.showMessageDialog(null, "Nombre actualizado a: " + jugadorSeleccionado.get_nombre());
            } else {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                return;
            }
        } else if (opcionSeleccionada == 1) {
            Posicion posicion_edit = Botones_Jugadores.seleccioneLaPosicion();
            if (posicion_edit != null) {
                jugadorSeleccionado.set_posicion(posicion_edit);
                JOptionPane.showMessageDialog(null, "Posición actualizada a: " + jugadorSeleccionado.get_posicion());
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una posición válida.");
                return;
            }
        }

        StringBuilder mensajeConfirmacion = new StringBuilder("Jugador editado:\n");
        mensajeConfirmacion.append("ID: ").append(jugadorSeleccionado.get_id()).append("\n");
        mensajeConfirmacion.append("Nombre: ").append(jugadorSeleccionado.get_nombre()).append("\n");
        mensajeConfirmacion.append("Posición: ").append(jugadorSeleccionado.get_posicion()).append("\n");
        mensajeConfirmacion.append("Estado: ").append(jugadorSeleccionado.get_estado()).append("\n");

        JOptionPane.showMessageDialog(null, mensajeConfirmacion.toString());
    }
}
