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
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void detectNearbyMines(){
        int nearbyMines=0;
        boolean outOfBounds=false;
        for(int i=0;i< dificultad.getFilas();i++){
            for(int j=0;j< dificultad.getColumnas();j++) {
                if (casillas[i - 1][j].getHasMine() && i-1!=-1) {
                    nearbyMines++;
                }
                if (casillas[i + 1][j].getHasMine() && i+1!= dificultad.getFilas()) {
                    nearbyMines++;
                }
                if (casillas[i][j - 1].getHasMine() && j-1!=-1) {
                    nearbyMines++;
                }
                if (casillas[i][j + 1].getHasMine() && j+1!=dificultad.getColumnas()) {
                    nearbyMines++;
                }
                if (casillas[i + 1][j - 1].getHasMine() && i+1!= dificultad.getFilas() && j-1!=-1) {
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
                casillas[i][j].setNearbyMines(nearbyMines);
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
