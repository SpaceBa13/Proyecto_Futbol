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

    public void agregar_jugador_a_equipo(Jugador jugador, Equipo equipo) {
        // Verificar si el jugador es un portero y si ya existe un portero en el equipo
        boolean porteroAsignado = false;
        for (Jugador j : equipo.getJugadores()) {
            if (j != null && j.getPosicion() == Posicion.PORTERO) {
                porteroAsignado = true;
                break;
            }
        }

        // Si el jugador es un portero y ya existe un portero en el equipo, mostrar un mensaje
        if (jugador.getPosicion() == Posicion.PORTERO && porteroAsignado) {
            JOptionPane.showMessageDialog(null, "No se puede agregar otro portero al equipo.");
            return;
        }

        // Asignar el estado del jugador según las posiciones disponibles en el equipo
        int posicionesDisponibles = 7 - equipo.getContadorJugadores(); // Calcular posiciones disponibles
        if (posicionesDisponibles == 2) {
            jugador.setEstado(Estado.SUPLENTE); // Asignar como suplente si hay dos posiciones disponibles
        } else {
            jugador.setEstado(Estado.TITULAR); // Asignar como titular en cualquier otro caso
        }

        // Intentar agregar el jugador al equipo
        if (equipo.agregarJugador(jugador)) {
            JOptionPane.showMessageDialog(null, "Jugador agregado exitosamente al equipo: " + equipo.getNombreEquipo() + ". Estado: " + jugador.getEstado());
        } else {
            JOptionPane.showMessageDialog(null, "El equipo " + equipo.getNombreEquipo() + " ya está completo.");
        }
    }

    public void agregar_jugador_a_equipos_disponibles(Jugador jugador, Equipo[] equipos) {
        // Crear una lista de opciones para mostrar en el menú
        String[] opcionesEquipos = new String[equipos.length + 1];
        int index = 0;

        // Agregar equipos disponibles (aquellos con menos de 7 jugadores)
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i] != null && equipos[i].getContadorJugadores() < 7) {
                opcionesEquipos[index++] = equipos[i].getNombreEquipo() + " (ID: " + equipos[i].getId() + ")";
            }
        }

        // Si no hay equipos disponibles, mostrar solo "Ningún equipo"
        if (index == 0) {
            opcionesEquipos[0] = "Ningún equipo";
        } else {
            // Agregar la opción "Ningún equipo" al final de la lista
            opcionesEquipos[index] = "Ningún equipo";
        }

        // Mostrar el menú de selección de equipo
        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el equipo al que desea agregar al jugador o 'Ningún equipo':",
                "Agregar Jugador al Equipo",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesEquipos,
                opcionesEquipos[0]
        );

        // Si el usuario selecciona "Ningún equipo", no se agrega al jugador a ningún equipo
        if (seleccion == null || seleccion.equals("Ningún equipo")) {
            jugador.setEquipo(""); // Dejar al jugador sin equipo
            jugador.setEstado(Estado.LIBRE); // Asignar el estado "Libre"
            JOptionPane.showMessageDialog(null, "El jugador no se unió a ningún equipo. Su estado es Libre.");
            return;
        }

        // Si el jugador selecciona un equipo, agregarlo a ese equipo
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i] != null && seleccion.contains(equipos[i].getNombreEquipo())) {
               agregar_jugador_a_equipo(jugador, equipos[i]);
               return;
            }
        }
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

    public void editar_Jugador(Jugador[] jugadores, int idJugador) {
        // Buscar al jugador con el ID proporcionado
        Jugador jugadorSeleccionado = null;
        for (Jugador jugador : jugadores) {
            if (jugador != null && jugador.getId() == idJugador) {
                jugadorSeleccionado = jugador;
                break;
            }
        }

        // Verificar si el jugador existe
        if (jugadorSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Jugador no encontrado.");
            return;
        }

        // Presentar opciones para editar el jugador
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

        if (opcionSeleccionada == -1) {
            return; // Si el usuario cancela la selección, salir
        }

        // Editar Nombre
        if (opcionSeleccionada == 0) {
            String nombre_edit = JOptionPane.showInputDialog("Ingrese el nuevo nombre del jugador:");
            if (nombre_edit != null && !nombre_edit.trim().isEmpty()) {
                jugadorSeleccionado.setNombre(nombre_edit);
                JOptionPane.showMessageDialog(null, "Nombre actualizado a: " + jugadorSeleccionado.getNombre());
            } else {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                return;  // Si el nombre es inválido, no se hace nada y se sale del método
            }
        }

        // Editar Posición
        else if (opcionSeleccionada == 1) {
            // Usamos el método Botones_Jugadores.seleccioneLaPosicion para seleccionar la posición
            Posicion posicion_edit = Botones_Jugadores.seleccioneLaPosicion();

            if (posicion_edit != null) {
                jugadorSeleccionado.setPosicion(posicion_edit);
                JOptionPane.showMessageDialog(null, "Posición actualizada a: " + jugadorSeleccionado.getPosicion());
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una posición válida.");
                return;  // Si no se selecciona una posición, no se hace nada y se sale del método
            }
        }

        // Mostrar mensaje de confirmación de la edición
        StringBuilder mensajeConfirmacion = new StringBuilder("Jugador editado:\n");
        mensajeConfirmacion.append("ID: ").append(jugadorSeleccionado.getId()).append("\n");
        mensajeConfirmacion.append("Nombre: ").append(jugadorSeleccionado.getNombre()).append("\n");
        mensajeConfirmacion.append("Posición: ").append(jugadorSeleccionado.getPosicion()).append("\n");
        mensajeConfirmacion.append("Estado: ").append(jugadorSeleccionado.getEstado()).append("\n");

        JOptionPane.showMessageDialog(null, mensajeConfirmacion.toString());
    }


}
