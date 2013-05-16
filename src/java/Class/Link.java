/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Class.ContentData.Option;
import Servlet.index;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author NewSuppamit
 */
public class Link {

    public static String getData(String option, String detail) {
        switch (Option.valueOf(option)) {
            case show:
                return showDB();
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

    private static String showDB() {
        try {
            JSONObject json = new JSONObject();
            JSONArray jarray = new JSONArray();
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM link "
                    + "WHERE status = '1' "
                    + "ORDER BY sequence ASC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_lin", con.getString("id_lin"));
                jchil.put("title", con.getString("title"));
                jchil.put("link", con.getString("link"));
                jchil.put("sequence", con.getString("sequence"));
                jchil.put("status", con.getString("status"));
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

    private static String showAllDB() {
        try {
            JSONObject json = new JSONObject();
            JSONArray jarray = new JSONArray();
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM link "
                    + "ORDER BY id_lin DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_lin", con.getString("id_lin"));
                jchil.put("title", con.getString("title"));
                jchil.put("link", con.getString("link"));
                jchil.put("sequence", con.getString("sequence"));
                jchil.put("status", con.getString("status"));
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
            String select = "SELECT * FROM link "
                    + "WHERE id_lin = '" + data.get("id_lin") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_lin", con.getString("id_lin"));
                json.put("title", con.getString("title"));
                json.put("link", con.getString("link"));
                json.put("sequence", con.getString("sequence"));
                json.put("status", con.getString("status"));
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
            String select = "select max(id_lin) as id_lin from link";
            con.query(select);
            String id_lin;
            if (con.next()) {
                id_lin = con.getString("id_lin");
            } else {
                id_lin = "0";
            }
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_lin = decimal_format.format(Integer.parseInt(id_lin) + 1);
            String insert = "insert into link values('"
                    + id_lin + "','"
                    + json.get("title") + "','"
                    + json.get("link") + "','"
                    + json.get("sequence") + "','"
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
            String update = "UPDATE link SET "
                    + "title = '" + json.get("title") + "',"
                    + "link = '" + json.get("link") + "',"
                    + "sequence = '" + json.get("sequence") + "',"
                    + "status = '" + json.get("status") + "' "
                    + "WHERE id_lin = '" + json.get("id_lin") + "'";
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
            String delete = "delete from link "
                    + "WHERE id_lin = '" + json.get("id_lin") + "'";
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