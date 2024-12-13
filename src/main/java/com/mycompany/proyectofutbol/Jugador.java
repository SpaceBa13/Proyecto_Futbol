package com.mycompany.proyectofutbol;

import javax.swing.JOptionPane;

public class Jugador {

    //Atributos
    private int id;
    private String nombre;
    private String equipo;
    private int golesAnotados;
    private int acciones;
    private Posicion posicion;
    private Estado estado;
    private static int idCounter = 10; // ID inicial que se incrementará automáticamente
    //Contructores

    public Jugador(String nombre, String equipo, int golesAnotados, int acciones, Posicion posicion) {
        this.id = idCounter++; // Asigna el ID único y lo incrementa
        this.nombre = nombre;
        this.equipo = equipo;
        this.golesAnotados = golesAnotados;
        this.acciones = acciones;
        this.posicion = posicion;
    }

    public Jugador(){
    }

    //Metodos
    public void mostrarDatos() {
        System.out.print("============================================");
        JOptionPane.showMessageDialog(null,
                "Datos del jugador " + id
                + "||"+ "Nombre " + nombre
                + "||"+ "Posicion" + posicion
                + "||"+ "Equipo " + equipo
                + "||"+ "Estado " + estado 
                + "||"+ "Goles " + golesAnotados);
    }


    //Encapsuladores
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getGolesAnotados() {
        return golesAnotados;
    }

    public void setGolesAnotados(int golesAnotados) {
        this.golesAnotados = golesAnotados;
    }

    public int getAcciones() {
        return acciones;
    }

    public void setAcciones(int acciones) {
        this.acciones = acciones;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
