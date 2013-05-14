/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author NewCom
 */
public class Connect {

    public Connection con = null;
    public Statement st = null;
    public ResultSet rs = null;

    public boolean connect() throws InstantiationException, IllegalAccessException {
        String conn = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/comsci_website?characterEncoding=UTF-8";
        String user = "comsci";
        String pass = "isyl]y[";
        try {
            Class.forName(conn).newInstance();
            try {
                con = (Connection) DriverManager.getConnection(url, user, pass);
                st = (Statement) con.createStatement();
            } catch (Exception Se) {
                return false;
            }
        } catch (ClassNotFoundException Cn) {
            return false;
        }
        return true;
    }

    public boolean disconnect() throws SQLException {
        try {
            st.close();
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void query(String sql) throws SQLException {
        rs = st.executeQuery(sql);
    }

    public int update(String sql) throws SQLException {
        return st.executeUpdate(sql);
    }

    public int insert(String sql) throws SQLException {
        return st.executeUpdate(sql);
    }

    public int delete(String sql) throws SQLException {
        return st.executeUpdate(sql);
    }

    public boolean next() throws SQLException {
        return rs.next();
    }

    public boolean first() throws SQLException {
        return rs.first();
    }

    public boolean last() throws SQLException {
        return rs.last();
    }

    public String getString(String sql) throws SQLException {
        String data = rs.getString(sql);
        return data;
    }

    public int getRow() throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int row = rsmd.getColumnCount();
        return row;
    }
}