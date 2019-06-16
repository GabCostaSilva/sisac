package sisac.models;

import sisac.dao.PagamentoDAO;

import java.util.ArrayList;

public class RegistroDePagamento {

    private PagamentoDAO pagamentoDAO;

    RegistroDePagamento() {
        this.pagamentoDAO = new PagamentoDAO();
    }

    void adicionaPagamento(Pagamento pagamento) {
        pagamentoDAO.create(pagamento);
    }

}
