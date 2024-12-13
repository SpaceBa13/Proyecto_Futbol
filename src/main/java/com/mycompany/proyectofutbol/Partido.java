package com.mycompany.proyectofutbol;

import java.util.Random;

public class Partido {

    Equipo[] equipos = new Equipo[2];
    int probabilidad = 0;
    int tipo_de_evento_transicion;
    Random rand = new Random();
    int moneda;

    Partido(Equipo equipo1, Equipo equipo2, Equipo[] equipos) {
        this.equipos = equipos;
    }

    //Equipo 1 jugador
    /*
    void Simular(){
        for(int i = 0; i <= 40; i++){
            probabilidad = rand.nextInt(1,100);
            //Eventos de transion
            if (probabilidad <= 70){
                Random rand_transicion = new Random();
                tipo_de_evento_transicion = rand.nextInt(1,5);
                switch (tipo_de_evento_transicion){
                    //Equipo X esta atacando intesamente
                    case 1:
                        moneda = rand.nextInt(0, 1);
                        System.out.println(equipos[moneda].id + "Esta cogiendose al otro :v");
                        break;
                    //El equipo [Equipo B]
                    //tomó el control de la
                    //pelota
                    case 2:
                        moneda = rand.nextInt(0, 1);
                        System.out.println(equipos[moneda].id + "Tomo control de la pelota");
                        break;
                    //[Jugador] realiza una
                    //atrapada espectacular.
                    //ESTA NO ES TRANSISITIVA!!!!!!!!!!!!!!!!!!!!!
                    case 3:

                        moneda = rand.nextInt(0, 1);
                        for(int i = 0; i < equipos[moneda].jugadores.lengt ; i++){
                            if (equipos[moneda].jugadores[i].posicion.equals("NO Portero")){
                                /*Aqui se agregaequipos[moneda].jugadores[i].setGoles(/*Aqui se busca y se le suma uno para agregarlo
                                equipos[moneda].jugadores[i].getGoles ++);
                                equipos[moneda].golesF ++;
                                equipos[no moneda].golesC --;
                                System.out.println(equipos[moneda].id + "Hizo un gol");
                            }
                        }

                }

            }

        }
    */
}