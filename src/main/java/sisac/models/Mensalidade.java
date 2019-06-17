package sisac.models;

import sisac.helpers.DataFormatada;

import java.time.LocalDate;

public class Mensalidade {

    private long id;
    private double valor;
    private boolean estaPaga;
    private long pagamentoId;


    private DataFormatada dataVencimento;

    public Mensalidade() {

    }

    public Mensalidade(String data, double valor) {
        this.dataVencimento = new DataFormatada(data);
        this.valor = valor;
    }

    public long getPagamentoId() {
        return pagamentoId;
    }

    public void setPagamentoId(long pagamentoId) {
        this.pagamentoId = pagamentoId;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isEstaPaga() {
        return estaPaga;
    }

    public void setEstaPaga(boolean estaPaga) {
        this.estaPaga = estaPaga;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = new DataFormatada(dataVencimento);
    }

    void pagar() {
        estaPaga = true;
    }

    public double getValor() {
        return valor;
    }

    public DataFormatada getDataVencimento() {
        return dataVencimento;
    }

    public boolean estaPaga() {
        return this.estaPaga;
    }

}
