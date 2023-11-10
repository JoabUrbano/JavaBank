package imd.ufrn.br.dao;

import imd.ufrn.br.controllers.GeradorImpostoRenda;
import imd.ufrn.br.models.Pessoa;

import java.util.ArrayList;

public class PessoaDAO {
    GeradorImpostoRenda geradorImpostoRenda = new GeradorImpostoRenda();
    private ArrayList<Pessoa> pessoas;

    private PessoaDAO() {
        this.pessoas = new ArrayList<Pessoa>();
    }

    static private PessoaDAO instance;

    static public PessoaDAO getInstance() {
        if (instance == null) {
            instance = new PessoaDAO();
        }

        return instance;
    }

    public void cadastraPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public void removerPessoa(Pessoa pessoa) {
        pessoas.remove(pessoa);
    }

    public void listaPessoas() {
        System.out.println("############## LISTAR PESSOAS ##############");

        for (Pessoa pessoa : pessoas) {
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Salário: " + pessoa.getSalario());
            System.out.println(pessoa.getConta().toString());
            System.out.println(pessoa.getSeguro().toString());
            System.out.println("_______________________________________");
        }

        System.out.println("############################################");

    }

    public double calcularTributosPessoas() {
        double impostoTotal = 0;
        double impostoIndividual = 0;

        for (Pessoa pessoa : pessoas) {
            impostoIndividual = geradorImpostoRenda.calculaValorTotalTributo(pessoa);
            impostoTotal += impostoIndividual;
            System.out.println("O imposto de " + pessoas.toString() + " é " + impostoIndividual + ".");
        }
        System.out.println("############################################");

        return impostoTotal;
    }

    private Pessoa getMaiorPagador() {
        Pessoa pessoa = null;

        for (Pessoa pp : pessoas) {
            double tributoTotal = geradorImpostoRenda.calculaValorTotalTributo(pp);

            if (pessoa == null || tributoTotal > geradorImpostoRenda.calculaValorTotalTributo(pessoa)) {
                pessoa = pp;
            }
        }

        return pessoa;
    }

    private Pessoa getMaiorSeguro() {
        Pessoa pessoa = null;

        for (Pessoa pp : pessoas) {
            if (pessoa == null || pp.getSeguro().getValor() > pessoa.getSeguro().getValor()) {
                pessoa = pp;
            }
        }

        return pessoa;
    }

    public void imprimeImpostoTotal() {
        double impostoTotal = calcularTributosPessoas();
        Pessoa pessoaMaiorImposto = this.getMaiorPagador();
        Pessoa pessoaMaiorSeguro = this.getMaiorSeguro();
        System.out.println("Valor total de imposto a ser recolhido: " + impostoTotal);
        System.out.println("Maior pagador de imposto: " + pessoaMaiorImposto.getNome());
        System.out.println("Maior pagador de seguro " + pessoaMaiorSeguro.getNome());
    }

}
