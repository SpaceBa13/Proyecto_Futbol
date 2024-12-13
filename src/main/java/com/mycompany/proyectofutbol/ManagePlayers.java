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

}
