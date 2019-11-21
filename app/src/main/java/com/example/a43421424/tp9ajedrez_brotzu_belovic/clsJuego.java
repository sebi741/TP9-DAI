package com.example.a43421424.tp9ajedrez_brotzu_belovic;

import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.RotateBy;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

public class clsJuego {
    Scene escenaADevolver;
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    float ladoLugar;
    Ajedrez ajedrez = new Ajedrez();
    Tablero tablero;
    Jugador jugadorBlancas;
    Jugador jugadorNegras;

    //constructor
    clsJuego(CCGLSurfaceView VistaDelJuego) {
        Log.d("Constructor clsJuego", "Comienza el constructor de la clase");
        _VistaDelJuego = VistaDelJuego;
    }

    public void ComenzarJuego() {
        Log.d("Comenzar", "Comienza el juego");
        Director.sharedDirector().attachInView(_VistaDelJuego);

        PantallaDelDispositivo = Director.sharedDirector().displaySize();

        Log.d("Comenzar" , "Saco el lado de cada lugar dividiendo el ancho de la pantalla por 8");
        ladoLugar = PantallaDelDispositivo.getWidth()/8;
        Log.d("Comenzar" , "instancio un jugador con negras y uno con blancas");
        jugadorBlancas = new Jugador(true);
        jugadorNegras = new Jugador(false);
        Log.d("Comenzar" , "asigno los jugadores a la clase Ajedrez");
        ajedrez.setBlancas(jugadorBlancas);
        ajedrez.setNegras(jugadorNegras);
        Log.d("Comenzar" , "incializo las piezas de los jugadores");
        jugadorBlancas.incializarPiezas();
        jugadorNegras.incializarPiezas();
        Log.d("Comenzar" , "incializo el tablero con sus lugares");
        ajedrez.inicializarTableroDadosLosJugadores();
        tablero = ajedrez.getTablero();

        Log.d("Comenzar", "Le digo al director que comience la escena");
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }

    private Scene EscenaDelJuego() {
        Log.d("EscenaDelJuego", "Comienza el armado de la escena del juego");

        Log.d("Escena del juego", "Declaro e instancio la escena en si");
        escenaADevolver = Scene.node();

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener la imagen del fondo");
        CapaTablero miCapaTablero = new CapaTablero();
        CapaPiezas miCapaPiezas = new CapaPiezas();

        Log.d("EscenaADevolver", "Agrego a la escena la capa del fondo y la del frente");
        escenaADevolver.addChild(miCapaTablero, -10);
        escenaADevolver.addChild(miCapaPiezas, 10);

        Log.d("EscenaADevolver", "Devuelvo al escena ya armada");
        return escenaADevolver;
    }

    class CapaTablero extends Layer {

        public CapaTablero() {
            Log.d("CapaTablero", "Comienza el constructor de CapaTablero");
            Log.d("CapaTablero", "Pongo la imagen del tablero");
            PonerImagenesTablero();
        }

        private void PonerImagenesTablero() {
            Log.d("PonerImagenesTablero", "Comienzo a poner las imagenes del tablero");

            Log.d("PonerImagenesTablero", "obtengo el factor por el que tengo que agrandar al sprite para que ocupe 1/8 del ancho de la pantalla");
            //Factor = Lo que quiero que ocupe / Lo que ocupa ahora
            Float FactorAncho = (ladoLugar) / tablero.matrizLugares[0][0].sprite.getWidth();
            Log.d("PonerImagenesTablero" , "obtengo el punto del eje vertical donde va a empezar el tablero. Que es 1/4 del alto de la pantalla");
            float primerPiezaY = PantallaDelDispositivo.getHeight()/4;
            float primerPiezaX = ladoLugar/2;
            Log.d("PonerImagenesTablero" , "recorro la matriz");
            for(int i=0; i<tablero.matrizLugares.length; i++){
                Log.d("PonerImagenesTablero" , "complete una columna");
                for(int j=0; j<tablero.matrizLugares.length; j++){
                    Log.d("PonerImagenesTablero" , "agrando el sprite dentro del objeto Lugar para que ocupe 1/8 del ancho");
                    tablero.matrizLugares[i][j].sprite.runAction(ScaleBy.action(0.01f, FactorAncho));
                    Log.d("PonerImagenesTablero" , "pongo el sprite en su posicion");
                    tablero.matrizLugares[i][j].sprite.setPosition(i*ladoLugar + primerPiezaX, j*ladoLugar + primerPiezaY);
                    Log.d("PonerImagenesTablero" , "agrego el sprite a la capa");
                    super.addChild(tablero.matrizLugares[i][j].sprite);
                }
            }

        }
    }

