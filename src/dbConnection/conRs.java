/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class conRs {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    boolean pstm1;

    public boolean isPstm1() {
        return pstm1;
    }

    public void setPstm1(boolean pstm1) {
        this.pstm1 = pstm1;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public PreparedStatement getPstm() {
        return pstm;
    }

    public void setPstm(PreparedStatement pstm) {
        this.pstm = pstm;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public conRs(Connection con) {
        this.con = con;
    }

    public conRs(Connection con, boolean pstm1) {
        this.con = con;
        this.pstm1 = pstm1;
    }
    
    public conRs(Connection con, ResultSet rs, PreparedStatement pstm) {
        this.con = con;
        this.rs = rs;
        this.pstm = pstm;
    }
    
}
