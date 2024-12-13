
package com.mycompany.proyectofutbol;

import javax.swing.JOptionPane;

public class Botones_Jugadores {
    

    public static Posicion seleccioneLaPosicion() {
        String botones[] = {"portero",
            "defensa","mediocampista","delantero"};

        int valorBoton = JOptionPane.showOptionDialog(
                null,
                "Seleccione una posicion: ",
                "Selecccione",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                botones,
                "portero");

        switch (valorBoton) {
            case 0:
                return Posicion.portero;
            case 1:
                return Posicion.defensa;
            case 2: 
                return Posicion.mediocampista;
            default: 
                return Posicion.delantero;
        }
    }
        
    public static Estado seleccioneElEstado() {
        String botones1[] = {"Titular",
            "Suplente","Libre"};

        int valorBoton1 = JOptionPane.showOptionDialog(
                null,
                "Seleccione el estado del jugador: ",
                "Selecccione",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                botones1,
                "portero");

        switch (valorBoton1) {
            case 0: 
                return Estado.titular;
            case 1:
                return Estado.suplente;
            default: 
                return Estado.libre;
        }
    
}

}
