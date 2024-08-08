import java.util.Arrays;

public class Palavra {
    private Letra[] letras;
    private char[] palavrachar;
    private int size;

    Palavra(String input) {
        palavrachar = input.toCharArray();
        size = input.length();

        letras = new Letra[size];

        for (int i = 0; i < palavrachar.length; i++) {
            letras[i] = new Letra(palavrachar[i]);
        }

    }

    boolean guess(char c) {
        boolean in = false;
        for (int i = 0; i < size; i++) {
            if (Character.toLowerCase(letras[i].value) == Character.toLowerCase(c) && !(letras[i].guessed)) {
                letras[i].guess();
                in = true;
            }
        }
        return in;
    }

    boolean isguessed() {
        for (int i = 0; i < size; i++) {
            if (!(letras[i].guessed)) {
                return false;
            }
        }
        return true;
    }

    void print() {
        for (int i = 0; i < size; i++) {
            if (letras[i].guessed) {
                System.out.print(letras[i].value);
            } else {
                System.out.print("_");
            }
        }
    }

    String word() {
        return new String(palavrachar);
    }
}
