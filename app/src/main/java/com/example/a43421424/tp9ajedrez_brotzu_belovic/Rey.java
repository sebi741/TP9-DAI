package com.example.a43421424.tp9ajedrez_brotzu_belovic;

import android.util.Log;

public class Rey extends Pieza{

    public Rey(int x, int y, String nombreArchivoImagen) {
        super( x, y, nombreArchivoImagen);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int hastaX, int hastaY, Jugador jugador) {
        if(super.movidaValida(tablero, desdeX, desdeY, hastaX, hastaY, jugador) == false) {
            Log.d("esValida" , "si para la clase Pieza no es valida la movida, devuelve false");
            return false;
        }
        if(Math.abs(desdeX-hastaX) <=1 && Math.abs(desdeY-hastaY) <= 1){
            return true;
        }
        return false;
    }
}
