package sisac.models;

import java.util.ArrayList;

public class RegistroDePagamento {

    private ArrayList<Pagamento> pagamentos;
    private int id;

    RegistroDePagamento(int id) {
        this.pagamentos = new ArrayList<>();
        this.id = id;
    }

    void adicionaPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
    }

    ArrayList<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public int getId() {
        return id;
    }
}
