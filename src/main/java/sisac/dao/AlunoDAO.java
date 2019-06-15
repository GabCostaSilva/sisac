package sisac.dao;

import sisac.ConnectionFactory;
import sisac.helpers.DataFormatada;
import sisac.models.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private Connection con;

    public AlunoDAO() {
        con = new ConnectionFactory().getConnection();
    }

    public int create(Aluno aluno) {

        String sql = "INSERT INTO tb_alunos (" +
                        "nome, endereco, telefone, " +
                        "data_matricula, " +
                        "data_matricula_fim, matriculado)" +
                        " VALUES(?,?,?,?,?,?);";

        try {
            PreparedStatement stmt = createStatement(aluno, sql);

            stmt.execute();
            stmt.close();

            System.out.println("Aluno adicionado");
            con.close();
         }

        catch(SQLException e) {
           e.printStackTrace();
        }

        return 0;
    }

    private PreparedStatement createStatement(Aluno aluno, String sql) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getEndereco());
        stmt.setString(3, aluno.getTelefone());
        stmt.setDate(4, Date.valueOf(aluno.getDataMatricula().getData()));
        stmt.setDate(5, Date.valueOf(aluno.getDataMatricula().getData()));
        stmt.setBoolean(6, aluno.isMatriculado());

        return stmt;
    }

    public void delete(int id) {
        String sql = String.format("DELETE FROM tb_alunos " +
                "WHERE id=%d", id);
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.execute();
            stmt.close();
            System.out.println("Aluno removido.");
            con.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Aluno aluno) {
        String sql = String.format("UPDATE tb_alunos SET nome=?, endereco=?, " +
                "telefone=?, data_matricula=?, data_limite_matricula=?, matriculado=? " +
                "WHERE id=%d", aluno.getId());
        try {
           PreparedStatement stmt = createStatement(aluno, sql);

            stmt.execute();
            stmt.close();
            System.out.println("Aluno atualizado");
            con.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> get() {
        String query = "SELECT * FROM tb_alunos";

        return createAlunos(query);
    }

    public List<Aluno> findByName(String name) {
        String query = String.format("SELECT * FROM tb_alunos WHERE name like %s", name);
        return createAlunos(query);
    }

    private List<Aluno> createAlunos(String query) {
        ArrayList<Aluno> alunos = new ArrayList<>();

        try(PreparedStatement stmt = con.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            Aluno a;

            while(rs.next()) {
                String nomeAluno = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                Date dataMatricula = rs.getDate("data_matricula");
                Date dataLimiteMatricula = rs.getDate("data_limite_matricula");
                boolean matriculado = rs.getBoolean("matriculado");

                DataFormatada dataMatriculaAlt = new DataFormatada(dataMatricula.toLocalDate());
                DataFormatada dataLimiteMatriculaAlt = new DataFormatada(dataLimiteMatricula.toLocalDate());

                a = new Aluno();

                a.setNome(nomeAluno);
                a.setEndereco(endereco);
                a.setTelefone(telefone);
                a.setDataMatricula(dataMatriculaAlt);
                a.setDataFimMatricula(dataLimiteMatriculaAlt);
                a.setMatriculado(matriculado);

                alunos.add(a);
            }
            stmt.execute();
            con.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }




}
