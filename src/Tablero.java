import java.util.ArrayList;
import java.util.Random;
public class Tablero {

    private Casilla casillas[][];
    private int minas;
    private Dificultad dificultad;

    public Tablero(Dificultad dificultad){
        this.dificultad=dificultad;
        casillas = new Casilla [this.dificultad.getFilas()][this.dificultad.getColumnas()];
        minas = dificultad.getMinas();
        this.instanceCells();
        this.generateMines();
        this.detectNearbyMines();
    }

    public void generateMines(){
        int filaRand;
        int columnaRand;

        for (int i = 0; i < minas; i++) {
            filaRand = getRandomNumber(dificultad.getFilas());
            columnaRand = getRandomNumber(dificultad.getColumnas());

            while(casillas[filaRand][columnaRand].getHasMine()){
                filaRand = getRandomNumber(dificultad.getFilas());
                columnaRand = getRandomNumber(dificultad.getColumnas());
            }

            casillas[filaRand][columnaRand].assignMine();
        }
    }

    public void printTablero(){
        for(int i=0;i < dificultad.getFilas(); i++){
            for(int j=0;j < dificultad.getColumnas();j++){
                if(casillas[i][j].getCovered()){
                    System.out.print("■ ");
                }
                if(casillas[i][j].getFlagged()){
                    System.out.print("▓ ");
                }
                if(!casillas[i][j].getCovered()){
                    System.out.print("  ");
                }
                /*if(casillas[i][j].getCovered()){
                    System.out.print(casillas[i][j].getNearbyMines() + " ");
                }
                if(casillas[i][j].getHasMine()){
                    System.out.print("X" + " ");
                }*/
            }
            System.out.println();
        }
    }

