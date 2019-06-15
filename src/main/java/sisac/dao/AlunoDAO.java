package sisac.dao;

import sisac.ConnectionFactory;
import sisac.helpers.DataFormatada;
import sisac.models.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {



    public AlunoDAO() {

    }

    public Aluno create(Aluno aluno) {
        Connection con = new ConnectionFactory().getConnection();
        String sql = "INSERT INTO tb_alunos (" +
                        "nome, endereco, telefone, " +
                        "data_matricula, " +
                        "data_matricula_fim, status, faixa, cpf)" +
                        " VALUES(?,?,?,?,?,?,?,?);";

        try {
            PreparedStatement stmt = createStatement(aluno, sql);
            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0)
                throw new SQLException("Não foi possível criar aluno");

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    aluno.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            stmt.close();
            con.close();
         }

        catch(SQLException e) {
           e.printStackTrace();
        }
        System.out.println("Aluno adicionado");
        return aluno;
    }

    private PreparedStatement createStatement(Aluno aluno, String sql) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getEndereco());
        stmt.setString(3, aluno.getTelefone());
        stmt.setDate(4, Date.valueOf(aluno.getDataMatricula().getData()));
        stmt.setDate(5, Date.valueOf(aluno.getDataMatricula().getData()));
        stmt.setInt(6, aluno.getStatus());
        stmt.setString(7, aluno.getFaixa());
        stmt.setString(8, aluno.getCpf());

        return stmt;
    }

    public int delete(String cpf) {
        Connection con = new ConnectionFactory().getConnection();
        String sql = String.format("DELETE FROM tb_alunos " +
                "WHERE cpf like %s", cpf);
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.execute();
            stmt.close();
            con.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Aluno removido.");

        return 0;
    }

    public Aluno update(Aluno aluno) {
        Connection con = new ConnectionFactory().getConnection();
        String sql = String.format("UPDATE tb_alunos SET nome=?, endereco=?, " +
                "telefone=?, data_matricula=?, data_matricula_fim=?, status=?, cpf=?, faixa=?" +
                "WHERE cpf like %s", aluno.getCpf());
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

        return aluno;
    }

    public List<Aluno> get() {
        String query = "SELECT * FROM tb_alunos";

        return createAlunos(query);
    }

    public List<Aluno> findByCpf(String cpf) {
        String query = String.format("SELECT * FROM tb_alunos WHERE cpf LIKE \"%s\"", cpf);
        return createAlunos(query);
    }

    private List<Aluno> createAlunos(String query) {
        Connection con = new ConnectionFactory().getConnection();
        ArrayList<Aluno> alunos = new ArrayList<>();

        try(PreparedStatement stmt = con.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            Aluno a;

            while(rs.next()) {
                String nomeAluno = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                Date dataMatricula = rs.getDate("data_matricula");
                Date dataLimiteMatricula = rs.getDate("data_matricula_fim");
                int status = rs.getInt("status");
                String faixa = rs.getString("faixa");
                String cpf = rs.getString("cpf");

                a = new Aluno();

                a.setNome(nomeAluno);
                a.setEndereco(endereco);
                a.setTelefone(telefone);
                a.setDataMatricula(new DataFormatada(dataMatricula.toLocalDate()));
                a.setDataFimMatricula(new DataFormatada(dataLimiteMatricula.toLocalDate()));
                a.setStatus(status);
                a.setCpf(cpf);
                a.setFaixa(faixa);

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
