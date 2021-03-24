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
        //this.printTablero();
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
            casillas[filaRand][columnaRand].uncover();
        }
    }

    public void printTablero(){
        for(int i=0;i < dificultad.getFilas(); i++){
            for(int j=0;j < dificultad.getColumnas();j++){
                /*if(casillas[i][j].getCovered()){
                    System.out.print("■ ");
                }
                if(casillas[i][j].getFlagged()){
                    System.out.print("▓ ");
                }
                if(!casillas[i][j].getCovered()){
                    System.out.print(" ");
                }*/
                if(casillas[i][j].getCovered()){
                    System.out.print(casillas[i][j].getNearbyMines() + " ");
                }
                if(casillas[i][j].getHasMine()){
                    System.out.print("X" + " ");
                }
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

    private void instanceCells(){
        for(int i=0;i< dificultad.getFilas();i++){
            for(int j=0;j< dificultad.getColumnas();j++){
                Casilla casilla = new Casilla();
                casillas[i][j]= casilla;
            }
        }
    }

    public int getRandomNumber(int max){
        Random random = new Random();
        return (int)((Math.random()*(max - 1)) + 1);
    }


}
