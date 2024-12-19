package imd.ufrn.br.models;

import imd.ufrn.br.utils.exceptions.MoneyNotAvailableException;

//@ non_null_by_default
public class ContaCorrente implements ITributavel {
    //@ spec_public
    private String agencia;
    //@ spec_public
    private String numero;
    //@ spec_public
    private double saldo;

    //@ public invariant saldo >= 0;

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
    //@ requires valor <= this.saldo;
    //@ requires (this.saldo - valor) > 0;
    //@ assigns this.saldo;
    //@ ensures this.saldo <= \old(this.saldo);
    public void sacar(double valor) {
        if (valor > this.saldo) {
            throw new MoneyNotAvailableException("Saldo insuficiente para realizar o saque na conta!");
        }
        //@ assert this.saldo == \old(this.saldo);
        this.saldo -= valor;
    }

    //@ requires valor > 0;
    //@ assigns this.saldo;
    //@ ensures this.saldo == \old(this.saldo) + valor;
    public void depositar(double valor) {
        this.saldo += valor;
    }

    //@ requires cc != null;
    //@ requires valor > 0;
    //@ requires this.saldo >= 0;
    //@ requires valor <= this.saldo;
    //@ ensures this.saldo >= 0;
    public void transferir(double valor, ContaCorrente cc) throws MoneyNotAvailableException {
        if (valor > this.saldo) {
            //@ assert this.saldo == \old(this.saldo);
            //@ assert this.saldo >= 0;
            throw new MoneyNotAvailableException("Não há saldo disponível na conta " + cc);
        }
        //@ assert this.saldo >= valor;
        this.saldo =  this.saldo - valor;
        //@ assert this.saldo >= 0;
        cc.depositar(valor);
    }

    //@ also
    //@ ensures \result == (this.saldo * 0.38) / 100;
    //@ ensures \result >= 0;
    //@ pure
    @Override
    public double calcularTributos() {
        //@ assume this.saldo >= 0;
        return (this.saldo * 0.38) / 100;
    }
}
