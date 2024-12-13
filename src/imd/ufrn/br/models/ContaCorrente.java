package imd.ufrn.br.models;

import imd.ufrn.br.utils.exceptions.MoneyNotAvailableException;
import imd.ufrn.br.utils.exceptions.MoneySoMuchException;

//@ non_null_by_default
public class ContaCorrente implements ITributavel {
    //@ spec_public
    private String agencia;
    //@ spec_public
    private String numero;
    //@ spec_public
    private double saldo;

    //@ public normal_behavior
    //@ requires agencia != null;
    //@ requires numero != null;
    //@ requires 0 < saldo < Double.MAX_VALUE;
    //@ ensures this.agencia == agencia;
    //@ ensures this.numero == numero;
    //@ ensures this.saldo == saldo;
    //@ pure
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
    //@ assigns this.agencia;
    //@ ensures this.agencia == agencia;
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    //@ pure
    public String getNumero() {
        return this.numero;
    }

    //@ requires numero != null;
    //@ assigns this.numero;
    //@ ensures this.numero == numero;
    public void setNumero(String numero) {
        this.numero = numero;
    }

    //@ pure
    public double getSaldo() {
        return this.saldo;
    }

    //@ requires saldo > 0;
    //@ assigns this.saldo;
    //@ ensures this.saldo == saldo;
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    //@ requires valor > 0;
    //@ requires valor < this.saldo;
    //@ requires (this.saldo - valor) > 0;
    //@ assigns this.saldo;
    //@ ensures this.saldo > 0;
    //@ ensures this.saldo <= \old(this.saldo);
    public void sacar(double valor) {
        if (valor > this.saldo) {
            throw new MoneyNotAvailableException("Saldo insuficiente para realizar o saque na conta!");
        }
        //@ assert this.saldo == \old(this.saldo);
        this.saldo -= valor;
    }

    //@ requires valor > 0;
    //@ requires (this.saldo + valor) <= 3000;
    //@ assigns this.saldo;
    //@ ensures this.saldo <= 3000;
    //@ ensures this.saldo >= \old(this.saldo);
    public void depositar(double valor) {
        if (this.saldo > 3000) 
            throw new MoneySoMuchException("A receita confiscou seu dinheiro! Pode parar a economia não!");

        this.saldo += valor;
    }

    //@ also
    //@ requires saldo > 0;
    //@ ensures \result == (this.saldo * 0.38) / 100;
    //@ ensures \result > 0;
    //@ pure
    @Override
    public double calcularTributos() {
        return (this.saldo * 0.38) / 100;
    }
}
