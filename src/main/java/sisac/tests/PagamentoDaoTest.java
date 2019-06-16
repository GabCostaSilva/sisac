package sisac.tests;

import org.junit.jupiter.api.Test;
import sisac.dao.PagamentoDAO;
import sisac.helpers.DataFormatada;
import sisac.models.Pagamento;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoDaoTest {
    private PagamentoDAO pagamentoDao = new PagamentoDAO();

    @Test void shouldCreatePagamento(){
        Pagamento pagamento = new Pagamento();

        pagamento.setValor(450.50);
        pagamento.setData(new DataFormatada(LocalDate.now()));
        pagamento.setTipo(0);

        long id = pagamentoDao.create(pagamento).getId();

        assertEquals(pagamento.getId(), id);
    }

}
