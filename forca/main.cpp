#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

class Letra {
    char value;
    bool guessed = false;
public:
    Letra() : value('\0') {};

    void setvalue(char word) {
        value = word;
    }
    char getvalue() {
        return tolower(value);
    }

    void guess() {
        guessed = true;
    }
    bool isguessed() {
        return guessed;
    }
};

class Palavra {
    Letra* letras;
    int size;

public:
    ~Palavra() {
        delete[] letras;
    }

    Palavra(std::string palavra) {
        size = palavra.size();
        letras = new Letra[size];

        for(int i = 0; i < size; i++) {
            letras[i].setvalue(palavra[i]);
        }
    }

    void print() {
        for(int i = 0; i < size; i++) {
            if (!letras[i].isguessed()) {
                std::cout << "_";
            } else {
                std::cout << letras[i].getvalue();
            }
        }
    }

    bool isguessed() {
        for(int i = 0; i < size; i++) {
            if (!letras[i].isguessed()) {
                return false;
            }
        }
        return true;
    }

    bool inword(char c) {
        for(int i = 0; i < size; i++) {
            if(letras[i].getvalue() == c) {
                return true;
            }
        }
        return false;
    }

    void guess(char c) {
        for(int i = 0; i < size; i++) {
            if(letras[i].getvalue() == c) {
                letras[i].guess();
            }
        }
    }
};

class Game {
    int lifes = 3;
    std::vector<char> guessedchars;
    int wins = 0;
    int loses = 0;

public:
    Game() {
        while (true) {
            int c = -1;
            std::system("clear");
            std::cout << "TERMINAL" << "\n";
            std::cout << "1 - Iniciar jogo." << "\n";
            std::cout << "0 - Sair." << "\n";
            std::cin >> c;


            switch (c) {
                case 1:
                    start();
                    break;
                case 0:
                    exit(0);
                default:
                    std::cout << "Insira um valor válido!" << "\n";
            }
        }
    }

    void start() {
        std::string s;
        std::cout << "Insira uma palavra para iniciar: ";
        std::cin >> s;
        Palavra palavra(s);

        while (!palavra.isguessed() && lifes != 0) {

            char letra;

            do {
                std::system("clear");
                palavra.print();
                std::cout << '\n';
                std::cout << "Vidas restantes: " << lifes << "\n";
                std::cout << "Chute uma letra: ";
                std::cin >> letra;
                letra = tolower(letra);
            } while (std::find(guessedchars.begin(), guessedchars.end(), letra) != guessedchars.end());

            if (!palavra.inword(letra)) {
                lifes--;
            } else {
                palavra.guess(letra);
            }
            guessedchars.push_back(letra);
        }

        if (palavra.isguessed()) {
            std::system("clear");
            std::cout << "GANHOU O JOGO, a palavra era: ";
            palavra.print();
            wins++;
        } else {
            std::system("clear");
            std::cout << "PERDEU O JOGO, a palavra era: ";
            palavra.print();
            loses++;
        }
        std::cin.ignore();
        std::cin.get();

        guessedchars.clear();
        lifes = 3;
    }
};

int main() {
    Game game;
}
