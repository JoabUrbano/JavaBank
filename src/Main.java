import imd.ufrn.br.dao.PessoaDAO;
import imd.ufrn.br.models.ContaCorrente;
import imd.ufrn.br.models.Pessoa;
import imd.ufrn.br.models.SeguroVida;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        PessoaDAO daoP =  PessoaDAO.getInstance();

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

        daoP.cadastrarPessoa(p1);

        daoP.listarPesoas();


    }
}