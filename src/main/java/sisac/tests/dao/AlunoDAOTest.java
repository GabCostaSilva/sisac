package sisac.tests.dao;


import com.sisac.dao.AlunoDAO;
import com.sisac.models.Aluno;

import java.time.LocalDate;
import java.util.ArrayList;

public class AlunoDAOTest {
    public static void test() {


        Aluno aluno1 = new Aluno();
        Aluno aluno2 = new Aluno();
        AlunoDAO dao = new AlunoDAO();

        aluno1.setNome("Maria");
        aluno1.setCidade("Birigui");
        aluno1.setEndereco("Rua 13");
        aluno1.setEstado("SP");
        aluno1.setTelefone("1199999999");
        aluno1.getMatricula().setDataInicial(LocalDate.of(2010, 04, 12));
        aluno1.getMatricula().setDataFinal(LocalDate.of(2013, 04, 12));

        aluno2.setNome("Joao");
        aluno2.setCidade("Lino");
        aluno2.setEndereco("Rua 14");
        aluno2.setEstado("SP");
        aluno2.setTelefone("1199999899");
        aluno1.getMatricula().setDataInicial(LocalDate.of(2010, 04, 12));
        aluno1.getMatricula().setDataFinal(LocalDate.of(2013, 04, 12));

        dao.addAluno(aluno1);
        dao.addAluno(aluno2);

        dao.removeAluno(2);
        aluno2.getMatricula().setDataInicial(LocalDate.of(2012, 12, 12));

        dao.updateAluno(aluno2);

        ArrayList<Aluno> alunos = dao.fetchAlunoByName("Joao");
        for(Aluno aluno: alunos){
            System.out.println("Aluno: " + aluno.toString());
        }
    }

}
