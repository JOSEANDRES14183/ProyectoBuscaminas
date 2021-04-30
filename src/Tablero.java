import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.Random;
public class Tablero {

    private Casilla casillas[][];
    private int filas;
    private int columnas;
    private int numMinas;
    private Dificultad dificultad;
    private int casillasPorDescubrir;

    public Tablero(Dificultad dificultad) {
        this.dificultad = dificultad;
        this.filas = dificultad.getFilas();
        this.columnas = dificultad.getColumnas();
        casillas = new Casilla[filas][columnas];
        numMinas = dificultad.getMinas();
        casillasPorDescubrir= (this.filas * this.columnas) - this.numMinas;
        this.instanceCells();
    }

    public Tablero(int filas, int columnas, int minas){
        this.filas=filas;
        this.columnas=columnas;
        casillas = new Casilla[filas][columnas];
        numMinas=minas;
        casillasPorDescubrir= (this.filas * this.columnas) - this.numMinas;
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
            filaRand = getRandomNumber(this.filas);
            columnaRand = getRandomNumber(this.columnas);

            while (casillas[filaRand][columnaRand].getHasMine() && casillas[filaRand][columnaRand]!=casillas[y][x]) {
                filaRand = getRandomNumber(this.filas);
                columnaRand = getRandomNumber(this.columnas);
            }

            casillas[filaRand][columnaRand].assignMine();
        }
    }

    public void printTablero() {
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
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
                    if(casillas[i][j].getNearbyMines()==0){
                        System.out.print(Colores.YELLOW + casillas[i][j].getNearbyMines() + " " + Colores.RESET);
                    }
                    if(casillas[i][j].getNearbyMines()==1){
                        System.out.print(Colores.BLUE + casillas[i][j].getNearbyMines() + " " + Colores.RESET);
                    }
                    if(casillas[i][j].getNearbyMines()==2){
                        System.out.print(Colores.GREEN + casillas[i][j].getNearbyMines() + " " + Colores.RESET);
                    }
                    if(casillas[i][j].getNearbyMines()==3){
                        System.out.print(Colores.RED + casillas[i][j].getNearbyMines() + " " + Colores.RESET);
                    }
                    if(casillas[i][j].getNearbyMines()==4){
                        System.out.print(Colores.BLUE_BOLD + casillas[i][j].getNearbyMines() + " " + Colores.RESET);
                    }
                    if(casillas[i][j].getNearbyMines()==5){
                        System.out.print(Colores.CYAN + casillas[i][j].getNearbyMines() + " " + Colores.RESET);
                    }
                    if(casillas[i][j].getNearbyMines()==6){
                        System.out.print(Colores.PURPLE + casillas[i][j].getNearbyMines() + " " + Colores.RESET);
                    }
                    if(casillas[i][j].getNearbyMines()==7){
                        System.out.print(Colores.WHITE + casillas[i][j].getNearbyMines() + " " + Colores.RESET);
                    }
                    if(casillas[i][j].getNearbyMines()==8){
                        System.out.print(Colores.BLACK + casillas[i][j].getNearbyMines() + " " + Colores.RESET);
                    }
                    if(casillas[i][j].getNearbyMines()==9){
                        System.out.print(Colores.YELLOW_BOLD + casillas[i][j].getNearbyMines() + " " + Colores.RESET);
                    }
                }
                if(casillas[i][j].getHasMine()){
                    System.out.print("X" + " ");
                }
            }
            System.out.println();
        }
    }

    public void detectNearbyMines() {

        for(int i=0;i< this.columnas;i++){
            for(int j=0;j< this.filas;j++){
                int k=i;
                int l=j;

                for(k=(i==0) ? 0 : i-1 ; k <= ((k==this.columnas) ? i : i + 1); k++){
                    for(l=(j==0) ? 0 : j-1 ; l <= ((l==this.filas) ? j : j + 1);l++){
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
        boolean posicionesCeldasYaDescubiertas[][] = new boolean[this.filas][this.columnas];
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
                for(k=(posicion.getNumX()==0) ? 0 : posicion.getNumX()-1; k <= ((k==this.columnas) ? posicion.getNumX() : posicion.getNumX()+1); k++){
                    for(j=(posicion.getNumY()==0) ? 0 : posicion.getNumY()-1; j<=((j==this.filas) ? posicion.getNumY() : posicion.getNumY()+1); j++){
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
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
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
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
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

    public void reducirCasillasPorDescubrir(){
        this.casillasPorDescubrir--;
    }

}
