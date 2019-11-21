package com.example.a43421424.tp9ajedrez_brotzu_belovic;
import android.util.Log;

public class Peon extends Pieza{

    public Peon(int x, int y, String nombreArchivoImagen) {
        super(x, y, nombreArchivoImagen);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador) {
        Log.d("movidaValida" , "compruebo que la movida del peon sea valida");
        if(super.movidaValida(tablero,desdeX, desdeY, haciaX, haciaY, jugador) == false) {
            Log.d("movidaValidaPeon" , "movida invalida de la clase Pieza");
            return false;
        }

        if (jugador.blanco == true){
            if(haciaX == desdeX && tablero.getLugar(haciaX, haciaY).estaOcupado() == false && haciaY-desdeY == 1) {
                Log.d("movidaValidaPeon" , "movio un peon blanco un casillero para adelante");
                return true;
            }

            if(haciaX - desdeX == haciaY - desdeY && Math.abs(haciaX-desdeX) == 1 && haciaY - desdeY == 1 && tablero.getLugar(haciaX, haciaY).estaOcupado() == true) {
                Log.d("movidaValidaPeon" , "comio con un peon blanco un casillero para adelante en diagonal");
                return true;
            }

            if (desdeY == 1 && haciaY == 3 && desdeX == haciaX){
                if (tablero.getLugar(desdeX,desdeY+1).estaOcupado() == true){
                    Log.d("movidaValidaPeon" , "movida invalida porque el peon se salteo una pieza");
                    return false;
                }
                Log.d("movidaValidaPeon" , "movio el peon blanco dos casilleros para adelante");
                return  true;
            }
        }
        else{
            if (desdeY == 6 && haciaY == 4 && desdeX == haciaX){
                if (tablero.getLugar(desdeX,desdeY-1).estaOcupado() == true){
                    Log.d("movidaValidaPeon" , "movida invalida porque el peon se salteo una pieza");
                    return false;
                }
                Log.d("movidaValidaPeon" , "movio el peon negro dos casilleros para adelante");
                return true;
            }
            if (haciaX == desdeX && tablero.getLugar(haciaX, haciaY).estaOcupado() == false && haciaY-desdeY == -1){
                Log.d("movidaValidaPeon" , "movio un peon negro un casillero para adelante");
                return  true;
            }
            if(haciaX - desdeX == haciaY - desdeY && Math.abs(haciaX-desdeX) == 1 && haciaY - desdeY == -1 && tablero.getLugar(haciaX, haciaY).estaOcupado() == true) {
                Log.d("movidaValidaPeon" , "comio con un peon negro un casillero para adelante en diagonal");
                return true;
            }
        }
        return false;
    }
}
