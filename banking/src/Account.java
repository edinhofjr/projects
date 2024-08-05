import java.math.BigDecimal;

public class Account {
    CPF cpf;
    BigDecimal balance;

    public Account(String cpf, BigDecimal balance) {
        this.cpf = new CPF(cpf);
        this.balance = balance;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
        } else {
            System.out.println("Valor insuficiente para retirada.");
        }
    }

    public void transfer(BigDecimal amount, Account destination) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            destination.deposit(amount);
        }
    }

    String getcpf() {
        return cpf.getcpf();
    }
}