    class CapaPiezas extends Layer {
        private int desdeX;
        private int desdeY;
        private Jugador jugadorMueve;
        private Jugador jugadorEspera;
        Label lblMiTurno;
        @Override
        public boolean ccTouchesBegan(MotionEvent event) {
            desdeX = (int) Math.floor(event.getX() / ladoLugar);
            //deltaX -->(lugar de toque - punta inferior izquierda del tablero) /ladolugar = DesdeY
            desdeY = (int) Math.floor(((PantallaDelDispositivo.getHeight() - event.getY()) - (tablero.getLugar(0, 0).sprite.getPositionY() - (tablero.getLugar(0, 0).sprite.getHeight() / 2))) / ladoLugar);
            Log.d("cctouchesbegan", "desdex" + desdeX);
            Log.d("cctouchesbegan", "desdey" + desdeY);
            if(desdeX > 7 || desdeY > 7) return true;
            if (tablero.getLugar(desdeX, desdeY).estaOcupado() == true) {
                Log.d("ccTouchesBegan" , "si toco en un lugar con una pieza entro");
                if (jugadorBlancas.ExistePiezaJugador(tablero.getLugar(desdeX, desdeY).pieza)) {
                    Log.d("ccTouchesBegan" , "si la pieza que toco es balnca el jugador que mueve es blanco");
                    jugadorMueve = jugadorBlancas;
                    jugadorEspera = jugadorNegras;
                } else {
                    Log.d("ccTouchesBegan" , "si la pieza que toco es negra el jugador que mueve es negro");
                    jugadorMueve = jugadorNegras;
                    jugadorEspera = jugadorBlancas;
                }
            }
            return true;
        }
        @Override
        public boolean ccTouchesMoved(MotionEvent event){
            Log.d("ccTouchesMoved" , "valido que se haya tocado un casillero");
            if (desdeX<8 && desdeX>-1){
                Log.d("ccTouchesMoved" , "valido que se haya tocado un casillero con una pieza");
                if (tablero.getLugar(desdeX, desdeY).estaOcupado() == true) {
                    Log.d("ccTouchesMoved", "hago que la pieza se mueva con el touch");
                    tablero.getLugar(desdeX, desdeY).pieza.getSprite().setPosition(event.getX(), PantallaDelDispositivo.getHeight() - event.getY());
                }
            }
            return true;
        }

