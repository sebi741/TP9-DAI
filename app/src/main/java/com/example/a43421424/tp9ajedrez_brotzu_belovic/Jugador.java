package com.example.a43421424.tp9ajedrez_brotzu_belovic;

import android.util.Log;
import java.util.ArrayList;

public class Jugador {
    public boolean blanco;
    public final int PEONES = 8;

    private boolean miTurno = false;
    private ArrayList<Pieza> arrayPiezasJugador = new ArrayList<>();

    public Jugador(boolean blanco) {
        Log.d("BobJugador", "si el jugador es blanco seteo miTurno a true");
        this.blanco = blanco;
        if (this.blanco){
            this.miTurno = true;
        }
    }

    public ArrayList<Pieza> getPiezas() {
        return arrayPiezasJugador;
    }

    public void setMiTurno(boolean miTurno) {this.miTurno = miTurno;}
    public boolean getMiTurno(){return miTurno;}

    public void incializarPiezas(){
        Log.d("inicializarpiezas" , "Agrego las piezas a la lista de piezas del jugador");
        if(this.blanco == true){
            Log.d("inicializarPiezas" , "si el jugador es blancas agrego las piezas blancas a la lista");
            for(int i=0; i<PEONES; i++){
                arrayPiezasJugador.add(new Peon(i,1,"peonblanco.png"));
            }
            arrayPiezasJugador.add(new Torre(0, 0, "torreblanca.png"));
            arrayPiezasJugador.add(new Torre(7, 0, "torreblanca.png"));
            arrayPiezasJugador.add(new Alfil(2, 0, "alfilblanco.png"));
            arrayPiezasJugador.add(new Alfil(5, 0, "alfilblanco.png"));
            arrayPiezasJugador.add(new Caballo(6, 0, "caballoblanco.png"));
            arrayPiezasJugador.add(new Caballo(1, 0, "caballoblanco.png"));
            arrayPiezasJugador.add(new Rey(4, 0, "reyblanco.png"));
            arrayPiezasJugador.add(new Reina(3, 0, "reinablanca.png"));
        }
        else{
            Log.d("inicializarPiezas" , "si el jugador es negras agrego las piezas negras a la lista");
            for(int i=0; i<PEONES; i++){
                arrayPiezasJugador.add(new Peon(i,6, "peonnegro.png"));
            }
            arrayPiezasJugador.add(new Torre(0, 7, "torrenegra.png"));
            arrayPiezasJugador.add(new Torre(7, 7, "torrenegra.png"));
            arrayPiezasJugador.add(new Alfil(2, 7, "alfilnegro.png"));
            arrayPiezasJugador.add(new Alfil( 5, 7, "alfilnegro.png"));
            arrayPiezasJugador.add(new Caballo(6, 7, "caballonegro.png"));
            arrayPiezasJugador.add(new Caballo(1, 7, "caballonegro.png"));
            arrayPiezasJugador.add(new Rey(4, 7, "reynegro.png"));
            arrayPiezasJugador.add(new Reina(3, 7, "reinanegra.png"));
        }

    }

    public boolean ExistePiezaJugador(Pieza pieza){
        Log.d("ExistePiezaJugador", "devuelvo true si la pieza pasada como parametro xiste en el arrayPiezasJugador");
        if (arrayPiezasJugador.contains(pieza)){
            return true;
        }
        return false;
    }
}
