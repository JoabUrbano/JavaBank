package imd.ufrn.br.models;

import imd.ufrn.br.utils.exceptions.MoneyNotAvailableException;
import imd.ufrn.br.utils.exceptions.MoneySoMuchException;

public class ContaCorrente implements ITributavel {
    //@ spec_public
    //@ non_null
    private String agencia = "0";
    //@ spec_public
    //@ non_null
    private String numero = "0";
    //@ spec_public
    private double saldo;

    //@ requires agencia != null;
    //@ requires numero != null;
    //@ requires saldo > 0;
    //@ ensures this.agencia == agencia;
    //@ ensures this.numero == numero;
    //@ ensures this.saldo == saldo;
    public ContaCorrente(String agencia, String numero, double saldo) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
    }

    //@ pure
    public String getAgencia() {
        return this.agencia;
    }

    //@ requires agencia != null;
    //@ ensures this.agencia == agencia;
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    //@ pure
    public String getNumero() {
        return this.numero;
    }

    //@ requires numero != null;
    //@ ensures this.numero == numero;
    public void setNumero(String numero) {
        this.numero = numero;
    }

    //@ pure
    public double getSaldo() {
        return this.saldo;
    }

    //@ requires saldo > 0;
    //@ ensures this.saldo == saldo;
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    //@ requires valor > 0;
    //@ requires valor < this.saldo;
    //@ requires (this.saldo - valor) > 0;
    //@ ensures this.saldo > 0;
    //@ ensures this.saldo <= \old(this.saldo);
    public void sacar(double valor) {
        if (valor > this.saldo) {
            throw new MoneyNotAvailableException("Saldo insuficiente para realizar o saque na conta!");
        }

        this.saldo -= valor;
    }

    //@ requires valor > 0;
    //@ requires (this.saldo + valor) <= 3000;
    //@ requires (this.saldo + valor) > this.saldo;
    //@ ensures this.saldo <= 3000;
    //@ ensures this.saldo >= \old(this.saldo);
    public void depositar(double valor) {
        if (this.saldo > 3000) 
            throw new MoneySoMuchException("A receita confiscou seu dinheiro! Pode parar a economia não!");

        this.saldo += valor;
    }

    //@ requires valor > 0;
    //@ requires valor < this.saldo;
    //@ requires (this.saldo - valor) > 0;
    //@ requires cc != null;
    //@ ensures this.saldo > 0;
    //@ ensures this.saldo <= \old(this.saldo);
    public boolean transferir(double valor, ContaCorrente cc) throws MoneyNotAvailableException {
        if (valor > this.saldo) {
            throw new MoneyNotAvailableException("Não há saldo disponível na conta " + cc);
        }
        this.saldo -= valor;
        cc.depositar(valor);
        return true;
    }

    //@ also
    //@ requires saldo > 0;
    //@ ensures \result == (this.saldo * 0.38) / 100;
    @Override
    public double calcularTributos() {
        return (this.saldo * 0.38) / 100;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "agencia='" + this.agencia + '\'' +
                ", numero='" + this.numero + '\'' +
                ", saldo=" + this.saldo +
                '}';
    }
}
