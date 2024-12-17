package imd.ufrn.br.controllers;

import imd.ufrn.br.models.Pessoa;

public class GeradorImpostoRenda {
    //@ requires pessoa != null;
    //@ requires pessoa.getConta() != null;
    //@ requires pessoa.getSeguro() != null;
    //@ requires pessoa.getSalario() > 0;
    //@ requires pessoa.salario > 0;
    //@ ensures \result > 0;
    public double calculaValorTotalTributo(Pessoa pessoa) {
        double totalTributo = 0;
        double tributoConta = pessoa.getConta().calcularTributos();
        //@ assert tributoConta >= 0;
        
        double tributoSeguro = pessoa.getSeguro().calcularTributos();
        //@ assert tributoSeguro > 0;
        
        double tributoPessoa = pessoa.calcularTributos();
        //@ assert tributoPessoa > 0;
        
        totalTributo = tributoConta + tributoSeguro + tributoPessoa;
        //@ assert totalTributo > 0;

        return totalTributo;
    }
}
