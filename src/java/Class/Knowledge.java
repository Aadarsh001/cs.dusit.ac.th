/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Class.ContentData.Option;
import Servlet.index;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author NewSuppamit
 */
public class Knowledge {

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
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", new Locale("th", "TH"));
            String Date = dateFormat.format(new Date());
            String select = "SELECT * FROM knowledge "
                    + "WHERE startdate <= '" + Date + "' and "
                    + "status = '1' ORDER BY id_kno DESC LIMIT 0,5";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_kno", con.getString("id_kno"));
                jchil.put("title", con.getString("title"));
                jchil.put("detail", con.getString("detail"));
                jchil.put("image", con.getString("image"));
                jchil.put("startdate", con.getString("startdate"));
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
            String select = "SELECT * FROM knowledge "
                    + "ORDER BY id_kno DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_kno", con.getString("id_kno"));
                jchil.put("title", con.getString("title"));
                jchil.put("detail", con.getString("detail"));
                jchil.put("image", con.getString("image"));
                jchil.put("startdate", con.getString("startdate"));
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
            String select = "SELECT * FROM knowledge "
                    + "WHERE id_kno = '" + data.get("id_kno") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_kno", con.getString("id_kno"));
                json.put("title", con.getString("title"));
                json.put("detail", con.getString("detail"));
                json.put("image", con.getString("image"));
                json.put("startdate", con.getString("startdate"));
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
            String select = "select max(id_kno) as id_kno from knowledge";
            con.query(select);
            con.first();
            String id_kno = con.getString("id_kno");
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_kno = decimal_format.format(Integer.parseInt(id_kno) + 1);
            String insert = "insert into knowledge values('"
                    + id_kno + "','"
                    + json.get("title") + "','"
                    + json.get("detail") + "','"
                    + json.get("image") + "','"
                    + json.get("startdate") + "','"
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
            String update = "UPDATE knowledge SET "
                    + "title = '" + json.get("title") + "',"
                    + "detail = '" + json.get("detail") + "',"
                    + "image = '" + json.get("image") + "',"
                    + "startdate = '" + json.get("startdate") + "',"
                    + "status = '" + json.get("status") + "' "
                    + "WHERE id_kno = '" + json.get("id_kno") + "'";
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
            String select = "select image from knowledge "
                    + "WHERE id_kno = '" + json.get("id_kno") + "'";
            con.query(select);
            if (con.next()) {
                String[] filename = con.getString("image").split("[,]");
                for (int i = 0; i < filename.length; i++) {
                    File file = new File(json.get("path") + filename[i]);
                    file.delete();
                }
            }
            String delete = "delete from knowledge "
                    + "WHERE id_kno = '" + json.get("id_kno") + "'";
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
