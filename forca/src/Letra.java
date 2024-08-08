public class Letra {
    char value;
    boolean guessed = false;

    Letra(char value) {
        this.value = value;
    }

    void guess() {
        guessed = true;
    }
}