        @Override public boolean ccTouchesEnded (MotionEvent event){
            Log.d("ccTouchesEnded" , "valido que toco en un casillero");
            if (desdeX<8 && desdeX>-1) {
                Log.d("ccTouchesEnded" , "valido que toco un caillero con una pieza");
                if(desdeX > 7 || desdeY > 7) return true;
                if (tablero.getLugar(desdeX, desdeY).estaOcupado() == true) {
                    Log.d("ccTouchesEnded" , "creo un objeto pieza y le asigno la pieza que se movio");
                    Pieza piezaMovida = tablero.getLugar(desdeX, desdeY).pieza;
                    Log.d("ccTouchesEnded" , "valido que toco en un casillero");

                    int HaciaX = (int) Math.floor(event.getX() / ladoLugar);
                    int HaciaY = (int) Math.floor(((PantallaDelDispositivo.getHeight() - event.getY()) - (tablero.getLugar(0, 0).sprite.getPositionY() - (tablero.getLugar(0, 0).sprite.getHeight() / 2))) / ladoLugar);
                    Log.d("ccTouchesEnded" , "valido que toco en un casillero");
                    if (HaciaY>-1 && HaciaY<8){
                        if(tablero.getLugar(desdeX, desdeY).pieza==null)return true;
                        Log.d("ccTouchesEnded", "haciax" + HaciaX + " haciay" + HaciaY);
                        Log.d("ccTouchesEnded" , "valido que la pieza la solto adentro de un solo casillero, sin tocar otro");
                        if (InterseccionEntreSprites(tablero.getLugar(desdeX, desdeY).pieza.getSprite(), tablero.getLugar(HaciaX, HaciaY).sprite)) {
                            Log.d("ccTouchesEnded" , "valido que la movida sea valida");
                            if (tablero.getLugar(desdeX, desdeY).pieza.movidaValida(tablero, desdeX, desdeY, HaciaX, HaciaY, jugadorMueve)) {
                                if (tablero.getLugar(HaciaX, HaciaY).estaOcupado() == true) {
                                    Log.d("ccTouchesEnded" , "si solto la pieza en un lugar donde hay una pieza de otro color la como");
                                    tablero.getLugar(HaciaX, HaciaY).pieza.getSprite().runAction(MoveTo.action(1f, PantallaDelDispositivo.getWidth() + 50f, PantallaDelDispositivo.getHeight() / 2));
                                    tablero.getLugar(HaciaX, HaciaY).pieza.getSprite().runAction(RotateBy.action(1f, 720f));
                                } else {
                                    Log.d("ccTouchesEnded" , "si no comio otra pieza libero el lugar desde donde salio la pieza movida");
                                    tablero.getLugar(desdeX, desdeY).liberarLugar();
                                }
                                Log.d("ccTouchesEnded" , "ocupo el lugar hacia donde se movio con la pieza que se movio");
                                tablero.getLugar(HaciaX, HaciaY).OcuparLugar(piezaMovida);
                                Log.d("ccTouchesEnded" , "ocupo el lugar graficamente");
                                tablero.getLugar(HaciaX, HaciaY).pieza.getSprite().setPosition(tablero.getLugar(HaciaX, HaciaY).sprite.getPositionX(), tablero.getLugar(HaciaX, HaciaY).sprite.getPositionY());
                                Log.d("ccTouchesEnded" , "invierto los turnos de los jugadores");
                                jugadorMueve.setMiTurno(false);
                                jugadorEspera.setMiTurno(true);
                                if (jugadorBlancas.getMiTurno() == true){
                                    lblMiTurno.setPosition(PantallaDelDispositivo.getWidth()/2, tablero.getLugar(0,0).sprite.getPositionY()-ladoLugar/2-40f);
                                }
                                else{
                                    lblMiTurno.setPosition(PantallaDelDispositivo.getWidth()/2, tablero.getLugar(0,7).sprite.getPositionY()+ladoLugar/2+40f);
                                }
                            } else {
                                Log.d("ccTouchesEnded", "devuelvo la pieza a su lugar porque la movida no es valida");
                                tablero.getLugar(desdeX, desdeY).pieza.getSprite().setPosition(tablero.getLugar(desdeX, desdeY).sprite.getPositionX(), tablero.getLugar(desdeX, desdeY).sprite.getPositionY());
                            }
                        } else {
                            Log.d("ccTouchesEnded", "devuelvo la pieza a su lugar porque la dejo en un lugar no valido");
                            tablero.getLugar(desdeX, desdeY).pieza.getSprite().setPosition(tablero.getLugar(desdeX, desdeY).sprite.getPositionX(), tablero.getLugar(desdeX, desdeY).sprite.getPositionY());
                        }
                    } else {
                        Log.d("ccTouchesEnded", "devuelvo la pieza a su lugar porque solto la pieza afuera del tablero");
                        tablero.getLugar(desdeX, desdeY).pieza.getSprite().setPosition(tablero.getLugar(desdeX, desdeY).sprite.getPositionX(), tablero.getLugar(desdeX, desdeY).sprite.getPositionY());
                    }
                }
            }
            return true;
        }

        public CapaPiezas(){
            Log.d("BobCapaPiezas", "habilito el touch");
            this.setIsTouchEnabled(true);
            Log.d("BobCapaPiezas", "recorro la matriz");
            for(int i=0; i<tablero.matrizLugares.length; i++){
                for(int j=0; j<tablero.matrizLugares.length; j++){
                    Log.d("BobCapaPiezas", "valido que estoy en una posicion donde comienza una pieza");
                    if (j<2 || j>5) {
                        Log.d("BobCapaPiezas", "pongo los sprites en los casilleros");
                        PonerImagenPieza(i, j);
                    }
                }
            }
            lblMiTurno = Label.label("Mi turno", "Verdana", 40);
            lblMiTurno.setPosition(PantallaDelDispositivo.getWidth()/2, tablero.getLugar(0,0).sprite.getPositionY()-ladoLugar/2-40f);
            super.addChild(lblMiTurno);
        }
        public void PonerImagenPieza(int x , int y){
            Log.d("PonerImagenesTablero", "obtengo el factor por el que tengo que agrandar al sprite para que ocupe el 85% del ancho del lugar");
            Log.d("PonerImagenPieza", "ladolugar:"+ladoLugar+"ancho pieza:"+tablero.getLugar(x,y).pieza.getSprite().getWidth());
            float FactorAncho = (ladoLugar * 0.75f) / (tablero.getLugar(x,y).pieza.getSprite().getWidth());
            Log.d("PonerImagenesTablero" , "factor"+FactorAncho);
            tablero.getLugar(x,y).pieza.getSprite().runAction(ScaleBy.action(0.01f, FactorAncho));
            Log.d("PonerImagenesTablero" , "pongo la imagen en el casillero correspondiente");
            tablero.getLugar(x,y).pieza.getSprite().setPosition(tablero.getLugar(x,y).sprite.getPositionX(),tablero.getLugar(x,y).sprite.getPositionY());
            Log.d("PonerImagenesTablero" , "agrego el sprite a la capa");
            super.addChild(tablero.getLugar(x,y).pieza.getSprite());
        }
    }



