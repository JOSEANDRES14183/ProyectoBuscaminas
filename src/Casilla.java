public class Casilla {

    private boolean hasMine;
    private boolean covered;
    private boolean flagged;
    private int nearbyMines;

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
        this.covered=false;
    }

    public void unflag(){
        this.flagged=false;
        this.covered=true;
    }

    public boolean isFlagged(){
        return this.flagged;
    }

    public void assignMine(){
        hasMine=true;
    }

    public boolean getHasMine(){
        return this.hasMine;
    }

    public boolean getCovered(){
        return this.covered;
    }

    public boolean getFlagged(){
        return this.flagged;
    }

    public void setNearbyMines(int nearbyMines){
        this.nearbyMines=nearbyMines;
    }

    public int getNearbyMines(){return this.nearbyMines;}

}
