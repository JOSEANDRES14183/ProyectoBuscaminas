import java.util.Scanner;
public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[]args){
        System.out.println("Elige una dificultad:");
        System.out.println("1. Fácil");
        System.out.println("2. Normal");
        System.out.println("3. Difícil");
        System.out.println("4. Personalizada");
        int opcDif=sc.nextInt();
        sc.nextLine();
        Tablero tablero;
        boolean primerDescubrumiento=false;

        switch (opcDif){
            case 1:
                tablero = new Tablero(Dificultad.FACIL);
                break;
            case 2:
                tablero = new Tablero(Dificultad.MEDIA);
                break;
            case 3:
                tablero = new Tablero(Dificultad.DIFICIL);
                break;
            case 4:
                // CUIDADO. Aquí hay que tener en cuenta errores, ej: mas minas que casillas, valores negativos, etc.
                System.out.println("Establece el número de filas");
                int filasCustom=sc.nextInt();
                sc.nextLine();
                System.out.println("Establece el número de columnas");
                int columnasCustom=sc.nextInt();
                sc.nextLine();
                System.out.println("Establece el número de minas");
                int minasCustom=sc.nextInt();
                sc.nextLine();

            default:
                tablero = new Tablero(Dificultad.MEDIA);
                break;
        }

        while(tablero.getCasillasPorDescubrir()>0) {
            tablero.printTablero();
            System.out.println("");
            System.out.println("¿Qué acción quieres realizar?");
            System.out.println("1- Descubrir una celda");
            System.out.println("2- Poner una bandera");
            int opc = sc.nextInt();
            sc.nextLine();
            System.out.println("Introduce las coordenadas de la celda:");
            System.out.println("X:");
            int axisX = sc.nextInt();
            sc.nextLine();
            System.out.println("Y:");
            int axisY = sc.nextInt();
            sc.nextLine();

            if (opc==1){
                if(!primerDescubrumiento){
                    tablero.completarTablero(axisX,axisY);
                    primerDescubrumiento=true;
                }

                if(tablero.getCasillas()[axisY][axisX].getHasMine()){
                    //tablero.youLose
                } else if(tablero.getCasillas()[axisY][axisX].getNearbyMines()!=0){
                    tablero.getCasillas()[axisY][axisX].uncover();
                }
                else {
                    tablero.uncoverNearbyCells(axisX,axisY);
                }
            }

            if(opc==2){
                if(tablero.getCasillas()[axisY][axisX].isFlagged()){
                    tablero.getCasillas()[axisY][axisX].unflag();
                } else {
                    tablero.getCasillas()[axisY][axisX].flag();
                }
            }

        }

        //tablero.youWin
    }

}
