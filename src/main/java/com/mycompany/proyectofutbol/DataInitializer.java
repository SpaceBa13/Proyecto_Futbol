package com.mycompany.proyectofutbol;

import java.util.Random;


public class DataInitializer {
    public static void inicializarDatos(Equipo[] equipos, Jugador[] jugadores) {
        // Generar nombres de equipos únicos
        String[] nombresEquipos = {"Los Dragones", "Los Lobos", "Los Halcones"};
        int jugadoresGenerados = 0;
        Random random = new Random();

        // Generar 3 equipos y asignar jugadores
        for (int i = 0; i < 3; i++) {
            // Crear un nuevo equipo
            Equipo equipo = new Equipo(nombresEquipos[i]);
            equipos[i] = equipo;

            // Asignar 7 jugadores al equipo (1 portero titular, 5 titulares, 2 suplentes)
            boolean porteroAsignado = false;

            // Asignar primero el portero titular
            Jugador portero = new Jugador(
                    "Portero " + (jugadoresGenerados + 1),
                    equipo.getNombreEquipo(),
                    0,
                    0,
                    Posicion.PORTERO
            );
            portero.setEstado(Estado.TITULAR); // El portero es siempre titular
            equipo.agregarJugador(portero);
            jugadores[jugadoresGenerados++] = portero;
            porteroAsignado = true;

            // Asignar los jugadores restantes (5 titulares, 2 suplentes)
            for (int j = 1; j < 7; j++) {
                Posicion posicion = generarPosicion(random, porteroAsignado);

                // Si ya se asignó un portero, asignar otras posiciones
                if (posicion == Posicion.PORTERO) {
                    porteroAsignado = true;
                }

                String nombreJugador = "Jugador " + (jugadoresGenerados + 1);
                Estado estado = (j < 5) ? Estado.TITULAR : Estado.SUPLENTE; // 5 titulares y 2 suplentes

                // Crear el jugador y asignarle estado según su posición
                Jugador jugador = new Jugador(
                        nombreJugador,
                        equipo.getNombreEquipo(),
                        0,
                        0,
                        posicion
                );
                jugador.setEstado(estado); // Asignar estado a titular o suplente
                equipo.agregarJugador(jugador);
                jugadores[jugadoresGenerados++] = jugador;
            }
        }

        // Generar 9 jugadores sin equipo
        for (int i = jugadoresGenerados; i < jugadoresGenerados + 9; i++) {
            // Los jugadores sin equipo pueden ser porteros o cualquier otra posición
            Posicion posicion = generarPosicion(random, false); // Permitir portero si es sin equipo
            String nombreJugador = "Jugador " + (i + 1);

            Jugador jugador = new Jugador(
                    nombreJugador,
                    "", // Sin equipo
                    0,
                    0,
                    posicion
            );

            jugador.setEstado(Estado.LIBRE); // Estado Libre para jugadores sin equipo
            jugadores[i] = jugador;
        }
    }

    private static Posicion generarPosicion(Random random, boolean porteroAsignado) {
        // Generar posiciones aleatorias para los jugadores
        // Si ya se asignó un portero, no permitir más porteros para jugadores en equipo
        Posicion[] posiciones;
        if (porteroAsignado) {
            // Filtrar las posiciones para no incluir PORTERO si ya se asignó uno
            posiciones = new Posicion[]{Posicion.DEFENSA, Posicion.MEDIOCAMPISTA, Posicion.DELANTERO};
        }else{
            posiciones = new Posicion[]{Posicion.DEFENSA, Posicion.MEDIOCAMPISTA, Posicion.DELANTERO, Posicion.PORTERO};
        }
        return posiciones[random.nextInt(posiciones.length)];
    }
}
