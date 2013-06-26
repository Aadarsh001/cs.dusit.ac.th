/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import static Class.ContentData.Option.add;
import static Class.ContentData.Option.all;
import static Class.ContentData.Option.edit;
import static Class.ContentData.Option.remove;
import static Class.ContentData.Option.show;
import static Class.ContentData.Option.some;
import Servlet.index;
import java.io.File;
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
public class Lab {

    public static String getData(String option, String detail) {
        switch (ContentData.Option.valueOf(option)) {
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
        switch (ContentData.Option.valueOf(option)) {
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
            String select = "SELECT * FROM lab "
                    + "WHERE status != '0' "
                    + "ORDER BY id_lab DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_lab", con.getString("id_lab"));
                jchil.put("title", con.getString("title"));
                jchil.put("link", con.getString("link"));
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
            String select = "SELECT * FROM lab "
                    + "ORDER BY id_lab DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_lab", con.getString("id_lab"));
                jchil.put("title", con.getString("title"));
                jchil.put("link", con.getString("link"));
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
            String select = "SELECT * FROM lab "
                    + "WHERE id_lab = '" + data.get("id_lab") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_lab", con.getString("id_lab"));
                json.put("title", con.getString("title"));
                json.put("link", con.getString("link"));
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
            String select = "select max(id_lab) as id_lab from lab";
            con.query(select);
            con.next();
            String id_lab = con.getString("id_lab");
            if (id_lab == null) {
                id_lab = "0";
            }
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_lab = decimal_format.format(Integer.parseInt(id_lab) + 1);
            String insert = "insert into lab values('"
                    + id_lab + "','"
                    + json.get("title") + "','"
                    + json.get("link") + "','"
                    + json.get("status") + "')";
            System.err.println(insert);
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
            String update = "UPDATE lab SET "
                    + "title = '" + json.get("title") + "',"
                    + "link = '" + json.get("link") + "',"
                    + "status = '" + json.get("status") + "' "
                    + "WHERE id_lab = '" + json.get("id_lab") + "'";
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
            String delete = "delete from lab "
                    + "WHERE id_lab = '" + json.get("id_lab") + "'";
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
