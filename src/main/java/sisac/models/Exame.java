package sisac.models;

import sisac.helpers.DataFormatada;

public class Exame {
    private DataFormatada dataHorario;
    private double custo;
    private boolean aprovado;

    public Exame(DataFormatada dataHorario, double custo) {
        this.dataHorario = dataHorario;
        aprovado = false;
        this.custo = custo;
    }

    public DataFormatada getDataHorario() {
        return dataHorario;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public String convidar(Aluno aluno) {
        return String.format("Olá %s, você foi convidadado a realizar o exame no dia %s",
                aluno.getNome(), dataHorario.getDataToString());
    }
}
