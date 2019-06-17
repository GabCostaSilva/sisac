package sisac.dao;

import sisac.ConnectionFactory;
import sisac.models.Mensalidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensalidadeDAO {

    public Mensalidade create(Mensalidade mensalidade) {
        Connection con = new ConnectionFactory().getConnection();

        String sql = "INSERT INTO tb_mensalidades (valor, data_vencimento, esta_paga, id_pagamento) VALUES(?,?,?,?);";

        try {
            PreparedStatement stmt = createStatement(mensalidade, sql);

            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0)
                throw new SQLException("Não foi possível criar mensalidade");

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    mensalidade.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Falha ao criar mensalidade. ID não obtido.");
                }
            }

            stmt.close();
            con.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return mensalidade;

    }

    public Mensalidade update(Mensalidade mensalidade){
            Connection con = new ConnectionFactory().getConnection();
            String sql = String.format("UPDATE tb_mensalidades SET valor=?, data_vencimento=?, " +
                    "esta_paga=?" +
                    "WHERE id=", mensalidade.getId());
            try {
                PreparedStatement stmt = createStatement(mensalidade, sql);

                stmt.execute();
                stmt.close();
                con.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }

            return mensalidade;
    }

    public Mensalidade delete(Mensalidade mensalidade){
        Connection con = new ConnectionFactory().getConnection();
        String sql = String.format("DELETE FROM tb_mensalidades " +
                "WHERE id=%d", mensalidade.getId());
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.execute();
            stmt.close();
            con.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return mensalidade;
    }

    public List<Mensalidade> findById(Mensalidade mensalidade) {
        String query = String.format("SELECT * FROM tb_mensalidades WHERE id=%d", mensalidade.getId());

        return createMensalidades(query);
    }

    private List<Mensalidade> createMensalidades(String query) {
        Connection con = new ConnectionFactory().getConnection();
        ArrayList<Mensalidade> mensalidades = new ArrayList<>();

        try(PreparedStatement stmt = con.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            Mensalidade mensalidade;

            while(rs.next()) {
                long id = rs.getLong("id");
                Date dataVencimento = rs.getDate("data_vencimento");
                double valor = rs.getDouble("valor");
                boolean estaPaga = rs.getBoolean("esta_paga");
                long idPagamento = rs.getLong("id_pagamento");

                mensalidade = new Mensalidade();

                mensalidade.setId(id);
                mensalidade.setValor(valor);
                mensalidade.setEstaPaga(estaPaga);
                mensalidade.setDataVencimento(dataVencimento.toLocalDate());
                mensalidade.setPagamentoId(idPagamento);

                mensalidades.add(mensalidade);
            }
            stmt.execute();
            con.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return mensalidades;
    }
    
    private PreparedStatement createStatement(Mensalidade mensalidade, String sql) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();

        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.setDouble(1, mensalidade.getValor());
        stmt.setDate(2, Date.valueOf(mensalidade.getDataVencimento().getData()));
        stmt.setBoolean(3, mensalidade.estaPaga());
        stmt.setLong(4, mensalidade.getPagamentoId());
        return  stmt;
    }
}
