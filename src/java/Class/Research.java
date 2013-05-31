/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

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
public class Research {

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
            String select = "SELECT * FROM research "
                    + "WHERE status = '1' "
                    + "ORDER BY id_res DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_res", con.getString("id_res"));
                jchil.put("owner", con.getString("owner"));
                jchil.put("title", con.getString("title"));
                jchil.put("file", con.getString("file"));
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
            String select = "SELECT * FROM research "
                    + "ORDER BY id_res DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_res", con.getString("id_res"));
                jchil.put("owner", con.getString("owner"));
                jchil.put("title", con.getString("title"));
                jchil.put("file", con.getString("file"));
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
            String select = "SELECT * FROM research "
                    + "WHERE id_res = '" + data.get("id_res") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_res", con.getString("id_res"));
                json.put("owner", con.getString("owner"));
                json.put("title", con.getString("title"));
                json.put("file", con.getString("file"));
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
            String select = "select max(id_res) as id_res from research";
            con.query(select);
            con.next();
            String id_res = con.getString("id_res");
            if (id_res == null) {
                id_res = "0";
            }
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_res = decimal_format.format(Integer.parseInt(id_res) + 1);
            String insert = "insert into research values('"
                    + id_res + "','"
                    + json.get("owner") + "','"
                    + json.get("title") + "','"
                    + json.get("file") + "','"
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
            String update = "UPDATE research SET "
                    + "owner = '" + json.get("owner") + "',"
                    + "title = '" + json.get("title") + "',"
                    + "status = '" + json.get("status") + "' "
                    + "WHERE id_res = '" + json.get("id_res") + "'";
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
            String select = "select file from research "
                    + "WHERE id_res = '" + json.get("id_res") + "'";
            con.query(select);
            if (con.next()) {
                String filename = con.getString("file");
                if (!"".equals(filename)) {
                    File file = new File(json.get("path") + filename);
                    file.delete();
                    String[] path = filename.split("[/]");
                    int no_path = path.length;
                    filename = "";
                    for (int i = 0; i < (no_path - 1); i++) {
                        filename += path[i] + "/";
                    }
                    file = new File(json.get("path") + filename);
                    file.delete();
                }
            }
            String delete = "delete from research "
                    + "WHERE id_res = '" + json.get("id_res") + "'";
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