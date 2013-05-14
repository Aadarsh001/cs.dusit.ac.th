/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

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
public class Slideshow {

    public static String getData(String option, String detail) {
        if (option.equals("show")) {
            return showDB();
        } else if (option.equals("all")) {
            return showAllDB();
        } else if (option.equals("some")) {
            return showSomeDB(detail);
        }
        return null;
    }

    public static boolean setData(String option, String data) {
        if (option.equals("add")) {
            return addDB(data);
        } else if (option.equals("edit")) {
            return editDB(data);
        } else if (option.equals("remove")) {
            return removeDB(data);
        }
        return false;
    }

    private static String showDB() {
        try {
            JSONObject json = new JSONObject();
            JSONArray jarray = new JSONArray();
            Connect con = new Connect();
            con.connect();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", new Locale("th", "TH"));
            String Date = dateFormat.format(new Date());
            String select = "SELECT * FROM slideshow "
                    + "WHERE startdate <= '" + Date + "' and "
                    + "enddate >= '" + Date + "' and "
                    + "status = '1' ORDER BY sequence ASC LIMIT 0,9";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_sli", con.getString("id_sli"));
                jchil.put("title", con.getString("title"));
                jchil.put("image", con.getString("image"));
                jchil.put("link", con.getString("link"));
                jchil.put("sequence", con.getString("sequence"));
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
            String select = "SELECT * FROM slideshow "
                    + "ORDER BY id_sli DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_sli", con.getString("id_sli"));
                jchil.put("title", con.getString("title"));
                jchil.put("image", con.getString("image"));
                jchil.put("link", con.getString("link"));
                jchil.put("sequence", con.getString("sequence"));
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
            String select = "SELECT * FROM slideshow "
                    + "WHERE id_sli = '" + data.get("id_sli") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_sli", con.getString("id_sli"));
                json.put("title", con.getString("title"));
                json.put("image", con.getString("image"));
                json.put("link", con.getString("link"));
                json.put("sequence", con.getString("sequence"));
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
            String select = "select max(id_sli) as id_sli from slideshow";
            con.query(select);
            String id_sli;
            if(con.next()){
                id_sli = con.getString("id_sli");
            }else{
                id_sli = "0";
            }
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_sli = decimal_format.format(Integer.parseInt(id_sli) + 1);
            String insert = "insert into slideshow values('"
                    + id_sli + "','"
                    + json.get("title") + "','"
                    + json.get("image") + "','"
                    + json.get("link") + "','"
                    + json.get("sequence") + "','"
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
            String update = "UPDATE slideshow SET "
                    + "title = '" + json.get("title") + "',"
                    + "image = '" + json.get("image") + "',"
                    + "link = '" + json.get("link") + "',"
                    + "sequence = '" + json.get("sequence") + "',"
                    + "startdate = '" + json.get("startdate") + "',"
                    + "enddate = '" + json.get("enddate") + "',"
                    + "status = '" + json.get("status") + "' "
                    + "WHERE id_sli = '" + json.get("id_sli") + "'";
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
            String select = "select image from slideshow "
                    + "WHERE id_sli = '" + json.get("id_sli") + "'";
            con.query(select);
            if (con.next()) {
                String filename = con.getString("image");
                if (!"".equals(filename)) {
                    File file = new File(json.get("path") + filename);
                    file.delete();
                }
            }
            String delete = "delete from slideshow "
                    + "WHERE id_sli = '" + json.get("id_sli") + "'";
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