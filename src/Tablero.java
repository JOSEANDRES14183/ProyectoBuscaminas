import java.util.Random;
public class Tablero {

    private int tamaño[][];
    private int minas;
    private Dificultad dificultad;

    public Tablero(Dificultad dificultad){
        this.dificultad=dificultad;
        tamaño = new int [this.dificultad.getFilas()][this.dificultad.getColumnas()];
        minas = dificultad.getMinas();
    }


    public void generarMinas(){
        Random random = new Random();
        for(int i=0;i<minas;i++){

        }
    }
}
