package sisac.models;

import java.time.LocalDate;

public class Matricula {

    private int id;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private int status;
    private Aluno aluno;

    public Matricula(int id, Aluno aluno) {
        this.id = id;
        this.status = 1;
        this.aluno = aluno;
    }

    public void trancar(){
        this.status = 0;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void destrancar(){
        this.status = 1;
    }
}
