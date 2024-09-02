package dev.edinho.common;

import java.util.Arrays;

public class CPF {
    char[] cpf;

    public CPF(String cpf) {
        if (cpf.length() == 11) {
            this.cpf = new char[11];
            this.cpf = cpf.toCharArray();
        }
    }

    public void setCPF(String cpf) {
        this.cpf = cpf.toCharArray();
    }

    public String getCPF() {
        return new String(cpf);
    }

    @Override
    public String toString() {
        return new String(cpf);
    }
}
