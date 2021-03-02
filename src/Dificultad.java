public enum Dificultad {

    FACIL(8,8,10),
    MEDIA(16,16,40),
    DIFICIL(16,30,99);

    private int filas;
    private int columnas;
    private int minas;

    private Dificultad(int filas, int columnas, int minas){
        this.filas=filas;
        this.columnas=columnas;
        this.minas=minas;
    }

    public int getFilas(){
        return filas;
    }

    public int getColumnas(){
        return columnas;
    }

    public int getMinas(){
        return minas;
    }


}
