package com.example.a43421424.tp9ajedrez_brotzu_belovic;

import android.util.Log;

public class Alfil extends Pieza {

    public Alfil(int x, int y, String nombreArchivoImagen) {
        super(x, y, nombreArchivoImagen);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador) {
        Log.d("MovidaValida", "valido que la movida sea valida segun la clase Pieza");
        if(super.movidaValida(tablero,desdeX, desdeY, haciaX, haciaY, jugador) == false) {
            return false;
        }

        Log.d("MovidaValida", "valido que deltay sea igual a deltax, osea que movio en diagonal");
        if(Math.abs(haciaX - desdeX) == Math.abs(haciaY - desdeY)) {
            Log.d("movidaValida", "valido que no se saltee piezas");
            Log.d("movidaValida", "recorro todos los lugares intermedios entre donde agarro la pieza y donde la solto" +
                    " y compruebo que esos casilleros no tengan piezas");
            if (haciaX>desdeX){
                if (haciaY>desdeY){
                    int j = desdeY;
                    for(int i=desdeX+1; i<haciaX; i++){
                        j++;
                        if (tablero.getLugar(i,j).estaOcupado() == true){
                            Log.d("movidaValidaAlfil" , "i"+i+"j"+j);
                            return false;
                        }
                    }
                }
                else{
                    int j = desdeY;
                    for(int i=desdeX+1; i<haciaX; i++){
                        j--;
                        if (tablero.getLugar(i,j).estaOcupado() == true){
                            Log.d("movidaValidaAlfil" , "i"+i+"j"+j);
                            return false;
                        }
                    }
                }
            }
            else{
                if (haciaY>desdeY){
                    int j = desdeY;
                    for(int i=desdeX-1; i>haciaX; i--){
                        j++;
                        if (tablero.getLugar(i,j).estaOcupado()){
                            Log.d("movidaValidaAlfil" , "i"+i+"j"+j);
                            return false;
                        }
                    }
                }
                else{
                    int j=desdeY;
                    for(int i=desdeX-1; i>haciaX; i--){
                        j--;
                        if (tablero.getLugar(i,j).estaOcupado()){
                            Log.d("movidaValidaAlfil" , "i"+i+"j"+j);
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
