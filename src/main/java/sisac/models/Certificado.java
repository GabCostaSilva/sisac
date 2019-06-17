package sisac.models;

public class Certificado {
    public Aluno aluno;

    public Certificado(Aluno aluno) {
        this.aluno = aluno;
    }

    public String toString(){
        return String.format(
                "Certificamos que %s foi graduado para a faixa %s",
                aluno.getNome(), aluno.getFaixa()
        );
    }
}
