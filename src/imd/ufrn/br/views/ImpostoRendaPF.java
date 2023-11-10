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
        ContaCorrente cc1 = new ContaCorrente();
        cc1.setAgencia("1020-5");
        cc1.setNumero("100.231-1");
        cc1.setSaldo(0);

        SeguroVida sv1 = new SeguroVida();
        sv1.setNumero(1);
        sv1.setBeneficiado("Maria da Silva");
        sv1.setValor(10000);

        Pessoa p1 = new Pessoa();
        p1.setNome("Roberto Carlos");
        p1.setSalario(500.0);
        p1.setConta(cc1);
        p1.setSeguro(sv1);

        daoP.cadastraPessoa(p1);

        // Registrar p2
        ContaCorrente cc2 = new ContaCorrente();
        cc2.setAgencia("2105-4");
        cc2.setNumero("123.564-9");
        cc2.setSaldo(0);

        SeguroVida sv2 = new SeguroVida();
        sv2.setNumero(2);
        sv2.setBeneficiado("João Maria");
        sv2.setValor(15000);

        Pessoa p2 = new Pessoa();
        p2.setNome("Dom Pedro");
        p2.setSalario(1000.0);
        p2.setConta(cc2);
        p2.setSeguro(sv2);

        daoP.cadastraPessoa(p2);

        // Registrar p3
        ContaCorrente cc3 = new ContaCorrente();
        cc3.setAgencia("3565-4");
        cc3.setNumero("584.557-3");
        cc3.setSaldo(0);

        SeguroVida sv3 = new SeguroVida();
        sv3.setNumero(3);
        sv3.setBeneficiado("João do Patrocínio");
        sv3.setValor(16000);

        Pessoa p3 = new Pessoa();
        p3.setNome("Rui Barbosa");
        p3.setSalario(1500.50);
        p3.setConta(cc3);
        p3.setSeguro(sv3);

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

        // Transferências
        try {
            p2.getConta().transferir(15000000, cc3);
        } catch (MoneyNotAvailableException e) {
            System.out.println(e.getMessage());
        }

        // Listar as Pessoas
        daoP.listaPessoas();

        // Listar os impostos
        daoP.calcularTributosPessoas(); // TODO: Tá faltando ajeitar essa implementação.

        // Listar o total de imposto e Pessoas associadas
        daoP.imprimeImpostoTotal();
    }
}
