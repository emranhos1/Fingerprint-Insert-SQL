/**
 *
 * @author Md. Emran Hossain
 */
package dao;

import dbConnection.DBConnectionSQL;
import dbConnection.conRs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertQueryDaoSQL {

    static DBConnectionSQL dbSQL = new DBConnectionSQL();
    static Connection con;
    static ResultSet rs;
    static PreparedStatement pstm;
    private static boolean pstm1;

    public static conRs insertQueryWithOutWhereClause(String tableName, String columnName, String values) throws SQLException {

        con = dbSQL.myConn();
        pstm = con.prepareStatement("Insert into " + tableName + "(" + columnName + ") values(" + values + ")");
        pstm1 = pstm.execute();
        conRs conrs = new conRs(con, pstm1);
        return conrs;
    }
}