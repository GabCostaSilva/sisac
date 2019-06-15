package sisac.dao;

import sisac.ConnectionFactory;
import sisac.models.Exame;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExameDao {
    private Connection con;

    public ExameDao() {
        con = new ConnectionFactory().getConnection();
    }

    public void create(Exame exame){

        String sql = "INSERT INTO tb_exames (" +
                "custo, data) VALUES (?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDouble(1, exame.getCusto());
            stmt.setDate(2, Date.valueOf(exame.getDataHorario().getData()));

            stmt.execute();
            stmt.close();

            System.out.println("Exame criado");
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
