package imd.ufrn.br.models;

public class ContaCorrente implements ITributavel {
    private String agencia;
    private String numero;
    private double saldo;

    public ContaCorrente() {
    }

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

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "agencia='" + agencia + '\'' +
                ", numero='" + numero + '\'' +
                ", saldo=" + saldo +
                '}';
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

    public void sacar(double valor) {
        System.out.println("############## SACAR ##############");

        if(valor > this.saldo) {
            System.out.println("Saldo insuficiente para realizar o saque!");
        }
        else{
            System.out.println("Valor sacado " + valor);
            this.saldo -= valor;
        }

        System.out.println("############################################");

    }

    public void depositar(double valor) {
        System.out.println("############## DEPOSITAR ##############");

        this.saldo += valor;
        System.out.println("Deposito de " + valor + " realizado com sucesso!");

        System.out.println("############################################");

    }

    public boolean transferir(double valor, ContaCorrente cc) {
        System.out.println("############## TRANSFERIR ##############");

        if(valor > this.saldo) {
            System.out.println("Saldo insuficiente para transferir!");
            return false;
        }
        this.saldo -= valor;
        cc.depositar(valor);
        System.out.println("Tranferência de " + valor + " realizada com sucesso!");

        System.out.println("############################################");

        return true;
    }

    @Override
    public double calcularTributos() {
        return (this.saldo * 0.38) / 100;
    }
}
