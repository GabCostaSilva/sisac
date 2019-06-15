package sisac.models;

import java.util.ArrayList;

public class RegistroDePagamento {

    private ArrayList<Pagamento> pagamentos;
    private Aluno aluno;

    RegistroDePagamento() {
        this.pagamentos = new ArrayList<>();
    }

    void adicionaPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
    }

    ArrayList<Pagamento> getPagamentos() {
        return pagamentos;
    }

}
