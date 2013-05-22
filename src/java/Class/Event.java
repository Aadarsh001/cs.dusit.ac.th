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
public class Event {

    public static String getData(String option, String detail) {
        switch (Option.valueOf(option)) {
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

    private static String showDB(String detail) {
        try {
            JSONObject data = (JSONObject) JSONValue.parse(detail);
            JSONObject json = new JSONObject();
            JSONArray jarray = new JSONArray();
            Connect con = new Connect();
            con.connect();
            int page = Integer.parseInt(data.get("page").toString());
            int rp = Integer.parseInt(data.get("rp").toString());
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", new Locale("th", "TH"));
            String Date = dateFormat.format(new Date());
            String select = "SELECT * FROM event "
                    + "WHERE startdate <= '" + Date + "' and "
                    + "enddate >= '" + Date + "' and "
                    + "status = '1' ORDER BY id_eve DESC LIMIT " + rp * (page - 1) + "," + rp * page + "";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_eve", con.getString("id_eve"));
                jchil.put("title", con.getString("title"));
                jchil.put("detail", con.getString("detail"));
                jchil.put("image", con.getString("image"));
                jchil.put("startdate", con.getString("startdate"));
                jchil.put("enddate", con.getString("enddate"));
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
            String select = "SELECT * FROM event "
                    + "ORDER BY id_eve DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_eve", con.getString("id_eve"));
                jchil.put("title", con.getString("title"));
                jchil.put("detail", con.getString("detail"));
                jchil.put("image", con.getString("image"));
                jchil.put("startdate", con.getString("startdate"));
                jchil.put("enddate", con.getString("enddate"));
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
            String select = "SELECT * FROM event "
                    + "WHERE id_eve = '" + data.get("id_eve") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_eve", con.getString("id_eve"));
                json.put("title", con.getString("title"));
                json.put("detail", con.getString("detail"));
                json.put("image", con.getString("image"));
                json.put("startdate", con.getString("startdate"));
                json.put("enddate", con.getString("enddate"));
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
            String select = "select max(id_eve) as id_eve from event";
            con.query(select);
            con.next();
            String id_eve = con.getString("id_eve");
            if (id_eve == null) {
                id_eve = "0";
            }
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_eve = decimal_format.format(Integer.parseInt(id_eve) + 1);
            String insert = "insert into event values('"
                    + id_eve + "','"
                    + json.get("title") + "','"
                    + json.get("detail") + "','"
                    + json.get("image") + "','"
                    + json.get("startdate") + "','"
                    + json.get("enddate") + "','"
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
            String update = "UPDATE event SET "
                    + "title = '" + json.get("title") + "',"
                    + "detail = '" + json.get("detail") + "',"
                    + "image = '" + json.get("image") + "',"
                    + "startdate = '" + json.get("startdate") + "',"
                    + "enddate = '" + json.get("enddate") + "',"
                    + "status = '" + json.get("status") + "' "
                    + "WHERE id_eve = '" + json.get("id_eve") + "'";
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
            String select = "select image from event "
                    + "WHERE id_eve = '" + json.get("id_eve") + "'";
            con.query(select);
            if (con.next()) {
                String[] filename = con.getString("image").split("[,]");
                for (int i = 0; i < filename.length; i++) {
                    File file = new File(json.get("path") + filename[i]);
                    file.delete();
                }
            }
            String delete = "delete from event "
                    + "WHERE id_eve = '" + json.get("id_eve") + "'";
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
