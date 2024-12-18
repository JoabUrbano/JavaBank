package imd.ufrn.br.dao;

import imd.ufrn.br.controllers.GeradorImpostoRenda;
import imd.ufrn.br.models.Pessoa;
import imd.ufrn.br.models.SeguroVida;
import imd.ufrn.br.models.ContaCorrente;

import java.util.ArrayList;

//@ non_null_by_default
public class PessoaDAO {
    GeradorImpostoRenda geradorImpostoRenda = new GeradorImpostoRenda();
    //@ spec_public
    private ArrayList<Pessoa> pessoas;
    //@ spec_public
    private ContaCorrente ccDefault;
    //@ spec_public
    private SeguroVida svDefault;
    //@ spec_public
    private Pessoa pDefault;

    private PessoaDAO() {
        this.ccDefault = new ContaCorrente("0000-0", "000.000-0", 1);
        this.svDefault = new SeguroVida(1, 1, "Seguro Default", 1, 1);
        this.pDefault = new Pessoa("Pessoa Default", 1, ccDefault, svDefault);
        this.pessoas = new ArrayList<Pessoa>();
        this.pessoas.add(pDefault);
    }

    static private PessoaDAO instance;

    static public PessoaDAO getInstance() {
        if (instance == null) {
            instance = new PessoaDAO();
        }

        return instance;
    }

    //@ requires pessoa != null;
    //@ assigns pessoas.*;
    public void cadastraPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    //@ requires pessoas.size() > 1;
    //@ assigns pessoas.*;
    //@ ensures pessoas.size() > 0;
    public void removerPessoa(Pessoa pessoa) {
        pessoas.remove(pessoa);
    }

    //@ requires pessoas != null;
    //@ requires (\forall Pessoa pessoa; pessoas.contains(pessoa); pessoa.salario > 0);
    //@ requires (\forall Pessoa pessoa; pessoas.contains(pessoa); pessoa.getSalario() > 0);
    //@ requires (\forall Pessoa pessoa; pessoas.contains(pessoa); pessoa.getConta() != null);
    //@ requires (\forall Pessoa pessoa; pessoas.contains(pessoa); pessoa.getSeguro() != null);
    public double calcularTributosPessoas() {

        double impostoTotal = 0;
        double impostoIndividual = 0;
        for (Pessoa pessoa : pessoas) {
            impostoIndividual = geradorImpostoRenda.calculaValorTotalTributo(pessoa);
            //@ assert impostoIndividual > 0;
            //@ assume impostoTotal >= 0;
            impostoTotal += impostoIndividual;
            //@ assert impostoTotal > 0;
        }

        return impostoTotal;
    }

    //@ requires pessoas != null;
    //@ requires (\forall Pessoa pp; pessoas.contains(pp); pp.salario > 0);
    //@ requires (\forall Pessoa pp; pessoas.contains(pp); pp.getSalario() > 0);
    //@ requires (\forall Pessoa pp; pessoas.contains(pp); pp.getConta() != null);
    //@ requires (\forall Pessoa pp; pessoas.contains(pp); pp.getSeguro() != null);
    //@ ensures \result != null;
    private Pessoa getMaiorPagador() {
        //@ assume this.pDefault != null;
        Pessoa pessoa = this.pDefault;
        //@ assert pessoa != null;
        
        double newImpostoTotal = 0;
        for (Pessoa pp : pessoas) {
            //@ assume pp.getSalario() > 0;
            double tributoTotal = geradorImpostoRenda.calculaValorTotalTributo(pp);
            //@ assert tributoTotal > 0;

            //@ assume pessoa.salario > 0;
            //@ assume pessoa.getSalario() > 0;
            //@ assume pessoa.getConta() != null;
            //@ assume pessoa.getSeguro() != null;
            //@ assume pessoa != null;
            newImpostoTotal = geradorImpostoRenda.calculaValorTotalTributo(pessoa);
            //@ assert newImpostoTotal > 0;

            if (tributoTotal > newImpostoTotal) {
                //@ assume pp != null;
                pessoa = pp;
                //@ assert pessoa != null;
            }
        }

        return pessoa;
    }

    //@ requires pessoas != null;
    //@ requires (\forall Pessoa pp; pessoas.contains(pp); pp.salario > 0);
    //@ requires (\forall Pessoa pp; pessoas.contains(pp); pp.getSalario() > 0);
    //@ requires (\forall Pessoa pp; pessoas.contains(pp); pp.getConta() != null);
    //@ requires (\forall Pessoa pp; pessoas.contains(pp); pp.getSeguro() != null);
    //@ ensures \result != null;
    private Pessoa getMaiorSeguro() {
        //@ assume this.pDefault != null;
        Pessoa pessoa = this.pDefault;
        //@ assert pessoa != null;
        
        for (Pessoa pp : pessoas) {
            if (pp.getSeguro().getValor() > pessoa.getSeguro().getValor()) {
                pessoa = pp;
            }
        }

        return pessoa;
    }

}
