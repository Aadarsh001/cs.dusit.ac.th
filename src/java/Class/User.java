/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Class.ContentData.Option;
import Servlet.index;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author NewSuppamit
 */
public class User {

    public static String getData(String option, String detail) {
        switch (Option.valueOf(option)) {
            case login:
                return loginDB(detail);
            case check:
                return checkDB(detail);
            case all:
                return showAllDB();
            case some:
                return showSomeDB(detail);
            default:
                return null;
        }
    }

    public static boolean setData(String option, String data) {
        switch (Option.valueOf(option)) {
            case add:
                return addDB(data);
            case edit:
                return editDB(data);
            case remove:
                return removeDB(data);
            default:
                return false;
        }
    }

    private static String loginDB(String detail) {
        try {
            JSONObject data = (JSONObject) JSONValue.parse(detail);
            JSONObject json = new JSONObject();
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM user "
                    + "WHERE email = '" + data.get("email") + "' and "
                    + "password = '" + data.get("password") + "' and "
                    + "status != '0'";
            con.query(select);
            while (con.next()) {
                json.put("email", con.getString("email"));
                json.put("pname", con.getString("pname"));
                json.put("fname", con.getString("fname"));
                json.put("lname", con.getString("lname"));
                json.put("status", con.getString("status"));
            }
            con.disconnect();
            return json.toString();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private static String showAllDB() {
        try {
            JSONObject json = new JSONObject();
            JSONArray jarray = new JSONArray();
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM user";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                json.put("email", con.getString("email"));
                json.put("pname", con.getString("pname"));
                json.put("fname", con.getString("fname"));
                json.put("lname", con.getString("lname"));
                json.put("status", con.getString("status"));
                jarray.add(jchil);
            }
            json.put("data", jarray);
            con.disconnect();
            return json.toString();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private static String showSomeDB(String detail) {
        try {
            JSONObject data = (JSONObject) JSONValue.parse(detail);
            JSONObject json = new JSONObject();
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM user "
                    + "WHERE email = '" + data.get("email") + "'";
            con.query(select);
            while (con.next()) {
                json.put("email", con.getString("email"));
                json.put("pname", con.getString("pname"));
                json.put("fname", con.getString("fname"));
                json.put("lname", con.getString("lname"));
                json.put("password", con.getString("password"));
                json.put("status", con.getString("status"));
            }
            con.disconnect();
            return json.toString();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private static String checkDB(String detail) {
        try {
            JSONObject data = (JSONObject) JSONValue.parse(detail);
            JSONObject json = new JSONObject();
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM user "
                    + "WHERE email = '" + data.get("email") + "'";
            con.query(select);
            if (con.next()) {
                json.put("email", "already");
            } else {
                json.put("email", "ok");
            }
            con.disconnect();
            return json.toString();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private static boolean addDB(String data) {
        try {
            Connect con = new Connect();
            JSONObject json = (JSONObject) JSONValue.parse(data);
            con.connect();
            String insert = "insert into user values('"
                    + json.get("email") + "','"
                    + json.get("password") + "','"
                    + json.get("pname") + "','"
                    + json.get("fname") + "','"
                    + json.get("lname") + "','"
                    + json.get("status") + "')";
            if (con.insert(insert) > 0) {
                return true;
            }
            con.disconnect();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private static boolean editDB(String data) {
        try {
            Connect con = new Connect();
            JSONObject json = (JSONObject) JSONValue.parse(data);
            con.connect();
            String update = "UPDATE user SET "
                    + "password = '" + json.get("password") + "',"
                    + "pname = '" + json.get("pname") + "',"
                    + "fname = '" + json.get("fname") + "',"
                    + "lname = '" + json.get("lname") + "',"
                    + "status = '" + json.get("status") + "' "
                    + "WHERE email = '" + json.get("email") + "'";
            if (con.update(update) > 0) {
                return true;
            }
            con.disconnect();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private static boolean removeDB(String data) {
        try {
            Connect con = new Connect();
            JSONObject json = (JSONObject) JSONValue.parse(data);
            con.connect();
            String delete = "delete from user "
                    + "WHERE email = '" + json.get("email") + "'";
            if (con.delete(delete) > 0) {
                return true;
            }
            con.disconnect();
        } catch (Exception e) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}
