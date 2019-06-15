package sisac.dao;

import sisac.ConnectionFactory;
import sisac.models.Matricula;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatriculaDao {
    private Connection con;

    public MatriculaDao() {
        con = new ConnectionFactory().getConnection();
    }

    public PreparedStatement createStatement(String sql, Matricula matricula) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sql);

        Date dataInicial = Date.valueOf(matricula.getDataInicial());
        Date dataFinal = Date.valueOf(matricula.getDataFinal());

        stmt.setDate(1, dataInicial);
        stmt.setDate(2, data);
        stmt.setString(3, matricula.getTipo());
        stmt.setInt(4, matricula.getAluno().getId());
        return stmt;
    }
}
