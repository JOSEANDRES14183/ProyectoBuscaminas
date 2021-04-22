public class Posicion {

    private int numX;
    private int numY;

    public Posicion (int numX, int numY){
        this.numX = numX;
        this.numY = numY;
    }

    public Posicion(){}

    public int getNumX(){
        return this.numX;
    }

    public int getNumY(){
        return this.numY;
    }

    public void setNumX(int x){this.numX=x;}

    public void setNumY(int y){this.numY=y;}

    public void setPosicion(int x, int y){
        this.numX=x;
        this.numY=y;
    }
}
