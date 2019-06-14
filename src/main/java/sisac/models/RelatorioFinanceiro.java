package sisac.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class RelatorioFinanceiro {
    private RegistroDePagamento registro;

    public RelatorioFinanceiro(RegistroDePagamento registro) {
        this.registro = registro;
    }

    public String emitirRelatorioCompleto() {
        double debito = getTotalPago() - getTotalMensalidades();
        return String.format(
                "Total Pago: %.2f\n" +
                "Total de Mensalidades: %.2f\n" +
                "Débito: %.2f\n",
                getTotalPago(), getTotalMensalidades(), debito);
    }

    public ArrayList<String> emitirRelatorioParcial(LocalDate dataInicial, LocalDate dataFinal) {
        ArrayList<String> relatorio = new ArrayList<>();
        for(Pagamento p : getPagamentosBetweenDates(dataInicial, dataFinal)) {
            relatorio.add(
                    String.format(
                            "Aluno: %s\n" +
                            "Data: %s\n" +
                            "Valor: %.2fR$\n" +
                            "Forma de Pagamento %s\n" +
                            "****************************************************************\n",
                            p.getAluno().getNome(), p.getDataFormatada().getDataToString(), p.getValor(), p.getTipo()));
        }
        return relatorio;
    }

    private ArrayList<Pagamento> getPagamentosBetweenDates(LocalDate dataInicial, LocalDate dataFinal) {
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
        int mesInicial = dataInicial.getMonth().getValue();
        int mesFinal = dataFinal.getMonth().getValue();

        for (Pagamento p : registro.getPagamentos()) {
            if(p.getData().getMonthValue() >= mesInicial && p.getData().getMonthValue() <= mesFinal)
                pagamentos.add(p);
        }

        return pagamentos;
    }


    private double getTotalPago() {
        double totalPago = 0;
        for(Pagamento p : registro.getPagamentos())
            totalPago += p.getValor();

        return totalPago;
    }

    private double getTotalMensalidades() {
        double totalMensalidades = 0;
        for(Pagamento p : registro.getPagamentos()) {
            totalMensalidades += p.getMensalidade().getValor();
        }
        return totalMensalidades;
    }
}
