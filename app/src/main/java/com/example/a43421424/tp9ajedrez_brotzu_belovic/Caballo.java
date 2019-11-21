package com.example.a43421424.tp9ajedrez_brotzu_belovic;

import android.util.Log;

public class Caballo extends Pieza {

    public Caballo(int x, int y, String nombreArchivoImagen) {
        super(x, y, nombreArchivoImagen);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador) {
        Log.d("MovidaValida", "valido que la movida sea valida segun la clase Pieza");
        if(super.movidaValida(tablero,desdeX, desdeY, haciaX, haciaY, jugador) == false) {
            return false;
        }
        Log.d("MovidaValida", "valido que deltay = 2 y deltax = 1 || deltay = 1 y deltax = 2");
        if (Math.abs(desdeX-haciaX) == 1 && Math.abs(desdeY-haciaY) == 2){
            return true;
        }
        if (Math.abs(desdeX-haciaX) == 2 && Math.abs(desdeY-haciaY) == 1){
            return true;
        }
        return false;
    }
}

