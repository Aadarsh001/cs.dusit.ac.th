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
public class Academic {

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
            String select = "SELECT * FROM academic "
                    + "WHERE status = '1' "
                    + "ORDER BY id_aca DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_aca", con.getString("id_aca"));
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
            String select = "SELECT * FROM academic "
                    + "ORDER BY id_aca DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_aca", con.getString("id_aca"));
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
            String select = "SELECT * FROM academic "
                    + "WHERE id_aca = '" + data.get("id_aca") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_aca", con.getString("id_aca"));
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
            String select = "select max(id_aca) as id_aca from academic";
            con.query(select);
            con.first();
            String id_aca;
            if (con.next()) {
                id_aca = con.getString("id_aca");
            } else {
                id_aca = "0";
            }
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_aca = decimal_format.format(Integer.parseInt(id_aca) + 1);
            String insert = "insert into academic values('"
                    + id_aca + "','"
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
            String update = "UPDATE academic SET "
                    + "owner = '" + json.get("owner") + "',"
                    + "title = '" + json.get("title") + "',"
                    + "file = '" + json.get("file") + "',"
                    + "status = '" + json.get("status") + "' "
                    + "WHERE id_aca = '" + json.get("id_aca") + "'";
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
            String select = "select file from academic "
                    + "WHERE id_aca = '" + json.get("id_aca") + "'";
            con.query(select);
            if (con.next()) {
                String filename = con.getString("file");
                if (!"".equals(filename)) {
                    File file = new File(json.get("path") + filename);
                    file.delete();
                }
            }
            String delete = "delete from academic "
                    + "WHERE id_aca = '" + json.get("id_aca") + "'";
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