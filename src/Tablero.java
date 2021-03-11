import java.util.Random;
public class Tablero {

    private Casilla casillas[][];
    private int minas;
    private Dificultad dificultad;

    public Tablero(Dificultad dificultad){
        this.dificultad=dificultad;
        casillas = new Casilla [this.dificultad.getFilas()][this.dificultad.getColumnas()];
        minas = dificultad.getMinas();
    }


    public void generarMinas(){
        for(int i=0;i<minas;i++){
            casillas[getRandomNumber(dificultad.getFilas())][getRandomNumber(dificultad.getColumnas())].assignMine();
        }
    }

    public int getRandomNumber(int max){
        Random random = new Random();
        return (int)((Math.random()*(max - 1)) + 1);
    }
}
