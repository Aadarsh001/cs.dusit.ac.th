/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Class.ContentData.Option;
import Servlet.index;
import java.io.File;
import java.io.FileOutputStream;
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
import sun.misc.BASE64Decoder;

/**
 *
 * @author NewSuppamit
 */
public class News {

    public static String getData(String option, String detail) {
        switch (Option.valueOf(option)) {
            case pin:
                return showPinDB();
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
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", new Locale("th", "TH"));
            String Date = dateFormat.format(new Date());
            Connect con = new Connect();
            con.connect();
            int page = Integer.parseInt(data.get("page").toString());
            int rp = Integer.parseInt(data.get("rp").toString());
            String select = "SELECT * FROM news "
                    + "WHERE status = '1' and startdate <= '" + Date + "' "
                    + "ORDER BY startdate DESC,id_new DESC LIMIT " + rp * (page - 1) + "," + rp * page + "";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_new", con.getString("id_new"));
                jchil.put("title", con.getString("title"));
                jchil.put("detail", con.getString("detail"));
                jchil.put("file", con.getString("file"));
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
            String select = "SELECT * FROM news "
                    + "ORDER BY id_new DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_new", con.getString("id_new"));
                jchil.put("title", con.getString("title"));
                jchil.put("detail", con.getString("detail"));
                jchil.put("file", con.getString("file"));
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
            String select = "SELECT * FROM news "
                    + "WHERE id_new = '" + data.get("id_new") + "'";
            con.query(select);
            while (con.next()) {
                json.put("id_new", con.getString("id_new"));
                json.put("title", con.getString("title"));
                json.put("detail", con.getString("detail"));
                json.put("file", con.getString("file"));
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

    private static String showPinDB() {
        try {
            JSONObject json = new JSONObject();
            JSONArray jarray = new JSONArray();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", new Locale("th", "TH"));
            String Date = dateFormat.format(new Date());
            Connect con = new Connect();
            con.connect();
            String select = "SELECT * FROM news "
                    + "WHERE status = '2' and startdate <= '" + Date + "' "
                    + "ORDER BY id_new DESC";
            con.query(select);
            while (con.next()) {
                JSONObject jchil = new JSONObject();
                jchil.put("id_new", con.getString("id_new"));
                jchil.put("title", con.getString("title"));
                jchil.put("detail", con.getString("detail"));
                jchil.put("file", con.getString("file"));
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

    private static boolean addDB(String data) {
        try {
            Connect con = new Connect();
            JSONObject json = (JSONObject) JSONValue.parse(data);
            con.connect();
            String select = "select max(id_new) as id_new from news";
            con.query(select);
            con.next();
            String id_new = con.getString("id_new");
            if (id_new == null) {
                id_new = "0";
            }
            DecimalFormat decimal_format = new DecimalFormat("000000");
            id_new = decimal_format.format(Integer.parseInt(id_new) + 1);
            String file = (String) json.get("file");
            String filename = (String) json.get("filename");
            if (file != null) {
                String path = "file/news/";
                String[] filename_temp = filename.split("[.]");
                filename = filename_temp[filename_temp.length - 1];
                String[] datas = file.split("[,]");
                BASE64Decoder decoder = new BASE64Decoder();
                filename = path + "news_" + id_new + "." + filename;
                String base64 = datas[1];
                byte[] normal = decoder.decodeBuffer(base64);
                FileOutputStream fo = new FileOutputStream(json.get("path") + filename);
                fo.write(normal);
                fo.close();
            }
            String insert = "insert into news values('"
                    + id_new + "','"
                    + json.get("title") + "','"
                    + json.get("detail") + "','"
                    + filename + "','"
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
            String file = (String) json.get("file");
            String filename = (String) json.get("filename");
            if (file != null) {
                String select = "select file from news "
                        + "WHERE id_new = '" + json.get("id_new") + "'";
                con.query(select);
                if (con.next()) {
                    if (!"".equals(filename)) {
                        new File(json.get("path") + con.getString("file")).delete();
                    }
                }
                String path = "file/news/";
                String[] filename_temp = filename.split("[.]");
                filename = filename_temp[filename_temp.length - 1];
                String[] datas = file.split("[,]");
                BASE64Decoder decoder = new BASE64Decoder();
                filename = path + "news_" + json.get("id_new") + "." + filename;
                String base64 = datas[1];
                byte[] normal = decoder.decodeBuffer(base64);
                FileOutputStream fo = new FileOutputStream(json.get("path") + filename);
                filename = "file = '" + filename + "',";
                fo.write(normal);
                fo.close();
            }
            String update = "UPDATE news SET "
                    + "title = '" + json.get("title") + "',"
                    + "detail = '" + json.get("detail") + "',"
                    + "startdate = '" + json.get("startdate") + "',"
                    + filename
                    + "status = '" + json.get("status") + "' "
                    + "WHERE id_new = '" + json.get("id_new") + "'";
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
            String select = "select file from news "
                    + "WHERE id_new = '" + json.get("id_new") + "'";
            con.query(select);
            if (con.next()) {
                String filename = con.getString("file");
                if (!"".equals(filename)) {
                    File file = new File(json.get("path") + filename);
                    file.delete();
                }
            }
            String delete = "delete from news "
                    + "WHERE id_new = '" + json.get("id_new") + "'";
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