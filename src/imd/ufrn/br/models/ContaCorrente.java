package imd.ufrn.br.models;

import imd.ufrn.br.utils.exceptions.MoneyNotAvailableException;
import imd.ufrn.br.utils.exceptions.MoneySoMuchException;

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

        if (valor > this.saldo) {
            throw new MoneyNotAvailableException("Saldo insuficiente para realizar o saque na conta!");
        }

        System.out.println("Valor sacado " + valor);
        this.saldo -= valor;

        System.out.println("############################################");

    }

    public void depositar(double valor) {
        System.out.println("############## DEPOSITAR ##############");

        if (this.saldo > 3000) 
            throw new MoneySoMuchException("A receita confiscou seu dinheiro! Pode parar a economia não!");

        this.saldo += valor;
        System.out.println("Deposito de " + valor + " realizado com sucesso!");

        System.out.println("############################################");

    }

    public boolean transferir(double valor, ContaCorrente cc) throws MoneyNotAvailableException {
        System.out.println("############## TRANSFERIR ##############");

        if (valor > this.saldo) {
            throw new MoneyNotAvailableException("Não há saldo disponível na conta " + cc);
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
