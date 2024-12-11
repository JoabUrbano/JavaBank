package imd.ufrn.br.models;

import imd.ufrn.br.utils.exceptions.MoneyNotAvailableException;
import imd.ufrn.br.utils.exceptions.MoneySoMuchException;

public class ContaCorrente implements ITributavel {
    //@ spec_public
    private String agencia;
    //@ spec_public
    private String numero;
    //@ spec_public
    private double saldo;

    //@ ensures agencia != null;
    //@ ensures numero != null;
    //@ requires saldo > 0;
    //@ ensures this.agencia == agencia;
    //@ ensures this.numero == numero;
    //@ ensures this.saldo == saldo;
    public ContaCorrente(String agencia, String numero, double saldo) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    //@ requires agencia != null;
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

    //@ also
    //@ requires saldo > 0;
    //@ ensures \result == (this.saldo * 0.38) / 100;
    @Override
    public double calcularTributos() {
        return (this.saldo * 0.38) / 100;
    }

    //@ ensures \result != null;
    @Override
    public String toString() {
        return "ContaCorrente{" +
                "agencia='" + agencia + '\'' +
                ", numero='" + numero + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
