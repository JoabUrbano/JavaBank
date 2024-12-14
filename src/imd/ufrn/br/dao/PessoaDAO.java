package imd.ufrn.br.dao;

import imd.ufrn.br.controllers.GeradorImpostoRenda;
import imd.ufrn.br.models.Pessoa;

import java.util.ArrayList;

public class PessoaDAO {
    GeradorImpostoRenda geradorImpostoRenda = new GeradorImpostoRenda();
    //@ non_null
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

    public double calcularTributosPessoas() {
        double impostoTotal = 0;
        double impostoIndividual = 0;

        for (Pessoa pessoa : pessoas) {
            impostoIndividual = geradorImpostoRenda.calculaValorTotalTributo(pessoa);
            impostoTotal += impostoIndividual;
        }

        return impostoTotal;
    }
    private Pessoa getMaiorPagador() {
        Pessoa pessoa = null;
        for (Pessoa pp : pessoas) {
            double tributoTotal = geradorImpostoRenda.calculaValorTotalTributo(pp);

            if (tributoTotal > geradorImpostoRenda.calculaValorTotalTributo(pessoa)) {
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

}
