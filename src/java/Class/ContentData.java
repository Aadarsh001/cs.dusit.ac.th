/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author NewSuppamit
 */
public class ContentData {

    public static String getData(String content, String option, String detail) {
        if (content.equals("slideshow")) {
            return Slideshow.getData(option, detail);
        } else if (content.equals("news")) {
            return News.getData(option, detail);
        } else if (content.equals("event")) {
            return Event.getData(option, detail);
        } else if (content.equals("knowledge")) {
            return Knowledge.getData(option, detail);
        } else if (content.equals("link")) {
            return Link.getData(option, detail);
        } else if (content.equals("calendar")) {
            return Calendar.getData(option, detail);
        } else if (content.equals("user")) {
            return User.getData(option, detail);
        }
        return null;
    }

    public static boolean setData(String content, String option, String data) {
        if (content.equals("slideshow")) {
            return Slideshow.setData(option, data);
        } else if (content.equals("news")) {
            return News.setData(option, data);
        } else if (content.equals("event")) {
            return Event.setData(option, data);
        } else if (content.equals("knowledge")) {
            return Knowledge.setData(option, data);
        } else if (content.equals("link")) {
            return Link.setData(option, data);
        } else if (content.equals("calendar")) {
            return Calendar.setData(option, data);
        } else if (content.equals("user")) {
            return User.setData(option, data);
        }
        return false;
    }
}
