public class CPF {
    private char[] cpf = new char[11];
    CPF(String cpf) {
        if (cpf.length() != 11) {
            System.out.println("CPF deve conter 11 caracteres.");
        }
        this.cpf = cpf.toCharArray();
    }
    String getcpf() {
        return new String(cpf);
    }
}
