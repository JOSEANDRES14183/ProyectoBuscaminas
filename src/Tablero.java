import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.Random;
public class Tablero {

    private Casilla casillas[][];
    private int numMinas;
    private Dificultad dificultad;

    public Tablero(Dificultad dificultad) {
        this.dificultad = dificultad;
        casillas = new Casilla[this.dificultad.getFilas()][this.dificultad.getColumnas()];
        numMinas = dificultad.getMinas();
        this.instanceCells();
        this.generateMines();
        //this.printTablero();
        this.detectNearbyMines();
    }

    public void generateMines() {
        int filaRand;
        int columnaRand;

        for (int i = 0; i < numMinas; i++) {
            filaRand = getRandomNumber(dificultad.getFilas());
            columnaRand = getRandomNumber(dificultad.getColumnas());

            while (casillas[filaRand][columnaRand].getHasMine()) {
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
                }*/
                if (casillas[i][j].getFlagged()) {
                    System.out.print("▓ ");
                }
                /*if(!casillas[i][j].getHasMine() && !casillas[i][j].getCovered()){
                    System.out.print(casillas[i][j].getNearbyMines()+" ");
                }*/
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
        casillasADescubrir.add(casillas[y][x]);
        Posicion posicion = new Posicion(x,y);
        int k;
        int j;
        while (casillasADescubrir.get(0)==null){
            casillasADescubrir.remove(0);
            if(casillasADescubrir.get(0).getCovered() && !casillasADescubrir.get(0).getFlagged()){
                casillasADescubrir.get(0).uncover();
            }
            if(casillasADescubrir.get(0).getNearbyMines()==0 && !casillasADescubrir.get(0).getCovered()){
              k = posicion.getNumX();
              j = posicion.getNumY();
              for(k=(x==0) ? 0 : x-1; k <= ((k==dificultad.getColumnas()) ? x : x+1); k++){
                  for(j=(y==0) ? 0 : y-1; j<=((j== dificultad.getFilas()) ? y : y+1); j++){
                      if(casillas[j][k].getNearbyMines()==0 && !casillas[j][k].getHasMine()){
                          casillasADescubrir.add(casillas[j][k]);
                      }
                  }
              }
            }
        }

       /* ArrayList<Casilla> uncoveredCells = new ArrayList();

        if (y == 0 && x == 0) {
            if (casillas[y + 1][x].getNearbyMines() == 0 && !casillas[y + 1][x].getHasMine() && casillas[y + 1][x].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x]);
            }
            if (casillas[y][x + 1].getNearbyMines() == 0 && !casillas[y][x + 1].getHasMine() && casillas[y][x + 1].getCovered()) {
                uncoveredCells.add(casillas[y][x + 1]);
            }
            if (casillas[y + 1][x + 1].getNearbyMines() == 0 && !casillas[y + 1][x + 1].getHasMine() && casillas[y + 1][x + 1].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x + 1]);
            }
        }

        if (y == 0 && x != 0 && x != dificultad.getColumnas() - 1) {
            if (casillas[y][x + 1].getNearbyMines() == 0 && !casillas[y][x + 1].getHasMine() && casillas[y][x + 1].getCovered()) {
                uncoveredCells.add(casillas[y][x + 1]);
            }
            if (casillas[y][x - 1].getNearbyMines() == 0 && !casillas[y][x - 1].getHasMine() && casillas[y][x - 1].getCovered()) {
                uncoveredCells.add(casillas[y][x - 1]);
            }
            if (casillas[y + 1][x].getNearbyMines() == 0 && !casillas[y + 1][x].getHasMine() && casillas[y + 1][x].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x]);
            }
            if (casillas[y + 1][x - 1].getNearbyMines() == 0 && !casillas[y + 1][x - 1].getHasMine() && casillas[y + 1][x - 1].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x - 1]);
            }
            if (casillas[y + 1][x + 1].getNearbyMines() == 0 && !casillas[y + 1][x + 1].getHasMine() && casillas[y + 1][x + 1].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x + 1]);
            }
        }

        /*if (y != 0 && y != dificultad.getFilas() - 1 && x == 0) {
            if (casillas[y + 1][x].getNearbyMines() == 0 && !casillas[y + 1][x].getHasMine() && casillas[y + 1][x].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x]);
            }
            if (casillas[y - 1][x].getNearbyMines() == 0 && !casillas[y - 1][x].getHasMine() && casillas[y - 1][x].getCovered()) {
                uncoveredCells.add(casillas[y - 1][x]);
            }
            if (casillas[y + 1][x + 1].getHasMine()) {
                nearbyMines++;
            }
            if (casillas[y - 1][x + 1].getHasMine()) {
                nearbyMines++;
            }
            if (casillas[y][x + 1].getHasMine()) {
                nearbyMines++;
            }
            specialCell = true;
        }


        if (casillas[y - 1][x].getNearbyMines() == 0 && !casillas[y - 1][x].getHasMine() && casillas[y - 1][x].getCovered()) {
            uncoveredCells.add(casillas[y - 1][x]);
        }
        if (casillas[y + 1][x].getNearbyMines() == 0 && !casillas[y + 1][x].getHasMine() && casillas[y + 1][x].getCovered()) {
            uncoveredCells.add(casillas[y + 1][x]);
        }
        if (casillas[y][x + 1].getNearbyMines() == 0 && !casillas[y][x + 1].getHasMine() && casillas[y][x + 1].getCovered()) {
            uncoveredCells.add(casillas[y][x + 1]);
        }
        if (casillas[y][x - 1].getNearbyMines() == 0 && !casillas[y][x - 1].getHasMine() && casillas[y][x - 1].getCovered()) {
            uncoveredCells.add(casillas[y][x - 1]);
        }
        if (casillas[y - 1][x - 1].getNearbyMines() == 0 && !casillas[y - 1][x - 1].getHasMine() && casillas[y - 1][x - 1].getCovered()) {
            uncoveredCells.add(casillas[y - 1][x - 1]);
        }
        if (casillas[y - 1][x + 1].getNearbyMines() == 0 && !casillas[y - 1][x + 1].getHasMine() && casillas[y - 1][x + 1].getCovered()) {
            uncoveredCells.add(casillas[y - 1][x + 1]);
        }
        if (casillas[y + 1][x - 1].getNearbyMines() == 0 && !casillas[y + 1][x - 1].getHasMine() && casillas[y + 1][x + 1].getCovered()) {
            uncoveredCells.add(casillas[y + 1][x - 1]);
        }
        if (casillas[y + 1][x + 1].getNearbyMines() == 0 && !casillas[y + 1][x + 1].getHasMine() && casillas[y + 1][x + 1].getCovered()) {
            uncoveredCells.add(casillas[y + 1][x + 1]);
        }

        for (Casilla cell : uncoveredCells) {
            cell.uncover();
            uncoverNearbyCells(getXOfCell(cell), getYOfCell(cell));
        }*/
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

}
