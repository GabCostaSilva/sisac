package sisac.dao;
import sisac.ConnectionFactory;
import sisac.models.Aluno;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AlunoDAO {

    public AlunoDAO() {

    }

    public void create(Aluno aluno) {
        Connection con = new ConnectionFactory().getConnection();

        String sql = "INSERT INTO tb_alunos (" +
                        "nome, endereco, telefone" +
                        "id_exames, " +
                        "id_certificados, " +
                        "data_matricula, " +
                        "data_limite_matricula)" +
                        " VALUES(?,?,?,?,?,?,?);";

        try {
            PreparedStatement stmt = createStatement(aluno, con, sql);

            // Executa statement
            stmt.execute();
            stmt.close();

            System.out.println("Aluno adicionado");
            con.close();
         }
        catch(SQLException e) {
           e.printStackTrace();
        }
    }

    private PreparedStatement createStatement(Aluno aluno, Connection con, String sql) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getEndereco());
        stmt.setString(3, aluno.getTelefone());
        stmt.setInt(4, aluno.getIdExames());
        stmt.setInt(5, aluno.getIdCertificados());
        Date dataMatricula = Date.valueOf(aluno.getMatricula().getDataInicial());
        stmt.setDate(6, dataMatricula);
        Date dataFinal = Date.valueOf(aluno.getMatricula().getDataFinal());
        stmt.setDate(6, dataFinal);

        return stmt;
    }

    public void delete(int id) {
        Connection con = new ConnectionFactory().getConnection();
        String sql = String.format("DELETE FROM tb_alunos " +
                "WHERE id=%d", id);
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            // Executa statement
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
        Connection con = new ConnectionFactory().getConnection();
        String sql = String.format("UPDATE tb_alunos SET nome=?, endereco=?, " +
                "data_matricula=?, data_limite_matricula=? " +
                "WHERE id=%d", aluno.getId());
        try {
           PreparedStatement stmt = createStatement(aluno, con, sql);

            // Executa statement
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
        Connection con = new ConnectionFactory().getConnection();
        String query = "SELECT * FROM tb_alunos";
        ArrayList<Aluno> tb_alunos = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            Aluno a;
            while(rs.next()) {
                int id = rs.getInt("id");
                String nomeAluno = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                Date dataMatricula = rs.getDate("data_matricula");
                Date dataLimiteMatricula = rs.getDate("data_limite_matricula");

                LocalDate dataMatriculaAlt = dataMatricula.toLocalDate();
                LocalDate dataLimiteMatriculaAlt = dataLimiteMatricula.toLocalDate();

                a = new Aluno();

                a.setId(id);
                a.setNome(nomeAluno);
                a.setEndereco(endereco);
                a.setCidade(cidade);
                a.setEstado(estado);
                a.setTelefone(telefone);
                a.getMatricula().setDataInicial(dataMatriculaAlt);
                a.getMatricula().setDataFinal(dataLimiteMatriculaAlt);

                tb_alunos.add(a);
            }
            // Executa statement
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
