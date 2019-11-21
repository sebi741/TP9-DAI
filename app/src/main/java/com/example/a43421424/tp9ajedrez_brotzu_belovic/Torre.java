package com.example.a43421424.tp9ajedrez_brotzu_belovic;

public class Torre extends Pieza{

    public Torre(int x, int y, String nombreArchivoImagen) {
        super(x, y, nombreArchivoImagen);
    }


    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador) {
        if(super.movidaValida(tablero, desdeX, desdeY, haciaX, haciaY, jugador) == false) {
            return false;
        }
        if(haciaX == desdeX) {
            if (desdeY<haciaY){
                for (int i=desdeY+1; i<haciaY;i++){
                    if (tablero.getLugar(desdeX,i).estaOcupado() == true){
                        return false;
                    }
                }
            }
            else{
                for (int i=desdeY+1; i>haciaY; i--){
                    if (tablero.getLugar(desdeX,i).estaOcupado() == true){
                        return false;
                    }
                }
            }
            return true;
        }
        if(haciaY == haciaY) {
            if (desdeX<haciaX){
                for (int i=desdeX+1; i<haciaX; i++){
                    if (tablero.getLugar(i,desdeY).estaOcupado() == true){
                        return false;
                    }
                }
            }
            else{
                for (int i=desdeX+1; i>haciaX; i--){
                    if (tablero.getLugar(i,desdeY).estaOcupado()){
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }
}
