package imd.ufrn.br.models;

public class SeguroVida implements ITributavel {
    //@ spec_public
    private int numero;
    //@ spec_public
    private double salario;
    //@ spec_public
    //@ non_null
    private String beneficiado = "";
    //@ spec_public
    private double valor;
    //@ spec_public
    private double taxa;

    //@ requires numero > 0;
    //@ requires salario >= 0;
    //@ requires beneficiado != null;
    //@ requires valor > 0;
    //@ requires taxa > 0;
    //@ ensures this.numero == numero;
    //@ ensures this.salario == salario;
    //@ ensures this.beneficiado == beneficiado;
    //@ ensures this.valor == valor;
    //@ ensures this.taxa == taxa;
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

    //@ requires numero > 0;
    //@ ensures this.numero == numero;
    public void setNumero(int numero) {
        this.numero = numero;
    }

    //@ pure
    public double getSalario() {
        return salario;
    }

    //@ requires salario >= 0;
    //@ ensures this.salario == salario;
    public void setSalario(double salario) {
        this.salario = salario;
    }

    //@ pure
    public String getBeneficiado() {
        return beneficiado;
    }

    //@ requires beneficiado != null;
    //@ ensures this.beneficiado == beneficiado;
    public void setBeneficiado(String beneficiado) {
        this.beneficiado = beneficiado;
    }

    //@ pure
    public double getValor() {
        return valor;
    }

    //@ requires valor > 0;
    //@ ensures this.valor == valor;
    public void setValor(double valor) {
        this.valor = valor;
    }

    //@ pure
    public double getTaxa() {
        return taxa;
    }

    //@ requires taxa > 0;
    //@ ensures this.taxa == taxa;
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    @Override
    public double calcularTributos() {
        return 31.50;
    }
}
