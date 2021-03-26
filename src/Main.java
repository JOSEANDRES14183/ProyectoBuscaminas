import java.util.Scanner;
public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[]args){
        System.out.println("Elige una dificultad:");
        System.out.println("1. Fácil");
        System.out.println("2. Normal");
        System.out.println("3. Difícil");
        int opcDif=sc.nextInt();
        sc.nextLine();
        Tablero tablero;

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
            default:
                tablero = new Tablero(Dificultad.MEDIA);
                break;
        }

        while(true) {
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
                tablero.getCasillas()[axisY][axisX].uncover();
                tablero.uncoverNearbyCells(axisX,axisY);
            }
            if(opc==2){
                if(tablero.getCasillas()[axisY][axisX].isFlagged()){
                    tablero.getCasillas()[axisY][axisX].unflag();
                } else {
                    tablero.getCasillas()[axisY][axisX].flag();
                }
            }
        }
    }

}
