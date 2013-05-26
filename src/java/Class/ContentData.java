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

        slideshow, news, event, knowledge, link, calendar, user, personnel, student, course, academic, research, project, qassurance, groupdownload, download, session;
    }

    public enum Option {

        show, all, some, pin, login, check, add, edit, remove, set, get;
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
            case personnel:
                return Personnel.getData(option, detail);
            case student:
                return Student.getData(option, detail);
            case course:
                return Course.getData(option, detail);
            case academic:
                return Academic.getData(option, detail);
            case research:
                return Research.getData(option, detail);
            case project:
                return Project.getData(option, detail);
            case qassurance:
                return Qassurance.getData(option, detail);
            case groupdownload:
                return Groupdownload.getData(option, detail);
            case download:
                return Download.getData(option, detail);
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
            case personnel:
                return Personnel.setData(option, data);
            case student:
                return Student.setData(option, data);
            case course:
                return Course.setData(option, data);
            case academic:
                return Academic.setData(option, data);
            case research:
                return Research.setData(option, data);
            case project:
                return Project.setData(option, data);
            case qassurance:
                return Qassurance.setData(option, data);
            case groupdownload:
                return Groupdownload.setData(option, data);
            case download:
                return Download.setData(option, data);
            default:
                return false;
        }
    }
}
