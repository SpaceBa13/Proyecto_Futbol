package com.mycompany.proyectofutbol;

import javax.swing.JOptionPane;

public class Gestion_jugadores {

    public static Jugadores jugadores[] = new Jugadores[70];

    public static void mostrarJugador(Jugadores jugador[]) {

    }

    public static void agregarJugador(Jugadores jugador[]) {
        if (Jugadores.cantidadJugadores < jugadores.length) {

            //int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del jugador"));
            String nombre = JOptionPane.showInputDialog("Agregue el nombre del jugador: ");
            Posicion posicion = Botones_Jugadores.seleccioneLaPosicion();
            String equipo = JOptionPane.showInputDialog("Ingrese el equipo: ");
            //Falta seleccionar el equipo
            //Falta contemplar el estado acorde a la posicion de la lista

            //Esta parte va con la simulación
            //Sumar los goles anotados
            //Sumar las acciones 
            jugador[Jugadores.cantidadJugadores] = new Jugadores(10, nombre, equipo, 0, 0, posicion, Estado.titular);
            JOptionPane.showMessageDialog(null, "Jugador gregado correctament");

        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más de: " + jugador.length);

        }
    }

    public static void editarJugador(Jugadores jugador[]) {

        int idn = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del jugador: "));
        boolean creado = false;

        for (int i = 0; i < Jugadores.cantidadJugadores; i++) {
            if (jugador[i].getId() == idn) {

                String nombre = JOptionPane.showInputDialog("Agregue el nombre del jugador: ");
                Posicion posicion = Botones_Jugadores.seleccioneLaPosicion();

                //Agregar los botones para escoger si cambiar el nombre o la posicion
                jugadores[i].setNombre(nombre);
                jugadores[i].setPosicion(posicion);
                
                JOptionPane.showMessageDialog(null, "Datos del jugador editados con exito");
                creado = true;
                

            }
        }
        if (!creado) {
            JOptionPane.showMessageDialog(null, "JUGADOR NO ENCONTRADO");
        }
    }

    public static void eliminarJugador(Jugadores jugadores[]) {
        
        boolean creado = false;
        int idn = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del jugador que desea eliminar: "));
        for (int i = 0; i < Jugadores.cantidadJugadores; i++) {
            if (jugadores[i].getId() == idn) {
                
                jugadores[i] = null;
                ordenar(jugadores);
                Jugadores.cantidadJugadores --;
                
                
              JOptionPane.showMessageDialog(null, "Datos del jugador editados con exito");
                creado = true;  
            }
            
        }
        if (!creado) {
            JOptionPane.showMessageDialog(null, "JUGADOR NO ENCONTRADO");
        }
    }
    
    
    private static void ordenar(Jugadores jugadores[]){
        Jugadores jugadoresRemplazo[] = new Jugadores[jugadores.length];
        int contador = 0;
        
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null) {
                jugadoresRemplazo[contador] = jugadores[i];
                contador++;
            }
        }
        
        for (int i = 0; i < jugadoresRemplazo.length; i++) {
            jugadores[i] = jugadoresRemplazo[i];
            
        }
    }
    
    
    
    
    
    
    public static void genericos(Jugadores jugador[]) {

    }

}
