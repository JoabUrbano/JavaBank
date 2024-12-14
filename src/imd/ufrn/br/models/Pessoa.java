package imd.ufrn.br.models;

//@ non_null_by_default
public class Pessoa implements ITributavel {
    //@ spec_public
    private String nome;
    //@ spec_public
    private double salario;
    //@ spec_public
    private ContaCorrente conta;
    //@ spec_public
    private SeguroVida seguro;

    //@ public invariant salario > 0;

    //@ public normal_behavior
    //@ requires nome != null;
    //@ requires 0 < salario < Double.MAX_VALUE;
    //@ requires conta != null;
    //@ requires seguro != null;
    //@ ensures this.nome == nome;
    //@ ensures this.salario == salario;
    //@ ensures this.conta == conta;
    //@ ensures this.seguro == seguro;
    //@ pure
    public Pessoa(String nome, double salario, ContaCorrente conta, SeguroVida seguro)  {
        this.nome = nome;
        this.salario = salario;
        this.conta = conta;
        this.seguro = seguro;
    }

    //@ pure
    public String getNome() {
        return nome;
    }

    //@ requires nome != null;
    //@ assigns this.nome;
    //@ ensures this.nome == nome;
    public void setNome(String nome) {
        this.nome = nome;
    }

    //@ pure
    public double getSalario() {
        return salario;
    }

    //@ requires salario > 0;
    //@ assigns this.salario;
    //@ ensures this.salario == salario;
    public void setSalario(double salario) {
        this.salario = salario;
    }

    //@ pure
    public ContaCorrente getConta() {
        return conta;
    }

    //@ requires conta != null;
    //@ assigns this.conta;
    //@ ensures this.conta == conta;
    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }
    
    //@ pure
    public SeguroVida getSeguro() {
        return seguro;
    }

    //@ requires seguro != null;
    //@ assigns this.seguro;
    //@ ensures this.seguro == seguro;
    public void setSeguro(SeguroVida seguro) {
        this.seguro = seguro;
    }

    //@ also
    //@ ensures \result == (11 * salario) / 100;
    //@ ensures \result > 0; pure
    @Override
    public double calcularTributos() {
        return (11 * this.salario) / 100;
    }
}
