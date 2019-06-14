package sisac.models;

import java.util.ArrayList;

public class HistoricoDeTentativas {

    public ArrayList<Exame> exames;
    public Aluno aluno;

    public void adicionarExame(Exame exame) {
        exames.add(exame);
    }
}
