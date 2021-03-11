public class Casilla {

    private boolean hasMine;
    private boolean covered;
    private boolean flagged;

    public Casilla(){
        this.hasMine=false;
        this.covered=true;
        this.flagged=false;
    }

    public void uncover(){
        if(hasMine){
            //Aquí se llamará a un método para perder la partida.
            //Otros.youLose;
        }
        if(this.covered){
            covered=false;
            //Metodo para hacer uncover a las celdas cercanas que no tengan minas
        }
    }

    public void flag(){
        this.flagged=true;
    }

    public void unflag(){
        this.flagged=false;
    }

    public void assignMine(){
        hasMine=true;
    }

}
