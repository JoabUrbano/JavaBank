package imd.ufrn.br.models;

public class SeguroVida implements ITributavel {
    //@ spec_public
    private int numero;
    //@ spec_public
    private double salario;
    //@ spec_public
    //@ non_null
    private String beneficiado;
    //@ spec_public
    private double valor;
    //@ spec_public
    private double taxa;

    //@ public invariant salario > 0;
    //@ public invariant valor > 0;
    //@ public invariant taxa > 0;

    // Garantir que o número do seguro só pode ser atualizado para cima quando houver mudanças,
    // impedindo que haja atualizações erronias para um número já existente
    //@ public constraint numero >= \old(numero);

    //@ public normal_behavior
    //@ requires 0 < numero < Integer.MAX_VALUE;
    //@ requires 0 < salario < Double.MAX_VALUE;
    //@ requires beneficiado != null;
    //@ requires 0 < valor < Double.MAX_VALUE;
    //@ requires 0 < taxa < Double.MAX_VALUE;
    //@ ensures this.numero == numero;
    //@ ensures this.salario == salario;
    //@ ensures this.beneficiado == beneficiado;
    //@ ensures this.valor == valor;
    //@ ensures this.taxa == taxa;
    //@ pure
    public SeguroVida(int numero, double salario, String beneficiado, double valor, double taxa) {
        this.numero = numero;
        this.salario = salario;
        this.beneficiado = beneficiado;
        this.valor = valor;
        this.taxa = taxa;
    }

    //@ pure
    public int getNumero() {
        return numero;
    }

    //@ requires numero > this.numero;
    //@ assigns this.numero;
    //@ ensures this.numero == numero;
    public void setNumero(int numero) {
        this.numero = numero;
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
    public String getBeneficiado() {
        return beneficiado;
    }

    //@ requires beneficiado != null;
    //@ assigns this.beneficiado;
    //@ ensures this.beneficiado == beneficiado;
    public void setBeneficiado(String beneficiado) {
        this.beneficiado = beneficiado;
    }

    //@ pure
    public double getValor() {
        return valor;
    }

    //@ requires valor > 0;
    //@ assigns this.valor;
    //@ ensures this.valor == valor;
    public void setValor(double valor) {
        this.valor = valor;
    }

    //@ pure
    public double getTaxa() {
        return taxa;
    }

    //@ requires taxa > 0;
    //@ assigns this.taxa;
    //@ ensures this.taxa == taxa;
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    //@ also
    //@ ensures \result == 31.50;
    //@ ensures \result > 0;
    //@ pure
    @Override
    public double calcularTributos() {
        return 31.50;
    }
}
