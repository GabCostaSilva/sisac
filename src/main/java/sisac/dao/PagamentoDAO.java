package sisac.dao;

import sisac.ConnectionFactory;
import sisac.helpers.DataFormatada;
import sisac.models.Aluno;
import sisac.models.Pagamento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public PagamentoDAO() {
    }

    public Pagamento create(Pagamento pagamento) {
        Connection con = new ConnectionFactory().getConnection();

        String sql = "INSERT INTO tb_pagamentos (valor, data, tipo, id_aluno) VALUES(?,?,?,?);";
        try {
            PreparedStatement stmt = createStatement(pagamento, sql);

            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0)
                throw new SQLException("Não foi possível criar pagamento");

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pagamento.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Falha ao criar pagamento. ID não obtido.");
                }
            }

            stmt.close();
            con.close();
         }
        catch(SQLException e) {
           e.printStackTrace();
        }
        return pagamento;
    }

    public List<Pagamento> get() {
        Connection con = new ConnectionFactory().getConnection();
        String query = "SELECT * FROM tb_pagamentos;";
        List<Pagamento> tb_pagamentos = new ArrayList();

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            Pagamento p;

            while(rs.next()) {
                int id = rs.getInt("id");
                double valor = rs.getDouble("valor");
                Date data = rs.getDate("data");
                int tipo = rs.getInt("tipo");

                p = new Pagamento();
                p.setId(id);
                p.setValor(valor);
                p.setData(new DataFormatada(data.toLocalDate()));
                p.setTipo(tipo);

                tb_pagamentos.add(p);
            }
            stmt.execute();
            stmt.close();
            con.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return tb_pagamentos;
    }

    public List<Pagamento> findByAlunoId(Aluno aluno) {
        Connection con = new ConnectionFactory().getConnection();
        ArrayList<Pagamento> pagamentos = new ArrayList<>();

        String sql = String.format("SELECT * FROM tb_pagamentos " +
                "WHERE id_aluno=%d;", aluno.getId());

        try(PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            Pagamento p;

            while(rs.next()){
                p = new Pagamento();
                p.setData(new DataFormatada(rs.getDate("data").toLocalDate()));
                p.setValor(rs.getDouble("valor"));
                p.setTipo(rs.getInt("tipo"));
                p.setId(rs.getLong("id"));

                pagamentos.add(p);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return pagamentos;
    }

    private PreparedStatement createStatement(Pagamento pagamento, String sql) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.setDouble(1, pagamento.getValor());
        Date data = Date.valueOf(pagamento.getData());
        stmt.setDate(2, data);
        stmt.setInt(3, pagamento.getTipo());
        stmt.setLong(4, pagamento.getIdAluno());
        return stmt;
    }
}
