package sisac.tests.dao;


import com.sisac.dao.PagamentoDAO;
import com.sisac.models.Pagamento;

import java.time.LocalDate;
import java.util.ArrayList;

public class PagamentoDAOTest {
    public static void test() {


        Pagamento p1 = new Pagamento();
        Pagamento p2 = new Pagamento();
        PagamentoDAO dao = new PagamentoDAO();

        p1.setValor(111);
        p1.setData(LocalDate.of(2013, 04, 12));
        p1.setTipo("CARTAO_DE_CRETIDO");

        p2.setValor(22);
        p2.setData(LocalDate.of(2013, 04, 12));
        p2.setTipo("DINHEIRO");


        dao.addPagamento(p1);
        dao.addPagamento(p2);

        dao.removePagamento(2);
        dao.updatePagamento(p2);

        ArrayList<Pagamento> Pagamentos = dao.fetchPagamentos();
        for(Pagamento Pagamento: Pagamentos){
            System.out.println("Pagamento: " + Pagamento.toString());
        }
    }

}
