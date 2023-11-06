package imd.ufrn.br.models;

public class SeguroVida {
    private int numero;
    private double salario;
    private String beneficiado;
    private double valor;
    private double taxa;


    public SeguroVida(int numero, double salario, String beneficiado, double valor, double taxa) {
        this.numero = numero;
        this.salario = salario;
        this.beneficiado = beneficiado;
        this.valor = valor;
        this.taxa = taxa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getBeneficiado() {
        return beneficiado;
    }

    public void setBeneficiado(String beneficiado) {
        this.beneficiado = beneficiado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
}
