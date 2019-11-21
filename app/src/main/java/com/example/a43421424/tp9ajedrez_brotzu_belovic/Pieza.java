package com.example.a43421424.tp9ajedrez_brotzu_belovic;

import android.util.Log;

import org.cocos2d.nodes.Sprite;

public class Pieza {
    private boolean comida;
    private int x;
    private int y;
    private Sprite sprite;

    public Pieza( int x, int y, String nombreArchivoImagen) {
        this.comida = false;
        sprite = Sprite.sprite(nombreArchivoImagen);
        this.x = x;
        this.y = y;
    }

    public boolean getComida() {
        return comida;
    }
    public void setComida(boolean disponible) {
        this.comida = disponible;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Sprite getSprite() {return sprite;}
    public void setSprite(Sprite sprite) {this.sprite = sprite;}

    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador){
        if(haciaX == desdeX && haciaY == desdeY) {
            return false; //no se puede no mover
        }
        if(haciaX < 0 || haciaX > 7 || desdeY < 0 || desdeY > 7) {
            return false;
        }
        if (jugador.getMiTurno() == false){
            return false;
        }
        if (jugador.getPiezas().contains(tablero.getLugar(haciaX,haciaY).pieza)){
            Log.d("movidaValida" , "es una movida invalida porque trato de mover la pieza en un lugar donde ya tenia otra suya");
            return false;
        }
        return true;
    }

}
