package imd.ufrn.br.views;

import imd.ufrn.br.dao.PessoaDAO;
import imd.ufrn.br.models.ContaCorrente;
import imd.ufrn.br.models.Pessoa;
import imd.ufrn.br.models.SeguroVida;
import imd.ufrn.br.utils.exceptions.MoneyNotAvailableException;
import imd.ufrn.br.utils.exceptions.MoneySoMuchException;

public class ImpostoRendaPF {
    static public void Run() {
        PessoaDAO daoP = PessoaDAO.getInstance();

        // Registrar p1
        ContaCorrente cc1 = new ContaCorrente("1020-5", "100.231-1", 0);
        SeguroVida sv1 = new SeguroVida(1, "Maria da Silva", 10000);
        Pessoa p1 = new Pessoa("Roberto Carlos", 500.0, cc1, sv1);

        daoP.cadastraPessoa(p1);

        // Registrar p2
        ContaCorrente cc2 = new ContaCorrente("2105-4", "123.564-9", 0);
        SeguroVida sv2 = new SeguroVida(2, "João Maria", 15000);
        Pessoa p2 = new Pessoa("Dom Pedro", 1000.0, cc2, sv2);

        daoP.cadastraPessoa(p2);

        // Registrar p3
        ContaCorrente cc3 = new ContaCorrente("3565-4", "584.557-3", 0);
        SeguroVida sv3 = new SeguroVida(3, "João do Patrocínio", 16000);
        Pessoa p3 = new Pessoa("Rui Barbosa", 1500.50, cc3, sv3);

        daoP.cadastraPessoa(p3);

        // Listar as Pessoas
        daoP.listaPessoas();

        // Depósitos
        try {
            p1.getConta().depositar(30000);
        } catch (MoneySoMuchException e) {
            System.out.println(e.getMessage());
        }

        try {
            p1.getConta().depositar(2000);
        } catch (MoneySoMuchException e) {
            System.out.println(e.getMessage());
        }

        try {
            p2.getConta().depositar(500);
        } catch (MoneySoMuchException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        // Saques
        try {
            p3.getConta().sacar(100);
        } catch (MoneyNotAvailableException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        // Listar as Pessoas
        daoP.listaPessoas();

        // Listar os impostos
        daoP.calcularTributosPessoas(); // TODO: Tá faltando ajeitar essa implementação.

        // Listar o total de imposto e Pessoas associadas
        daoP.imprimeImpostoTotal();
    }
}
