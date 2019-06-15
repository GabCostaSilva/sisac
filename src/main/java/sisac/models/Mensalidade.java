package sisac.models;

import sisac.helpers.DataFormatada;

public class Mensalidade {

    private int id;
    private double valor;
    private boolean estaPaga;
    private DataFormatada dataVencimento;

    public Mensalidade() {

    }

    public Mensalidade(String data, double valor) {
        this.dataVencimento = new DataFormatada(data);
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
