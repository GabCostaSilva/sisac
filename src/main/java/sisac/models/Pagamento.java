package sisac.models;
import sisac.helpers.DataFormatada;
import java.time.LocalDate;

public class Pagamento {

    private int id;
    private double valor;
    private DataFormatada data;
    private Aluno aluno;
    private int tipo;

    public void setData(DataFormatada data) {
        this.data = data;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Pagamento() {
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Pagamento(Aluno aluno, double valor, DataFormatada data, int tipo) {
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.aluno = aluno;
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

    public String getTipo() {
        if(tipo == 0)
            return "Dinheiro";

        return "Cartão";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
