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
        if(valor > this.saldo) {
            System.out.println("Saldo insuficiente para realizar o saque!");
        }
        else{
            System.out.println("Valor sacado " + valor);
            this.saldo -= valor;
        }
    }

    void depositar(double valor) {
        this.saldo += valor;
        System.out.println("Deposito de " + valor + " realizado com sucesso!");
    }

    boolean transferir(double valor, ContaCorrente cc) {
        if(valor > this.saldo) {
            System.out.println("Saldo insuficiente para transferir!");
            return false;
        }
        this.saldo -= valor;
        cc.depositar(valor);
        System.out.println("Tranferência de " + valor + " realizada com sucesso!");
        return true;
    }
}
