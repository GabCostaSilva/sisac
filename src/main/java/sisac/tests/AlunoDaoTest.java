package sisac.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import sisac.dao.AlunoDAO;
import sisac.helpers.CPFGenerator;
import sisac.helpers.DataFormatada;
import sisac.models.Aluno;

import java.sql.SQLException;
import java.util.List;

class AlunoDaoTest {
    private AlunoDAO alunoDAO = new AlunoDAO();

    @Test
     void shouldCreateAluno() {
        Aluno aluno = new Aluno();

        aluno.setNome("João das Neves");
        aluno.setEndereco("Rua da Amargura, São Paulo - SP");
        aluno.setTelefone("1199933333");
        aluno.setDataMatricula(new DataFormatada("12/12/2010"));
        aluno.setDataFimMatricula(new DataFormatada("12/12/2013"));
        aluno.setCpf(CPFGenerator.generate());
        aluno.setGraduacao(2);

        long id = alunoDAO.create(aluno).getId();

        assertEquals(aluno.getId(), id);
    }

    @Test void shouldUpdateAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("João das Neves");
        aluno.setEndereco("Rua da Amargura, São Paulo - SP");
        aluno.setTelefone("1199933333");
        aluno.setDataMatricula(new DataFormatada("12/12/2010"));
        aluno.setDataFimMatricula(new DataFormatada("12/12/2011"));
        aluno.setCpf(CPFGenerator.generate());
        aluno.setGraduacao(2);

        long id = alunoDAO.update(aluno).getId();
        assertEquals(aluno.getId(), id);
    }

    @Test void shouldListAlunos(){
        try {
            List<Aluno> alunos = alunoDAO.get();
            assertNotNull(alunos);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