    public static boolean EstaEntre(int NumeroAComparar, int NumeroMenor, int NumeroMayor){
        boolean Devolver;

        Log.d("EstaEntre" , "Numeromenor: "+NumeroMenor+" - NumeroMayor:"+NumeroMayor);

        if (NumeroMenor > NumeroMayor){
            Log.d("EstaEntre" , "Me los dieron invertidos los ordeno");
            int Auxiliar;
            Auxiliar = NumeroMayor;
            NumeroMayor = NumeroMenor;
            NumeroMenor = Auxiliar;
        }

        if (NumeroAComparar >= NumeroMenor && NumeroAComparar<= NumeroMayor){
            Log.d("EstaEntre" , "EstaEntre");
            Devolver = true;
        }
        else{
            Log.d("EstaEntre" , "No esta entre");
            Devolver = false;
        }
        return Devolver;
    }


    private boolean InterseccionEntreSprites(Sprite Sprite1, Sprite Sprite2) {
        boolean Devolver = false;

        int Sprite1Izquierda, Sprite1Derecha, Sprite1Abajo, Sprite1Arriba;
        int Sprite2Izquierda, Sprite2Derecha, Sprite2Abajo, Sprite2Arriba;

        Sprite1Izquierda = (int) (Sprite1.getPositionX() - Sprite1.getWidth() / 2);
        Sprite1Derecha = (int) (Sprite1.getPositionX() + Sprite1.getWidth() / 2);
        Sprite1Abajo = (int) (Sprite1.getPositionY() - Sprite1.getHeight() / 2);
        Sprite1Arriba = (int) (Sprite1.getPositionY() + Sprite1.getHeight() / 2);

        Sprite2Izquierda = (int) (Sprite2.getPositionX() - Sprite2.getWidth() / 2);
        Sprite2Derecha = (int) (Sprite2.getPositionX() + Sprite2.getWidth() / 2);
        Sprite2Abajo = (int) (Sprite2.getPositionY() - Sprite2.getHeight() / 2);
        Sprite2Arriba = (int) (Sprite2.getPositionY() + Sprite2.getHeight() / 2);

        Log.d("interseccion", "Sp1 - Izq: " + Sprite1Izquierda + " -Der: " + Sprite1Derecha + "- Aba:" + Sprite1Abajo + "-Arr:" + Sprite1Arriba);
        Log.d("interseccion", "Sp2 - Izq: " + Sprite2Izquierda + " -Der: " + Sprite2Derecha + "- Aba:" + Sprite2Abajo + "-Arr:" + Sprite2Arriba);

        //borde izq y borde inf de Sprite1 esta dentro de Sprite2
        if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha)&& EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)){
            Log.d("Interseccion" , "1");
            //borde izq y borde sup de Sprite1 esta dentro de Sprite2
            if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda,Sprite2Derecha)&&EstaEntre(Sprite1Arriba, Sprite2Abajo,Sprite2Arriba)){
                Log.d("Interseccion" , "2");
                //borde der y borde sup de Sprite1 esta dentro de Sprite2
                if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) && EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {
                    Log.d("Interseccion" , "3");
                    //borde der y borde inf de Sprite1 esta dentro de sprite 2
                    if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) && EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)){
                        Log.d("Interseccion" , "4");
                        Devolver = true;
                    }
                }
            }
        }
        return Devolver;
    }
}
