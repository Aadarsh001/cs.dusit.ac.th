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
public class Download {
    
    public static String getData(String option, String detail) {
        switch (ContentData.Option.valueOf(option)) {
            case show:
                return showDB(detail);
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
    
    private static String showDB(String detail) {
        try {
            JSONObject data = (JSONObject) JSONValue.parse(detail);
            JSONObject json = new JSONObject();
            JSONArray jarray = new JSONArray();
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM download "
                    + "WHERE id_gro = '" + data.get("id_gro") + "' and status = '1' "
                    + "ORDER BY id_dow DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_dow", con.getString("id_dow"));
                jchil.put("id_gro", con.getString("id_gro"));
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
            String select = "SELECT * FROM download "
                    + "ORDER BY id_dow DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_dow", con.getString("id_dow"));
                jchil.put("id_gro", con.getString("id_gro"));
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
            String select = "SELECT * FROM download "
                    + "WHERE id_dow = '" + data.get("id_dow") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_dow", con.getString("id_dow"));
                json.put("id_gro", con.getString("id_gro"));
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
            String select = "select max(id_dow) as id_dow from download";
            con.query(select);
            con.next();
            String id_dow = con.getString("id_dow");
            if (id_dow == null) {
                id_dow = "0";
            }
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_dow = decimal_format.format(Integer.parseInt(id_dow) + 1);
            String insert = "insert into download values('"
                    + id_dow + "','"
                    + json.get("id_gro") + "','"
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
            String update = "UPDATE download SET "
                    + "id_gro = '" + json.get("id_gro") + "',"
                    + "title = '" + json.get("title") + "',"
                    + "file = '" + json.get("file") + "',"
                    + "status = '" + json.get("status") + "' "
                    + "WHERE id_dow = '" + json.get("id_dow") + "'";
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
            String select = "select file from download "
                    + "WHERE id_dow = '" + json.get("id_dow") + "'";
            con.query(select);
            if (con.next()) {
                String filename = con.getString("file");
                if (!"".equals(filename)) {
                    File file = new File(json.get("path") + filename);
                    file.delete();
                }
            }
            String delete = "delete from download "
                    + "WHERE id_dow = '" + json.get("id_dow") + "'";
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
