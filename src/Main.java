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
        tablero.printTablero();

    }
}
