package sisac.models;

import sisac.dao.MensalidadeDAO;
import sisac.dao.PagamentoDAO;

import java.time.LocalDate;
import java.util.List;

public class RegistroDePagamento {

    private PagamentoDAO pagamentoDAO;
    private MensalidadeDAO mensalidadeDAO;
    private Aluno aluno;

    RegistroDePagamento(Aluno aluno) {
        this.pagamentoDAO = new PagamentoDAO();
        this.mensalidadeDAO = new MensalidadeDAO();
        this.aluno = aluno;
    }

    void adicionaPagamento(Pagamento pagamento) {
        Pagamento pagamentoCriado = pagamentoDAO.create(pagamento);
        Mensalidade mensalidade = new Mensalidade();

        mensalidade.setValor(pagamentoCriado.getValor());
        mensalidade.setPagamentoId(pagamentoCriado.getId());
        mensalidade.setEstaPaga(true);
        mensalidade.setDataVencimento(LocalDate.now().plusDays(20));

        mensalidadeDAO.create(mensalidade);

    }

    public List<Pagamento> getPagamentos() {
        return pagamentoDAO.findByAlunoId(aluno);
    }

}
