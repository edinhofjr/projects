import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;

public class DatabaseConnection {
    Connection connection;

    DatabaseConnection(String host, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(host, user, password);
    }

    boolean AUTH(String cpf, String pw) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE CPF = ? AND PASSWORD = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.setString(2, pw);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

    boolean userExists(CPF cpf) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE CPF = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf.getcpf());
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

    void createAccount(CPF cpf, String pw) throws SQLException {
        String sql = "INSERT INTO accounts (CPF, password) VALUES (?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf.getcpf());
        statement.setString(2, pw);
        statement.execute();
    }

    Account getAccount(String cpf) throws SQLException {
        String sql = "SELECT CPF, balance FROM accounts WHERE CPF = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cpf);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return new Account(rs.getString(1), rs.getBigDecimal(2));
        } else {
            return null;
        }
    }

    void updateAccount(Account account) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE CPF = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setBigDecimal(1, account.balance);
        preparedStatement.setString(2,account.cpf.getcpf());
        preparedStatement.executeUpdate();
    }

    void transfer(Account account, BigDecimal amount, CPF cpf) throws SQLException {
        if (!userExists(cpf)) {
            System.out.println("Conta inexistente!");
        };
        Account destination = getAccount(cpf.getcpf());
        account.transfer(amount, destination);

        updateAccount(account);
        updateAccount(destination);
    }

    void transaction(Account account, BigDecimal amount, CPF cpf) throws SQLException {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "INSERT INTO transactions (CPFsrc, date,CPFdest, amount) VALUES (?,?,?,?);";

    }

    void disconnect() throws SQLException {
        connection.close();
    }
}
