/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Class.ContentData.Option;
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
public class Personnel {

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
            String select = "SELECT * FROM personnel "
                    + "WHERE status = '1' "
                    + "ORDER BY sequence ASC,id_per ASC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_per", con.getString("id_per"));
                jchil.put("name", con.getString("name"));
                jchil.put("position", con.getString("position"));
                jchil.put("detail", con.getString("detail"));
                jchil.put("sequence", con.getString("sequence"));
                jchil.put("image", con.getString("image"));
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
            String select = "SELECT * FROM personnel "
                    + "ORDER BY sequence ASC,id_per ASC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_per", con.getString("id_per"));
                jchil.put("name", con.getString("name"));
                jchil.put("position", con.getString("position"));
                jchil.put("detail", con.getString("detail"));
                jchil.put("sequence", con.getString("sequence"));
                jchil.put("image", con.getString("image"));
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
            String select = "SELECT * FROM personnel "
                    + "WHERE id_per = '" + data.get("id_per") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_per", con.getString("id_per"));
                json.put("name", con.getString("name"));
                json.put("position", con.getString("position"));
                json.put("sequence", con.getString("sequence"));
                json.put("detail", con.getString("detail"));
                json.put("image", con.getString("image"));
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
            String select = "select max(id_per) as id_per from personnel";
            con.query(select);
            con.next();
            String id_per = con.getString("id_per");
            if (id_per == null) {
                id_per = "0";
            }
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_per = decimal_format.format(Integer.parseInt(id_per) + 1);
            String insert = "insert into personnel values('"
                    + id_per + "','"
                    + json.get("name") + "','"
                    + json.get("position") + "','"
                    + json.get("detail") + "','"
                    + json.get("image") + "','"
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
            String update = "UPDATE personnel SET "
                    + "name = '" + json.get("name") + "',"
                    + "position = '" + json.get("position") + "',"
                    + "detail = '" + json.get("detail") + "',"
                    + "image = '" + json.get("image") + "',"
                    + "status = '" + json.get("status") + "',"
                    + "sequence = '" + json.get("sequence") + "' "
                    + "WHERE id_per = '" + json.get("id_per") + "'";
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
            String select = "select image from personnel "
                    + "WHERE id_per = '" + json.get("id_per") + "'";
            con.query(select);
            if (con.next()) {
                String filename = con.getString("image");
                if (!"".equals(filename)) {
                    File file = new File(json.get("path") + filename);
                    file.delete();
                }
            }
            String delete = "delete from personnel "
                    + "WHERE id_per = '" + json.get("id_per") + "'";
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