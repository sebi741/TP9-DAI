package com.example.a43421424.tp9ajedrez_brotzu_belovic;


import android.util.Log;

public class Ajedrez {
    private Tablero tablero = new Tablero();
    private Jugador blancas;
    private Jugador negras;

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Jugador getBlancas() {
        return blancas;
    }

    public void setBlancas(Jugador jugador) {
        this.blancas = jugador;
    }

    public Jugador getNegras() {
        return negras;
    }

    public void setNegras(Jugador jugador) {
        this.negras = jugador;
    }

    public boolean inicializarTableroDadosLosJugadores() {
        if(this.negras == null || this.blancas == null) {
            return false;
        }
        this.tablero = new Tablero();
        for(int i=0; i<negras.getPiezas().size(); i++){
            Log.d("InicializarTablero" , "obtengo el lugar que debe ocupar la pieza y lo ocupo");
            tablero.getLugar(negras.getPiezas().get(i).getX(), negras.getPiezas().get(i).getY()).OcuparLugar(negras.getPiezas().get(i));
            tablero.getLugar(blancas.getPiezas().get(i).getX(), blancas.getPiezas().get(i).getY()).OcuparLugar(blancas.getPiezas().get(i));
        }
        return true;
    }
}