    public void detectNearbyMines(){
        int nearbyMines=0;
        boolean specialCell=false;

        for(int i=0;i< dificultad.getFilas();i++){
            for(int j=0;j< dificultad.getColumnas();j++) {

                if (i == 0 && j == 0) {
                    if (casillas[i + 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i + 1][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    specialCell=true;
                }
                if (i == 0 && j != 0 && j != dificultad.getColumnas() - 1) {
                    if (casillas[i][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i + 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i + 1][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i + 1][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    specialCell=true;
                }

                if (i != 0 && i != dificultad.getFilas() - 1 && j == 0) {
                    if (casillas[i + 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i + 1][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if(casillas[i][j + 1].getHasMine()){
                        nearbyMines++;
                    }
                    specialCell=true;
                }

                if (i == dificultad.getFilas() - 1 && j == 0) {
                    if (casillas[i - 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    specialCell=true;
                }

                if (i == 0 && j == dificultad.getColumnas() - 1) {
                    if (casillas[i + 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i + 1][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    specialCell=true;
                }

                if (i != 0 && i != dificultad.getFilas() - 1 && j == dificultad.getColumnas() - 1) {
                    if (casillas[i + 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i + 1][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    specialCell=true;
                }

                if (i == dificultad.getFilas() - 1 && j == dificultad.getColumnas() - 1) {
                    if (casillas[i - 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    specialCell=true;
                }

                if (i == dificultad.getFilas() - 1 && j != 0 && j != dificultad.getColumnas() - 1) {
                    if (casillas[i][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    specialCell=true;
                }

                if(!specialCell) {
                    if (casillas[i - 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i + 1][j].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i + 1][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j - 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i - 1][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                    if (casillas[i + 1][j + 1].getHasMine()) {
                        nearbyMines++;
                    }
                }
                casillas[i][j].setNearbyMines(nearbyMines);
                nearbyMines = 0;
                specialCell=false;
            }
        }

    }

    public void uncoverNearbyCells(int x, int y){

        ArrayList <Casilla> uncoveredCells = new ArrayList();

        if (y == 0 && x == 0) {
            if (casillas[y+1][x].getNearbyMines()==0 && !casillas[y + 1][x].getHasMine() && casillas[y+1][x].getCovered()) {
                uncoveredCells.add(casillas[y+1][x]);
            }
            if (casillas[y][x + 1].getNearbyMines()==0 && !casillas[y][x + 1].getHasMine() && casillas[y][x + 1].getCovered()) {
                uncoveredCells.add(casillas[y][x + 1]);
            }
            if (casillas[y + 1][x + 1].getNearbyMines()==0 && !casillas[y + 1][x + 1].getHasMine() && casillas[y + 1][x + 1].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x + 1]);
            }
        }

        if (y == 0 && x != 0 && x != dificultad.getColumnas() - 1) {
            if (casillas[y][x + 1].getNearbyMines()==0 && !casillas[y][x + 1].getHasMine() && casillas[y][x + 1].getCovered()) {
                uncoveredCells.add(casillas[y][x + 1]);
            }
            if (casillas[y][x - 1].getNearbyMines()==0 && !casillas[y][x - 1].getHasMine() && casillas[y][x - 1].getCovered()) {
                uncoveredCells.add(casillas[y][x - 1]);
            }
            if (casillas[y + 1][x].getNearbyMines()==0 && !casillas[y + 1][x].getHasMine() && casillas[y + 1][x].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x]);
            }
            if (casillas[y + 1][x - 1].getNearbyMines()==0 && !casillas[y + 1][x - 1].getHasMine() && casillas[y + 1][x - 1].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x - 1]);
            }
            if (casillas[y + 1][x + 1].getNearbyMines()==0 && !casillas[y + 1][x + 1].getHasMine() && casillas[y + 1][x + 1].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x + 1]);
            }
        }

        if (y != 0 && y != dificultad.getFilas() - 1 && x == 0) {
            if (casillas[y + 1][x].getNearbyMines()==0 && !casillas[y + 1][x].getHasMine() && casillas[y + 1][x].getCovered()) {
                uncoveredCells.add(casillas[y + 1][x]);
            }
            if (casillas[y - 1][x].getNearbyMines()==0 && !casillas[y - 1][x].getHasMine() && casillas[y - 1][x].getCovered()) {
                uncoveredCells.add(casillas[y - 1][x]);
            }
            if (casillas[y + 1][x + 1].getHasMine()) {
                nearbyMines++;
            }
            if (casillas[y - 1][x + 1].getHasMine()) {
                nearbyMines++;
            }
            if(casillas[y][x + 1].getHasMine()){
                nearbyMines++;
            }
            specialCell=true;
        }


        if(casillas[y-1][x].getNearbyMines()==0 && !casillas[y-1][x].getHasMine() && casillas[y-1][x].getCovered()){
            uncoveredCells.add(casillas[y-1][x]);
        }
        if(casillas[y+1][x].getNearbyMines()==0 && !casillas[y+1][x].getHasMine() && casillas[y+1][x].getCovered()){
            uncoveredCells.add(casillas[y+1][x]);
        }
        if(casillas[y][x+1].getNearbyMines()==0 && !casillas[y][x+1].getHasMine() && casillas[y][x+1].getCovered()){
            uncoveredCells.add(casillas[y][x+1]);
        }
        if(casillas[y][x-1].getNearbyMines()==0 && !casillas[y][x-1].getHasMine() && casillas[y][x-1].getCovered()){
            uncoveredCells.add(casillas[y][x-1]);
        }
        if(casillas[y-1][x-1].getNearbyMines()==0 && !casillas[y-1][x-1].getHasMine() && casillas[y-1][x-1].getCovered()){
            uncoveredCells.add(casillas[y-1][x-1]);
        }
        if(casillas[y-1][x+1].getNearbyMines()==0 && !casillas[y-1][x+1].getHasMine() && casillas[y-1][x+1].getCovered()){
            uncoveredCells.add(casillas[y-1][x+1]);
        }
        if(casillas[y+1][x-1].getNearbyMines()==0 && !casillas[y+1][x-1].getHasMine() && casillas[y+1][x+1].getCovered()){
            uncoveredCells.add(casillas[y+1][x-1]);
        }
        if(casillas[y+1][x+1].getNearbyMines()==0 && !casillas[y+1][x+1].getHasMine() && casillas[y+1][x+1].getCovered()){
            uncoveredCells.add(casillas[y+1][x+1]);
        }

        for(Casilla cell:uncoveredCells){
            cell.uncover();
            uncoverNearbyCells(getXOfCell(cell),getYOfCell(cell));
        }
    }



    private void instanceCells(){
        for(int i=0;i< dificultad.getFilas();i++){
            for(int j=0;j< dificultad.getColumnas();j++){
                Casilla casilla = new Casilla();
                casillas[i][j]= casilla;
            }
        }
    }

    public int getRandomNumber(int max) {
        Random random = new Random();
        return (int) ((Math.random() * (max - 1)) + 1);
    }

    public Casilla[][] getCasillas(){
        return this.casillas;
    }

    public void setCasillas(Casilla[][] casillas){
        this.casillas=casillas;
    }

    private int getXOfCell(Casilla cell){
        int XOfCell=0;
        for(int i=0;i< dificultad.getFilas();i++){
            for(int j=0;i< dificultad.getColumnas();j++){
                if(cell==casillas[i][j]){
                    XOfCell=j;
                }
            }
        }
        return XOfCell;
    }

    private int getYOfCell(Casilla cell){
        int YOfCell=0;
        for(int i=0;i< dificultad.getFilas();i++){
            for(int j=0;i< dificultad.getColumnas();j++){
                if(cell==casillas[i][j]){
                    YOfCell=i;
                }
            }
        }
        return YOfCell;
    }


}
