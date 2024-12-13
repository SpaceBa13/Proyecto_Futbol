package com.mycompany.proyectofutbol;

import javax.swing.JOptionPane;

public class Equipos {
    
    //Atributos
    
    public int id;
    public String nombreEquipo;
    public int pJugados = 0;
    public int pGanados = 0;
    public int pEmpatados = 0;
    public int pPerdidos = 0;
    public int golesF = 0;
    public int golesC = 0;
    public int posesion = 0;
    public static int contador = 1;
    //Constructores
        
    //Metodos
    public void mostrarEquipos(int nEquipo) { 
        
        
        System.out.println("============================================================");
        System.out.println("Equipo "+ nEquipo + ": " + nombreEquipo);
        System.out.println("============================================================");
        System.out.println("Estadisticas e informacion tecnica:\n");  
        System.out.println("ID del Equipo " + nEquipo + ": " + id);
        System.out.println("Partidos jugados: " + pJugados);
        System.out.println("Partidos ganados: " + pGanados);
        System.out.println("Partidos empatados: " + pEmpatados);
        System.out.println("Partidos perdidos: " + pPerdidos);
        System.out.println("Goles a favor: " + golesF);
        System.out.println("Goles en contra: " + golesC);
        System.out.println("Porcentaje de posesion: " + posesion);  
        System.out.println("============================================================\n");
    }    
    
}