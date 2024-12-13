package imd.ufrn.br.controllers;

import imd.ufrn.br.models.Pessoa;

public class GeradorImpostoRenda {
    //@ requires pessoa != null;
    //@ ensures \result > 0;
    public double calculaValorTotalTributo(Pessoa pessoa) {
        double totalTributo = 0;
        //@ assume pessoa.getConta().getSaldo() > 0;
        //@ assume pessoa.getSalario() > 0;
        double tributoConta = pessoa.getConta().calcularTributos();
        //@ assert tributoConta > 0;
        
        double tributoSeguro = pessoa.getSeguro().calcularTributos();
        //@ assert tributoSeguro > 0;
        
        double tributoPessoa = pessoa.calcularTributos();
        //@ assert tributoPessoa > 0;
        
        totalTributo = tributoConta + tributoSeguro + tributoPessoa;

        return totalTributo;
    }
}
