package sisac.models;
import sisac.helpers.DataFormatada;
import java.time.LocalDate;

public class Pagamento {
    private long id;
    private double valor;
    private DataFormatada data;
    private final int[] tipos = {0, 1}; // 0 - Cartão, 1 - dinheiro
    private int tipo;

    public Pagamento() {
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(DataFormatada data) {
        this.data = data;
    }

    public Pagamento(double valor, DataFormatada data, int tipo) {
        this.valor = valor;
        this.data = data;
        try {
            this.tipo = tipos[tipo];
        } catch(IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("O tipo deve ser 0 ou 1");
        }
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }
    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data.getData();
    }

    public DataFormatada getDataFormatada() {
        return data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
