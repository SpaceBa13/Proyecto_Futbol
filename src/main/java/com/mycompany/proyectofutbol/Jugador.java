package com.mycompany.proyectofutbol;

import javax.swing.JOptionPane;

public class Jugador {

    // Atributos
    private int id;
    private String nombre;
    private String equipo;
    private int golesAnotados;
    private int acciones;
    private Posicion posicion;
    private Estado estado;
    private static int idCounter = 10; // ID inicial que se incrementará automáticamente

    // Constructores

    // Constructor con parámetros
    public Jugador(String nombre, String equipo, Posicion posicion) {
        this.id = idCounter++; // Asigna el ID único y lo incrementa
        this.nombre = nombre;
        this.equipo = equipo;
        this.posicion = posicion;
        this.golesAnotados = 0;
        this.acciones = 0;
    }

    // Constructor vacío
    public Jugador() {}

    // Métodos

    // Mostrar los datos del jugador
    public void mostrar_datos() {
        System.out.print("============================================");
        JOptionPane.showMessageDialog(null,
                "Datos del jugador " + id
                        + " || Nombre: " + nombre
                        + " || Posición: " + posicion
                        + " || Equipo: " + equipo
                        + " || Estado: " + estado
                        + " || Goles: " + golesAnotados);
    }

    // Encapsuladores (Getters y Setters)

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public String get_nombre() {
        return nombre;
    }

    public void set_nombre(String nombre) {
        this.nombre = nombre;
    }

    public String get_equipo() {
        return equipo;
    }

    public void set_equipo(String equipo) {
        this.equipo = equipo;
    }

    public int get_goles_anotados() {
        return golesAnotados;
    }

    public void set_goles_anotados(int golesAnotados) {
        this.golesAnotados = golesAnotados;
    }

    public int get_acciones() {
        return acciones;
    }

    public void set_acciones(int acciones) {
        this.acciones = acciones;
    }

    public Posicion get_posicion() {
        return posicion;
    }

    public void set_posicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Estado get_estado() {
        return estado;
    }

    public void set_estado(Estado estado) {
        this.estado = estado;
    }
}
