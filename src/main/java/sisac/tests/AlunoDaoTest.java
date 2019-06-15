package sisac.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import sisac.dao.AlunoDAO;
import sisac.helpers.CPFGenerator;
import sisac.helpers.DataFormatada;
import sisac.models.Aluno;

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

        assertEquals(0, alunoDAO.create(aluno));
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

        assertEquals(0, alunoDAO.update(aluno));
    }

    @Test void shouldListAlunos(){
        List<Aluno> alunos = alunoDAO.get();

        assertNotNull(alunos);
    }

}
