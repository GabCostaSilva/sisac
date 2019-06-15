package sisac.dao;

import sisac.ConnectionFactory;
import sisac.helpers.DataFormatada;
import sisac.models.Aluno;

import java.sql.*;
import java.util.ArrayList;

public class AlunoDAO {
    private Connection con;

    public AlunoDAO() {
        con = new ConnectionFactory().getConnection();
    }

    public void create(Aluno aluno) {

        String sql = "INSERT INTO tb_alunos (" +
                        "nome, endereco, telefone" +
                        "data_matricula, " +
                        "data_limite_matricula, matriculado)" +
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
    }

    private PreparedStatement createStatement(Aluno aluno, String sql) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getEndereco());
        stmt.setString(3, aluno.getTelefone());
        Date dataMatricula = Date.valueOf(aluno.getDataMatricula().getData());
        stmt.setDate(4, dataMatricula);
        Date dataFinal = Date.valueOf(aluno.getDataMatricula().getData());
        stmt.setDate(5, dataFinal);
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
                "telefone=?, data_matricula=?, data_limite_matricula=? " +
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

    public ArrayList<Aluno> get() {
        String query = "SELECT * FROM tb_alunos";
        ArrayList<Aluno> tb_alunos = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(query);
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

                tb_alunos.add(a);
            }
            stmt.execute();
            stmt.close();
            con.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return tb_alunos;
    }
}
