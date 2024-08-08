import java.util.Scanner;

public class Forca {
    public static void main(String[] args) {
        int life = 3;
        System.out.println("Insira uma palavra: ");
        Scanner in = new Scanner(System.in);
        Palavra p = new Palavra(in.nextLine());
        p.print();

        while (life > 0 && !(p.isguessed())) {
            System.out.println();
            System.out.println("Insira uma letra: ");

            if (!(p.guess(in.next().charAt(0)))) {
                System.out.println("Essa letra não está na palavra.");
                life--;
            }

            if (life == 0) {
                System.out.println("As vidas acabaram");
                System.out.println("A palavra era: " + p.word());
            }

            if (p.isguessed()) {
                System.out.println("GANHOU O JOGO.");
                System.out.println("A PALAVRA ERA: " + p.word());
            } else {
                p.print();
            }
        }
    }
}
