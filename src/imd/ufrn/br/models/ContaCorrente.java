package imd.ufrn.br.models;

public class ContaCorrente {
    private String agencia;
    private String numero;
    private double saldo;

    public ContaCorrente(String agencia, String numero, double saldo) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    void sacar(double valor) {

    }

    void depositar(double valor) {

    }

    boolean transferir(double valor, ContaCorrente cc) {
        return true;
    }
}
