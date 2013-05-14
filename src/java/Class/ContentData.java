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

    public enum Content {

        slideshow, news, event, knowledge, link, calendar, user;
    }

    public enum Option {

        show, all, some, pin, login, check, add, edit, remove;
    }

    public static String getData(String content, String option, String detail) {
        switch (Content.valueOf(content)) {
            case slideshow:
                return Slideshow.getData(option, detail);
            case news:
                return News.getData(option, detail);
            case event:
                return Event.getData(option, detail);
            case knowledge:
                return Knowledge.getData(option, detail);
            case link:
                return Link.getData(option, detail);
            case calendar:
                return Calendar.getData(option, detail);
            case user:
                return User.getData(option, detail);
            default:
                return null;
        }
    }

    public static boolean setData(String content, String option, String data) {
        switch (Content.valueOf(content)) {
            case slideshow:
                return Slideshow.setData(option, data);
            case news:
                return News.setData(option, data);
            case event:
                return Event.setData(option, data);
            case knowledge:
                return Knowledge.setData(option, data);
            case link:
                return Link.setData(option, data);
            case calendar:
                return Calendar.setData(option, data);
            case user:
                return User.setData(option, data);
            default:
                return false;
        }
    }
}
