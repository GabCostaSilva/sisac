package sisac.models;

public class Certificado {
    public Aluno aluno;
    private Exame exame;

    public Certificado(Aluno aluno, Exame exame) {
        this.aluno = aluno;
        this.exame = exame;
    }

    public String emitir(){
        return String.format(
                "Certificamos que %s foi graduado para a faixa %s no exame do dia %s.",
                aluno.getNome(), aluno.getFaixa(), exame.getDataHorario().getDataToString()
        );
    }
}
