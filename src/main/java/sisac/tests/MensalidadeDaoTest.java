package sisac.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sisac.dao.AlunoDAO;
import sisac.dao.MensalidadeDAO;
import sisac.dao.PagamentoDAO;
import sisac.models.Mensalidade;

import java.time.LocalDate;

public class MensalidadeDaoTest {
    MensalidadeDAO mensalidadeDao = new MensalidadeDAO();

    @Test
    void shouldCreateMensalidade() {
        Mensalidade mensalidade = new Mensalidade();
        PagamentoDAO pagamentoDao = new PagamentoDAO();

        mensalidade.setValor(450);
        mensalidade.setDataVencimento(LocalDate.now());
        mensalidade.setEstaPaga(true);

        long idPagamento = pagamentoDao.get().get(0).getId();

        mensalidade.setPagamentoId(idPagamento);

        long id = mensalidadeDao.create(mensalidade).getId();

        Assertions.assertEquals(id, mensalidade.getId());
    }
}
