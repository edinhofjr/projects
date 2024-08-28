import java.math.BigDecimal;
import java.util.Scanner;
import static java.lang.System.exit;
import java.sql.*;

public class Banking {
    DatabaseConnection database;
    Scanner sc = new Scanner(System.in);
    Account account;

    public static void main(String[] args) throws SQLException {
        Banking banking = new Banking();
    }

    Banking() throws SQLException {
        database = new DatabaseConnection("jdbc:mysql://localhost:3306/banking", "root", "2005");

        while (true) {
            byte c;

            System.out.println("1 - Entrar");
            System.out.println("2 - Cadastrar");
            System.out.println("3 - Sair");
            c = sc.nextByte();
            sc.nextLine();

            switch (c) {
                case 1:
                    if (logon()) {menu();}
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    database.disconnect();
                    exit(0);
                default:
                    System.out.println("Valor incorreto.");
                    break;
            }
        }
    }

    private boolean logon() throws SQLException {
        String cpf, pw;
        System.out.println("Insira seu ID: ");
        cpf = sc.nextLine();
        System.out.println("Insira sua Senha: ");
        pw = sc.nextLine();

        if (database.AUTH(cpf, pw)) {
            System.out.println("Login com sucesso!");
            account = database.getAccount(cpf);
            return true;
        } else {
            System.out.println("Login ou Senha incorreta!");
            return false;
        }
    }

    private void register() throws SQLException {
        CPF cpf;
        String pw;
        String pw2;

        do {
            System.out.println("Insira seu CPF: ");
            cpf = new CPF(sc.nextLine());
        } while(database.userExists(cpf));

        System.out.println("Insira a senha: ");
        pw = sc.nextLine();
        System.out.println("Confirme a senha: ");
        pw2 = sc.nextLine();

        while (!pw.equals(pw2)) {
            System.out.println("AS SENHAS NÃO SÃO IGUAIS!");

            System.out.println("Insira a senha: ");
            pw = sc.nextLine();
            System.out.println("Confirme a senha: ");
            pw2 = sc.nextLine();
        }
        database.createAccount(cpf, pw);
    }

    private void menu() throws SQLException {
        while(true) {
            byte c;
            System.out.println("1 - Saldo");
            System.out.println("2 - Deposito");
            System.out.println("3 - Saque");
            System.out.println("4 - Transferir");
            System.out.println("0 - Sair");
            c = sc.nextByte();
            sc.nextLine();
            BigDecimal value;

            switch (c) {
                case 1:
                    System.out.println(account.balance);
                    break;
                case 2:
                    System.out.println("Insira o valor a ser depositado: ");
                    value = sc.nextBigDecimal();
                    account.deposit(value);
                    database.updateAccount(account);
                    break;
                case 3:
                    System.out.println("Insira o valor a ser sacado: ");
                    value = sc.nextBigDecimal();
                    account.withdraw(value);
                    database.updateAccount(account);
                    break;
                case 4:
                    System.out.println("Insira o valor a ser transferido");
                    value = sc.nextBigDecimal();
                    sc.nextLine();

                    System.out.println("Insira o CPF de destino");
                    CPF cpf = new CPF(sc.nextLine());
                    database.transfer(account, value, cpf);
                    break;
                case 0:
                    account = null;
                    return;
            }
        }
    }
}
