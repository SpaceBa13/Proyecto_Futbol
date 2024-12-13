package com.mycompany.proyectofutbol;

import javax.swing.JOptionPane;

public class Proyectofutbol {
    public static void main(String[] args) {
        ManagePlayers managePlayers = new ManagePlayers();
        ManageTeams manageTeams = new ManageTeams();
        String[] botones = {" Gestión de Jugadores", " Gestión de Equipos", " Simulación de Partidos", " Reportes", " Salir del Sistema"};
        int valorbotones;
        Jugador jugadores[] = new Jugador[70];
        Equipo equipos[] = new Equipo[20];

        // Llamar a la clase de inicialización para generar los datos
        DataInitializer.inicializarDatos(equipos, jugadores);
        // Mostrar los equipos generados con sus jugadores
        for (Equipo equipo : equipos) {
            if (equipo != null) {
                equipo.mostrarInformacion();
            }
        }

        // Mostrar los jugadores sin equipo
        System.out.println("\nJugadores sin equipo:");
        for (Jugador jugador : jugadores) {
            if (jugador != null && (jugador.getEquipo() == null || jugador.getEquipo().isEmpty())) {
                System.out.println("ID: " + jugador.getId() + ", Nombre: " + jugador.getNombre() + ", Posición: " + jugador.getPosicion());
            }
        }



        valorbotones = JOptionPane.showOptionDialog(
                null,
                "Seleccione una  de las siguientes opciónes para realizar :",
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                botones,
                botones[0]);
        
        Equipo Equipo1 = new Equipo("1");
        Equipo Equipo2 = new Equipo("2");
        Equipo Equipo3 = new Equipo("3");

        while (valorbotones != 5) {
            switch (valorbotones) {
                case 0: //Menu de jugadores
                    JOptionPane.showMessageDialog(null, "Bienvenido a Gestión de Jugadores ");

                    String[] botones1 = {" Crear jugadores ", " Editar jugador", " Eliminar jugador", "Mostrar jugador", "Volver"
                    };
                    int menujugadores;

                    menujugadores = JOptionPane.showOptionDialog(
                            null,
                            "Seleccione una  de las siguientes opciónes para realizar :",
                            "Menú Principal",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            botones1,
                            botones1[0]);

                    while (menujugadores != 5) {
                        switch (menujugadores) {
                            case 0:
                                String nombre = JOptionPane.showInputDialog(
                                        "Ingrese el nombre del jugador: ");

                                //PRUEBA
                                String equi = JOptionPane.showInputDialog(
                                        "Ingrese el equipo del jugador: ");

                                //Se agregan los botones para seleccionar la Posicion
                                Posicion posicion = Botones_Jugadores.seleccioneLaPosicion();


                                Jugador jugador_modelo = new Jugador(nombre, equi, posicion);

                                managePlayers.agregar_jugadores(jugador_modelo, jugadores);
                                managePlayers.agregar_jugador_a_equipos_disponibles(jugador_modelo, equipos);

                                menujugadores = 5;
                                break;
                            case 1://Edicion de jugadores
                                int id_jugador_edit = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del jugador que desea eliminar"));
                                managePlayers.editar_Jugador(jugadores,id_jugador_edit);
                                menujugadores = 5;
                                break;
                            case 2://Eliminacion de jugadores
                                int id_jugador = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del jugador que desea eliminar"));
                                managePlayers.eliminar_jugador(jugadores, id_jugador);
                                menujugadores = 5;
                                break;
                            case 3:
                                managePlayers.imprimir_jugadores(jugadores);
                                menujugadores = 5;
                                break;
                            case 4:
                                menujugadores = 5;
                                main(null);
                                break;
                            default:
                                menujugadores = 5;
                                break;
                        }
                    }
                    break;

                case 1: // Menu de Equipos
                    //Configuracion inicial de botones
                    String[] botonesEquipos = {"Mostrar Equipos", "Agregar Equipo", "Agregar Jugador a un Equipo", "Eliminar Jugador de un equipo", "Regresar"};
                    int valorEquipos;

                    valorEquipos = JOptionPane.showOptionDialog(
                            null,
                            "Seleccione una  de las siguientes opciónes para realizar :",
                            "Gestion de Jugadores",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            botonesEquipos,
                            botonesEquipos[0]);

                    while (valorEquipos != 5) {
                        //Casos 
                        switch (valorEquipos) {
                            case 0: //Metodo de mostrar
                                manageTeams.mostrar_equipos(equipos);
                                valorEquipos = 5;
                                break;
                            case 1: //Metodo de agregar
                                manageTeams.agregarEquipo(equipos, jugadores);
                                valorEquipos = 5;
                                break;
                            case 2: //Metodo de agregar un jugador a un equipo
                                JOptionPane.showMessageDialog(null, "Agregar Jugador a un Equipo");
                                break;
                            case 3:
                                JOptionPane.showMessageDialog(null, "Eliminar Jugador a un Equipo");
                                break;
                            default:
                                valorEquipos = 5;
                                break;
                        }
                    }
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Bienvenido a Simulación de Partidos ");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Bienvenido a Reportes .");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Muchas gracias por utilizar nuestro sistema.");
                    valorbotones = 5;
                    break;
            }

            if (valorbotones == 5) {
                break;
            }

            Equipo[] equipos_partido = {Equipo1, Equipo2};

            /*
            Partido partido = new Partido(equipos_partido);
            */

            valorbotones = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una  de las siguientes opcióne para realizar :",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    botones,
                    botones[0]);
        }
        
    }


}
