package imd.ufrn.br.dao;

import imd.ufrn.br.models.Pessoa;

import java.util.ArrayList;

public class PessoaDAO {
     private  ArrayList<Pessoa> pessoas;

    private PessoaDAO() {
        this.pessoas = new ArrayList<Pessoa>();
    }

    static private PessoaDAO instance;

    static public PessoaDAO getInstance() {
        if(instance == null) {
            instance = new PessoaDAO();
        }

        return instance;
    }

    public void cadastrarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public void removerPessoa(Pessoa pessoa) {
        pessoas.remove(pessoa);
    }

    public void listarPesoas() {
        for(Pessoa pessoa : pessoas) {
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Salário: " + pessoa.getSalario());
            System.out.println(pessoa.getConta().toString());
            System.out.println(pessoa.getSeguro().toString());

        }
    }

    public void calcularTributosPessoas() {

    }

    public void imprimeImpostoTotal() {

    }

}
