package imd.ufrn.br.models;

public class Pessoa implements ITributavel {
    //@ spec_public
    //@ non_null
    private String nome = "";
    //@ spec_public
    //@ non_null
    private double salario = 0;
    //@ spec_public
    //@ nullable
    private ContaCorrente conta;
    //@ spec_public
    //@ nullable
    private SeguroVida seguro;

    public Pessoa() {}

    //@ requires salario > 0;
    //@ ensures nome != null;
    //@ ensures conta != null;
    //@ ensures this.nome == nome;
    //@ ensures this.salario == salario;
    //@ ensures this.conta == conta;
    //@ ensures this.seguro == seguro;
    public Pessoa(String nome, double salario, ContaCorrente conta, SeguroVida seguro)  {
        this.nome = nome;
        this.salario = salario;
        this.conta = conta;
        this.seguro = seguro;
    }

    public String getNome() {
        return nome;
    }

    //@ requires nome != null;
    //@ ensures this.nome == nome;
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    //@ requires salario > 0;
    //@ ensures this.salario == salario;
    public void setSalario(double salario) {
        this.salario = salario;
    }

    public ContaCorrente getConta() {
        return conta;
    }

    //@ requires conta != null;
    //@ ensures this.conta == conta;
    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }
    
    public SeguroVida getSeguro() {
        return seguro;
    }

    //@ ensures this.seguro == seguro;
    public void setSeguro(SeguroVida seguro) {
        this.seguro = seguro;
    }

    //@ also
    //@ requires salario > 0;
    //@ ensures \result == (11 * salario) / 100;
    @Override
    public double calcularTributos() {
        return (11 * salario) / 100;
    }
}
