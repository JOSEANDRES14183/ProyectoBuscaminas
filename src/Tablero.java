import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.Random;
public class Tablero {

    private Casilla casillas[][];
    private int numMinas;
    private Dificultad dificultad;
    private int casillasPorDescubrir;

    public Tablero(Dificultad dificultad) {
        this.dificultad = dificultad;
        casillas = new Casilla[this.dificultad.getFilas()][this.dificultad.getColumnas()];
        numMinas = dificultad.getMinas();
        casillasPorDescubrir= (dificultad.getFilas() * dificultad.getColumnas()) - numMinas;
        this.instanceCells();
    }

    public void completarTablero(int x, int y){
        this.generateMines(x,y);
        this.detectNearbyMines();
    }

    public void generateMines(int x, int y) {
        int filaRand;
        int columnaRand;

        for (int i = 0; i < numMinas; i++) {
            filaRand = getRandomNumber(dificultad.getFilas());
            columnaRand = getRandomNumber(dificultad.getColumnas());

            while (casillas[filaRand][columnaRand].getHasMine() && casillas[filaRand][columnaRand]!=casillas[y][x]) {
                filaRand = getRandomNumber(dificultad.getFilas());
                columnaRand = getRandomNumber(dificultad.getColumnas());
            }

            casillas[filaRand][columnaRand].assignMine();
        }
    }

    public void printTablero() {
        for (int i = 0; i < dificultad.getFilas(); i++) {
            for (int j = 0; j < dificultad.getColumnas(); j++) {
                /*if (casillas[i][j].getCovered()) {
                    System.out.print("■ ");
                }
                if (casillas[i][j].getFlagged()) {
                    System.out.print("▓ ");
                }
                if(!casillas[i][j].getHasMine() && !casillas[i][j].getCovered()){
                    System.out.print(casillas[i][j].getNearbyMines()+" ");
                }*/

               if(!casillas[i][j].getCovered()){
                    System.out.print("C"+" ");
                }
                if(casillas[i][j].getCovered() && !casillas[i][j].getHasMine()){
                    System.out.print(casillas[i][j].getNearbyMines() + " ");
                }
                if(casillas[i][j].getHasMine()){
                    System.out.print("X" + " ");
                }
            }
            System.out.println();
        }
    }

    public void detectNearbyMines() {

        for(int i=0;i< dificultad.getColumnas();i++){
            for(int j=0;j< dificultad.getFilas();j++){
                int k=i;
                int l=j;

                for(k=(i==0) ? 0 : i-1 ; k <= ((k==dificultad.getColumnas()) ? i : i + 1); k++){
                    for(l=(j==0) ? 0 : j-1 ; l <= ((l== dificultad.getFilas()) ? j : j + 1);l++){
                        if(casillas[l][k].getHasMine() && casillas[l][k]!=casillas[j][i]){
                            casillas[j][i].setNearbyMines();
                        }
                    }
                }
            }
        }
    }

    public void uncoverNearbyCells(int x, int y) {
        ArrayList<Casilla> casillasADescubrir = new ArrayList<Casilla>();
        boolean posicionesCeldasYaDescubiertas[][] = new boolean[this.dificultad.getFilas()][this.dificultad.getColumnas()];
        casillasADescubrir.add(casillas[y][x]);
        Posicion posicion;
        Posicion posicionAux;
        int k;
        int j;
        while (casillasADescubrir.size()!=0){

            posicion=getIndexesOfCell(casillasADescubrir.get(0));

            for(Casilla casilla: casillasADescubrir){
                if(casilla.getCovered()){
                    casilla.uncover();
                    posicionAux=getIndexesOfCell(casilla);
                    posicionesCeldasYaDescubiertas[posicionAux.getNumY()][posicionAux.getNumX()]=true;
                }
            }

            if(casillasADescubrir.get(0).getNearbyMines()==0){
                k = posicion.getNumX();
                j = posicion.getNumY();
                for(k=(posicion.getNumX()==0) ? 0 : posicion.getNumX()-1; k <= ((k==dificultad.getColumnas()) ? posicion.getNumX() : posicion.getNumX()+1); k++){
                    for(j=(posicion.getNumY()==0) ? 0 : posicion.getNumY()-1; j<=((j== dificultad.getFilas()) ? posicion.getNumY() : posicion.getNumY()+1); j++){
                        if(casillas[j][k].getNearbyMines()==0 && !casillas[j][k].getHasMine() && casillas[j][k]!=casillasADescubrir.get(0) && !posicionesCeldasYaDescubiertas[j][k]) {
                            casillasADescubrir.add(casillas[j][k]);
                        }
                    }
                }
                casillasADescubrir.remove(0);
            }
        }
    }


    private void instanceCells() {
        for (int i = 0; i < dificultad.getFilas(); i++) {
            for (int j = 0; j < dificultad.getColumnas(); j++) {
                Casilla casilla = new Casilla();
                casillas[i][j] = casilla;
            }
        }
    }

    public int getRandomNumber(int max) {
        Random random = new Random();
        return (int) ((Math.random() * (max - 1)) + 1);
    }

    public Casilla[][] getCasillas() {
        return this.casillas;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    private Posicion getIndexesOfCell(Casilla casilla){
        Posicion posicion = new Posicion();
        for (int i = 0; i < dificultad.getFilas(); i++) {
            for (int j = 0; j < dificultad.getColumnas(); j++) {
                if (casilla == casillas[i][j]) {
                    posicion.setNumX(j);
                    posicion.setNumY(i);
                    return posicion;
                }
            }
        }
        return posicion;
    }

    public int getCasillasPorDescubrir(){
        return this.casillasPorDescubrir;
    }

}
