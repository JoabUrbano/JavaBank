package imd.ufrn.br.dao;

import imd.ufrn.br.controllers.GeradorImpostoRenda;
import imd.ufrn.br.models.Pessoa;
import imd.ufrn.br.models.SeguroVida;
import imd.ufrn.br.models.ContaCorrente;

import java.util.ArrayList;

public class PessoaDAO {
    GeradorImpostoRenda geradorImpostoRenda = new GeradorImpostoRenda();
    // @ non_null
    private ArrayList<Pessoa> pessoas;
    // @ non_null
    private ContaCorrente ccDefault;
    // @ non_null
    private SeguroVida svDefault;
    // @ non_null
    private Pessoa pDefault;

    private PessoaDAO() {
        this.pessoas = new ArrayList<Pessoa>();
        this.ccDefault = new ContaCorrente("0000-0", "000.000-0", 1);
        this.svDefault = new SeguroVida(1, 1, "Seguro Default", 1, 1);
        this.pDefault = new Pessoa("Pessoa Default", 1, ccDefault, svDefault);
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
        Pessoa pessoa = this.pDefault;
        for (Pessoa pp : pessoas) {
            double tributoTotal = geradorImpostoRenda.calculaValorTotalTributo(pp);

            if (tributoTotal > geradorImpostoRenda.calculaValorTotalTributo(pessoa)) {
                pessoa = pp;
            }
        }

        return pessoa;
    }

    private Pessoa getMaiorSeguro() {
        Pessoa pessoa = this.pDefault;

        for (Pessoa pp : pessoas) {
            if (pp.getSeguro().getValor() > pessoa.getSeguro().getValor()) {
                pessoa = pp;
            }
        }

        return pessoa;
    }

}
