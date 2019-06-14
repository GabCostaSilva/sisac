package sisac.dao;
import com.sisac.ConnectionFactory;
import com.sisac.models.Pagamento;
import sisac.ConnectionFactory;
import sisac.helpers.DataFormatada;
import sisac.models.Pagamento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PagamentoDAO {

    public void create(Pagamento pagamento) {
        Connection con = new ConnectionFactory().getConnection();

        String sql = "INSERT INTO tb_pagamentos (valor, data, tipo, id_aluno) VALUES(?,?,?,?);";
        try {
            PreparedStatement stmt = createStatement(pagamento, con, sql);

            stmt.execute();
            stmt.close();

            System.out.println("Pagamento efetuado");
            con.close();
         }
        catch(SQLException e) {
           e.printStackTrace();
        }
    }

    public ArrayList<Pagamento> get() {
        Connection con = new ConnectionFactory().getConnection();
        String query = "SELECT * FROM tb_pagamentos;";
        ArrayList<Pagamento> tb_pagamentos = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            Pagamento p;
            while(rs.next()) {
                int id = rs.getInt("id");
                double valor = rs.getDouble("valor");
                Date data = rs.getDate("data");
                LocalDate dataAux = data.toLocalDate();
                int tipo = rs.getInt("tipo");

                p = new Pagamento();
                p.setId(id);
                p.setValor(valor);
                p.setData(new DataFormatada(dataAux));
                p.setTipo(tipo);

                tb_pagamentos.add(a);
            }
            // Executa statement
            stmt.execute();
            stmt.close();
            con.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return tb_pagamentos;
    }

    private PreparedStatement createStatement(Pagamento pagamento, Connection con, String sql) throws SQLException{
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setDouble(1, pagamento.getValor());
            Date data = Date.valueOf(pagamento.getData());
            stmt.setDate(2, data);
            stmt.setString(3, pagamento.getTipo());

            return stmt;
    }
}
