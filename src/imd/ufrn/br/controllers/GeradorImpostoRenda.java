package imd.ufrn.br.controllers;

import imd.ufrn.br.models.Pessoa;

public class GeradorImpostoRenda {
    public double calculaValorTotalTributo(Pessoa pessoa) {
        double totalTributo = 0;

        totalTributo += pessoa.getConta().calcularTributos();
        totalTributo += pessoa.getSeguro().calcularTributos();
        totalTributo += pessoa.calcularTributos();


        return totalTributo;
    }
}
