package com.example.a43421424.tp9ajedrez_brotzu_belovic;

import android.util.Log;

public class Tablero {

    public Lugar[][] matrizLugares = new Lugar[8][8];

    public Tablero() {
        Log.d("ContructorTablero" , "Lleno el tablero de lugares");
        //i es ancho;  j es alto;
        for(int i=0; i<matrizLugares.length; i++){
            for(int j=0; j<matrizLugares.length; j++){
                if ((i%2 == 0 && j%2 == 0 )|| (i%2 == 1&& j%2 == 1)){
                    this.matrizLugares[i][j] = new Lugar(i, j, "lugarnegro.png");
                }
                else{
                    this.matrizLugares[i][j] = new Lugar(i, j, "lugarblanco.png");
                }
            }
        }
    }

    public Lugar getLugar(int x, int y) {
        Log.d("getLugar" , "devuelvo el Lugar correspondiente a la x e y pasadas como parametro");
        return matrizLugares[x][y];
    }
}

