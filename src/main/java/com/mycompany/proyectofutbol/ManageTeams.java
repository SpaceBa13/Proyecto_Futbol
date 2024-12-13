package com.mycompany.proyectofutbol;

import javax.swing.*;

import javax.swing.JOptionPane;

public class ManageTeams {

    public ManageTeams() {}

    // ----- Funciones de Agregar Jugadores y Equipos -----

    // Verifica si hay espacio para agregar un nuevo equipo
    private int encontrar_espacio_en_equipos(Equipo[] equipos) {
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i] == null) {
                return i;
            }
        }
        return -1;
    }

    // Agregar un nuevo equipo
    public void agregar_equipo(Equipo[] equipos, Jugador[] jugadores) {
        int indexEquipo = encontrar_espacio_en_equipos(equipos);
        if (indexEquipo == -1) {
            JOptionPane.showMessageDialog(null, "No hay espacio disponible para agregar más equipos.");
            return;
        }

        String nombreEquipo = JOptionPane.showInputDialog("Ingrese el nombre del nuevo equipo:");
        if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido para el equipo.");
            return;
        }

        Equipo nuevoEquipo = new Equipo(nombreEquipo);
        asignar_jugadores_a_equipo(jugadores, nuevoEquipo);

        equipos[indexEquipo] = nuevoEquipo;
        nuevoEquipo.mostrar_informacion();
    }

    // Asignar jugadores al nuevo equipo
    private void asignar_jugadores_a_equipo(Jugador[] jugadores, Equipo equipo) {
        while (equipo.get_contador_jugadores() < 7) {
            String selecion = seleccionar_jugador(jugadores);
            if (selecion != null) {
                agregar_jugador_a_equipo(jugadores, equipo, selecion);
            } else {
                break;
            }
        }
    }

    // Mostrar todos los equipos
    public void mostrar_equipos(Equipo[] equipos) {
        for (Equipo equipo : equipos) {
            if (equipo != null) {
                equipo.mostrar_informacion();
            }
        }
    }

    // ----- Funciones de Agregar Jugadores a un Equipo -----

    // Agregar un jugador a un equipo por su ID
    public void agregar_jugador_a_equipo_por_id(Jugador[] jugadores, Equipo[] equipos, int id_del_equipo) {
        boolean equipoEncontrado = false;
        boolean equipoLleno = false;

        for (Equipo equipo : equipos) {
            if (equipo != null && equipo.get_id() == id_del_equipo) {
                equipoEncontrado = true;
                if (equipo.get_contador_jugadores() >= 7) {
                    equipoLleno = true;
                    break;
                }

                String selecion = seleccionar_jugador(jugadores);
                if (selecion != null) {
                    agregar_jugador_a_equipo(jugadores, equipo, selecion);
                } else {
                    break;
                }
            }
        }

        if (!equipoEncontrado) {
            JOptionPane.showMessageDialog(null, "El equipo con ID " + id_del_equipo + " no existe.");
        }

        if (equipoLleno) {
            JOptionPane.showMessageDialog(null, "El equipo ya está lleno.");
        }
    }

    // ----- Funciones de Eliminar Jugadores de un Equipo -----

    // Eliminar un jugador de un equipo por su ID
    public void eliminar_jugador_a_equipo_por_id(Jugador[] jugadores, Equipo[] equipos, int id_del_equipo) {
        boolean equipoEncontrado = false;

        for (Equipo equipo : equipos) {
            if (equipo != null && equipo.get_id() == id_del_equipo) {
                equipoEncontrado = true;

                String selecion = seleccionar_jugador_del_equipo(equipo.get_jugadores());
                if (selecion != null) {
                    eliminar_jugador_a_equipo(jugadores, equipo, selecion);
                } else {
                    break;
                }
            }
        }

        if (!equipoEncontrado) {
            JOptionPane.showMessageDialog(null, "El equipo con ID " + id_del_equipo + " no existe.");
        }
    }

    // Eliminar un jugador del equipo
    private void eliminar_jugador_a_equipo(Jugador[] jugadores, Equipo equipo, String selecion) {
        for (int i = 0; i < equipo.get_contador_jugadores(); i++) {
            Jugador jugador = equipo.get_jugadores()[i];

            if (jugador != null && selecion.contains("ID: " + jugador.get_id())) {
                jugador.set_estado(Estado.LIBRE);
                jugador.set_goles_anotados(0);
                jugador.set_acciones(0);
                jugador.set_equipo("");

                equipo.get_jugadores()[i] = null;
                equipo.set_contador_jugadores(equipo.get_contador_jugadores() - 1);

                JOptionPane.showMessageDialog(null, "El jugador " + jugador.get_nombre() + " ha sido eliminado del equipo y ahora está libre.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Jugador no encontrado en el equipo.");
    }

    // ----- Funciones de Selección de Jugadores -----

    // Seleccionar un jugador (sin equipo)
    private String seleccionar_jugador(Jugador[] jugadores) {
        return seleccionar_jugador_general(jugadores, false);
    }

    // Seleccionar un jugador de un equipo
    private String seleccionar_jugador_del_equipo(Jugador[] jugadores) {
        return seleccionar_jugador_general(jugadores, true);
    }

    // Función general para seleccionar un jugador
    private String seleccionar_jugador_general(Jugador[] jugadores, boolean desdeEquipo) {
        String[] opcionesJugadores = new String[jugadores.length + 1];
        int jugadorDisponible = 0;

        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null && (desdeEquipo || jugadores[i].get_equipo().isEmpty())) {
                opcionesJugadores[jugadorDisponible++] = jugadores[i].get_nombre() + " (ID: " + jugadores[i].get_id() + ", Posición: " + jugadores[i].get_posicion() + ")";
            }
        }

        if (jugadorDisponible == 0) {
            JOptionPane.showMessageDialog(null, desdeEquipo ? "No hay jugadores disponibles para eliminar." : "No hay jugadores disponibles para agregar.");
            return null;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un jugador:",
                desdeEquipo ? "Eliminar Jugador" : "Agregar Jugador",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesJugadores,
                opcionesJugadores[0]
        );

        return seleccion != null && !seleccion.equals("") ? seleccion : null;
    }

    // ----- Funciones de Agregar Jugador a un Equipo -----

    // Agregar un jugador a un equipo
    private void agregar_jugador_a_equipo(Jugador[] jugadores, Equipo equipo, String seleccion) {
        // Buscar al jugador seleccionado por ID
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null && seleccion.contains("ID: " + jugadores[i].get_id())) {
                Jugador jugadorSeleccionado = jugadores[i];

                // Verificar si el jugador es un portero y si ya existe un portero en el equipo
                boolean porteroAsignado = false;
                for (Jugador j : equipo.get_jugadores()) {
                    if (j != null && j.get_posicion() == Posicion.PORTERO) {
                        porteroAsignado = true;
                        break;
                    }
                }

                // Si el jugador es un portero y ya existe uno, mostrar mensaje y no agregar
                if (jugadorSeleccionado.get_posicion() == Posicion.PORTERO && porteroAsignado) {
                    JOptionPane.showMessageDialog(null, "No se puede agregar otro portero al equipo.");
                    return;
                }

                // Asignar el estado del jugador según las posiciones disponibles en el equipo
                int posicionesDisponibles = 7 - equipo.get_contador_jugadores(); // Calcular posiciones disponibles
                if (posicionesDisponibles == 2) {
                    jugadorSeleccionado.set_estado(Estado.SUPLENTE); // Asignar como suplente si hay 2 posiciones disponibles
                } else {
                    jugadorSeleccionado.set_estado(Estado.TITULAR); // Asignar como titular en cualquier otro caso
                }

                // Intentar agregar el jugador al equipo
                if (equipo.agregar_jugador(jugadorSeleccionado)) {
                    JOptionPane.showMessageDialog(null, "Jugador agregado exitosamente al equipo: " + equipo.get_nombre_equipo() + ". Estado: " + jugadorSeleccionado.get_estado());
                } else {
                    JOptionPane.showMessageDialog(null, "El equipo " + equipo.get_nombre_equipo() + " ya está completo.");
                }
                break;
            }
        }
    }
}

