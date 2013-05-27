/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Class.ContentData;
import Class.ContentData.Content;
import Class.ContentData.Option;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;
import org.json.simple.JSONObject;
import sun.misc.BASE64Decoder;

/**
 *
 * @author NewSuppamit
 */
public class content extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet content</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet content at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String content = request.getParameter("content");
        String option = request.getParameter("option");
        JSONObject data = new JSONObject();
        switch (Content.valueOf(content)) {
            case slideshow:
                if (Option.some.toString().equals(option)) {
                    data.put("id_sli", request.getParameter("id_sli"));
                }
                break;
            case news:
                if (Option.show.toString().equals(option)) {
                    data.put("rp", request.getParameter("rp"));
                    if (request.getParameter("page") != null) {
                        data.put("page", request.getParameter("page"));
                    } else {
                        data.put("page", "1");
                    }
                } else if (Option.some.toString().equals(option)) {
                    data.put("id_new", request.getParameter("id_new"));
                }
                break;
            case event:
                if (Option.show.toString().equals(option)) {
                    data.put("rp", request.getParameter("rp"));
                    if (request.getParameter("page") != null) {
                        data.put("page", request.getParameter("page"));
                    } else {
                        data.put("page", "1");
                    }
                } else if (Option.some.toString().equals(option)) {
                    data.put("id_eve", request.getParameter("id_eve"));
                }
                break;
            case knowledge:
                if (Option.show.toString().equals(option)) {
                    data.put("rp", request.getParameter("rp"));
                    if (request.getParameter("page") != null) {
                        data.put("page", request.getParameter("page"));
                    } else {
                        data.put("page", "1");
                    }
                } else if (Option.some.toString().equals(option)) {
                    data.put("id_kno", request.getParameter("id_kno"));
                }
                break;
            case link:
                if (Option.some.toString().equals(option)) {
                    data.put("id_lin", request.getParameter("id_lin"));
                }
                break;
            case calendar:
                if (Option.some.toString().equals(option)) {
                    data.put("date", request.getParameter("date"));
                }
                break;
            case user:
                if (Option.login.toString().equals(option)) {
                    data.put("email", request.getParameter("email"));
                    data.put("password", request.getParameter("password"));
                } else if (Option.check.toString().equals(option)) {
                    data.put("email", request.getParameter("email"));
                }
                break;
            case personnel:
                if (Option.some.toString().equals(option)) {
                    data.put("id_per", request.getParameter("id_per"));
                }
                break;
            case student:
                if (Option.show.toString().equals(option)) {
                    data.put("year", request.getParameter("year"));
                } else if (Option.some.toString().equals(option)) {
                    data.put("id_stu", request.getParameter("id_stu"));
                }
                break;
            case course:
                if (Option.some.toString().equals(option)) {
                    data.put("id_cou", request.getParameter("id_cou"));
                }
                break;
            case academic:
                if (Option.some.toString().equals(option)) {
                    data.put("id_aca", request.getParameter("id_aca"));
                }
                break;
            case research:
                if (Option.some.toString().equals(option)) {
                    data.put("id_res", request.getParameter("id_res"));
                }
                break;
            case project:
                if (Option.some.toString().equals(option)) {
                    data.put("id_pro", request.getParameter("id_pro"));
                }
                break;
            case qassurance:
                if (Option.show.toString().equals(option)) {
                    data.put("category", request.getParameter("category"));
                } else if (Option.some.toString().equals(option)) {
                    data.put("id_qas", request.getParameter("id_qas"));
                }
                break;
            case groupdownload:
                if (Option.some.toString().equals(option)) {
                    data.put("id_gro", request.getParameter("id_gro"));
                }
                break;
            case download:
                if (Option.show.toString().equals(option)) {
                    data.put("id_gro", request.getParameter("id_gro"));
                } else if (Option.some.toString().equals(option)) {
                    data.put("id_dow", request.getParameter("id_dow"));
                }
                break;
            case session:
                HttpSession session = request.getSession(true);
                if (option.equals("get")&&session.getAttribute("email")!=null) {
                    JSONObject result = new JSONObject();
                    result.put("email", session.getAttribute("email"));
                    result.put("pname", session.getAttribute("pname"));
                    result.put("fname", session.getAttribute("fname"));
                    result.put("lname", session.getAttribute("lname"));
                    result.put("status", session.getAttribute("status"));
                    out.print(result);
                } else {
                    session.removeAttribute("email");
                    session.removeAttribute("pname");
                    session.removeAttribute("fname");
                    session.removeAttribute("lname");
                    session.removeAttribute("status");
                }
                break;
        }
        if (!content.equals("session")) {
            out.print(ContentData.getData(content, option, data.toString()));
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        String content = request.getParameter("content");
        String option = request.getParameter("option");
        switch (Content.valueOf(content)) {
            case slideshow:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_sli", request.getParameter("id_sli"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    String image = request.getParameter("image");
                    String filename = request.getParameter("filename");
                    if (image != null) {
                        if (!"".equals(image)) {
                            if (filename != null) {
                                File file = new File(getServletContext().getRealPath("/") + filename);
                                file.delete();
                            }
                            String[] datas = image.split("[,]");
                            String[] filetype = datas[0].split("[/]");
                            filetype = filetype[1].split("[;]");
                            BASE64Decoder decoder = new BASE64Decoder();
                            filename = "images/slideshow/" + UUID.randomUUID() + "." + filetype[0];
                            String base64 = datas[1];
                            byte[] normal = decoder.decodeBuffer(base64);
                            FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                            fo.write(normal);
                            fo.close();
                        }
                    }
                    data.put("title", request.getParameter("title"));
                    data.put("image", filename);
                    data.put("link", request.getParameter("link"));
                    data.put("sequence", request.getParameter("sequence"));
                    data.put("startdate", request.getParameter("startdate"));
                    data.put("enddate", request.getParameter("enddate"));
                    data.put("status", request.getParameter("status"));
                }
                break;
            case news:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    System.out.println(request.getParameter("id_new"));
                    data.put("id_new", request.getParameter("id_new"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                if (Option.add.toString().equals(option)) {
                    String file = request.getParameter("file");
                    String filename = request.getParameter("filename");
                    if (file != null) {
                        if (!"".equals(file)) {
                            String path = "file/news/" + UUID.randomUUID() + "/";
                            String[] filename_temp = filename.split("[\\\\]");
                            filename = filename_temp[filename_temp.length - 1];
                            String[] datas = file.split("[,]");
                            BASE64Decoder decoder = new BASE64Decoder();
                            if (new File(getServletContext().getRealPath("/") + path).mkdirs()) {
                                System.out.println("true");
                            } else {
                                System.out.println("false");
                            }
                            filename = path + filename;
                            String base64 = datas[1];
                            byte[] normal = decoder.decodeBuffer(base64);
                            FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                            fo.write(normal);
                            fo.close();
                        }
                    }
                    data.put("file", filename);
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    data.put("title", request.getParameter("title"));
                    data.put("detail", request.getParameter("detail"));
                    data.put("startdate", request.getParameter("startdate"));
                    data.put("status", request.getParameter("status"));
                }
                break;
            case event:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_eve", request.getParameter("id_eve"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                if (Option.add.toString().equals(option)) {
                    String[] image = request.getParameterValues("image[]");
                    String filenames = "";
                    if (image != null) {
                        for (int i = (image.length - 1); i >= 0; i--) {
                            if (!"".equals(image[i])) {
                                String[] datas = image[i].split("[,]");
                                String filename = "";
                                String[] filetype = datas[0].split("[/]");
                                filetype = filetype[1].split("[;]");
                                BASE64Decoder decoder = new BASE64Decoder();
                                filename = "images/event/" + UUID.randomUUID() + "." + filetype[0];
                                filenames += filename;
                                if (i > 0) {
                                    filenames += ",";
                                }
                                String base64 = datas[1];
                                byte[] normal = decoder.decodeBuffer(base64);
                                FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                                fo.write(normal);
                                fo.close();
                            }
                        }
                    }
                    data.put("image", filenames);
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    data.put("title", request.getParameter("title"));
                    data.put("detail", request.getParameter("detail"));

                    data.put("startdate", request.getParameter("startdate"));
                    data.put("enddate", request.getParameter("enddate"));
                    data.put("status", request.getParameter("status"));
                }

                break;
            case knowledge:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_kno", request.getParameter("id_kno"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                if (Option.add.toString().equals(option)) {
                    String[] image = request.getParameterValues("image[]");
                    String filenames = "";
                    if (image != null) {
                        for (int i = (image.length - 1); i >= 0; i--) {
                            if (!"".equals(image[i])) {
                                String[] datas = image[i].split("[,]");
                                String filename = "";
                                String[] filetype = datas[0].split("[/]");
                                filetype = filetype[1].split("[;]");
                                BASE64Decoder decoder = new BASE64Decoder();
                                filename = "images/knowledge/" + UUID.randomUUID() + "." + filetype[0];
                                filenames += filename;
                                if (i > 0) {
                                    filenames += ",";
                                }
                                String base64 = datas[1];
                                byte[] normal = decoder.decodeBuffer(base64);
                                FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                                fo.write(normal);
                                fo.close();
                            }
                        }
                    }
                    data.put("image", filenames);
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    data.put("title", request.getParameter("title"));
                    data.put("detail", request.getParameter("detail"));
                    data.put("startdate", request.getParameter("startdate"));
                    data.put("status", request.getParameter("status"));
                }
                break;
            case link:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_lin", request.getParameter("id_lin"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    data.put("title", request.getParameter("title"));
                    data.put("link", request.getParameter("link"));
                    data.put("sequence", request.getParameter("sequence"));
                    data.put("status", request.getParameter("status"));
                }
                break;
            case calendar:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_cal", request.getParameter("id_cal"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    data.put("title", request.getParameter("title"));
                    data.put("detail", request.getParameter("detail"));
                    data.put("date", request.getParameter("date"));
                    data.put("status", request.getParameter("status"));
                }
                break;
            case user:
                data.put("email", request.getParameter("email"));
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    data.put("password", request.getParameter("password"));
                    data.put("pname", request.getParameter("pname"));
                    data.put("fname", request.getParameter("fname"));
                    data.put("lname", request.getParameter("lname"));
                    data.put("status", request.getParameter("status"));
                }
                break;
            case personnel:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_per", request.getParameter("id_per"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    String image = request.getParameter("image");
                    String filename = request.getParameter("filename");
                    if (image != null) {
                        if (!"".equals(image)) {
                            if (filename != null) {
                                File file = new File(getServletContext().getRealPath("/") + filename);
                                file.delete();
                            }
                            String[] datas = image.split("[,]");
                            String[] filetype = datas[0].split("[/]");
                            filetype = filetype[1].split("[;]");
                            BASE64Decoder decoder = new BASE64Decoder();
                            filename = "images/personnel/" + UUID.randomUUID() + "." + filetype[0];
                            String base64 = datas[1];
                            byte[] normal = decoder.decodeBuffer(base64);
                            FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                            fo.write(normal);
                            fo.close();
                        }
                    }
                    data.put("name", request.getParameter("name"));
                    data.put("position", request.getParameter("position"));
                    data.put("detail", request.getParameter("detail"));
                    data.put("sequence", request.getParameter("sequence"));
                    data.put("image", filename);
                    data.put("status", request.getParameter("status"));
                    
                }
                break;
            case student:
                data.put("id_stu", request.getParameter("id_stu"));
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    data.put("name", request.getParameter("name"));
                    data.put("detail", request.getParameter("detail"));
                    data.put("status", request.getParameter("status"));
                }
                break;
            case course:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_cou", request.getParameter("id_cou"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    String file = request.getParameter("file");
                    String filename = "";
                    if (file != null) {
                        if (!"".equals(file)) {
                            String[] datas = file.split("[,]");
                            String[] filetype = datas[0].split("[/]");
                            filetype = filetype[1].split("[;]");
                            BASE64Decoder decoder = new BASE64Decoder();
                            filename = "file/course/" + UUID.randomUUID() + "." + filetype[0];
                            String base64 = datas[1];
                            byte[] normal = decoder.decodeBuffer(base64);
                            FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                            fo.write(normal);
                            fo.close();
                        }
                    }
                    data.put("title", request.getParameter("title"));
                    data.put("file", filename);
                    data.put("status", request.getParameter("status"));
                }
                break;
            case academic:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_aca", request.getParameter("id_aca"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    String file = request.getParameter("file");
                    String filename = "";
                    if (file != null) {
                        if (!"".equals(file)) {
                            String[] datas = file.split("[,]");
                            String[] filetype = datas[0].split("[/]");
                            filetype = filetype[1].split("[;]");
                            BASE64Decoder decoder = new BASE64Decoder();
                            filename = "file/academic/" + UUID.randomUUID() + "." + filetype[0];
                            String base64 = datas[1];
                            byte[] normal = decoder.decodeBuffer(base64);
                            FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                            fo.write(normal);
                            fo.close();
                        }
                    }
                    data.put("owner", request.getParameter("owner"));
                    data.put("title", request.getParameter("title"));
                    data.put("file", filename);
                    data.put("status", request.getParameter("status"));
                }
                break;
            case research:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_res", request.getParameter("id_res"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    String file = request.getParameter("file");
                    String filename = "";
                    if (file != null) {
                        if (!"".equals(file)) {
                            String[] datas = file.split("[,]");
                            String[] filetype = datas[0].split("[/]");
                            filetype = filetype[1].split("[;]");
                            BASE64Decoder decoder = new BASE64Decoder();
                            filename = "file/research/" + UUID.randomUUID() + "." + filetype[0];
                            String base64 = datas[1];
                            byte[] normal = decoder.decodeBuffer(base64);
                            FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                            fo.write(normal);
                            fo.close();
                        }
                    }
                    data.put("owner", request.getParameter("owner"));
                    data.put("title", request.getParameter("title"));
                    data.put("file", filename);
                    data.put("status", request.getParameter("status"));
                }
                break;
            case project:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_pro", request.getParameter("id_pro"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    data.put("owner", request.getParameter("owner"));
                    data.put("title", request.getParameter("title"));
                    data.put("link", request.getParameter("link"));
                    data.put("status", request.getParameter("status"));
                }
                break;
            case qassurance:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_qas", request.getParameter("id_qas"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    String file = request.getParameter("file");
                    String filename = "";
                    if (file != null) {
                        if (!"".equals(file)) {
                            String[] datas = file.split("[,]");
                            String[] filetype = datas[0].split("[/]");
                            filetype = filetype[1].split("[;]");
                            BASE64Decoder decoder = new BASE64Decoder();
                            filename = "file/qassurance/" + UUID.randomUUID() + "." + filetype[0];
                            String base64 = datas[1];
                            byte[] normal = decoder.decodeBuffer(base64);
                            FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                            fo.write(normal);
                            fo.close();
                        }
                    }
                    data.put("category", request.getParameter("category"));
                    data.put("title", request.getParameter("title"));
                    data.put("file", filename);
                    data.put("status", request.getParameter("status"));
                }
                break;
            case groupdownload:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_gro", request.getParameter("id_gro"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    data.put("title", request.getParameter("title"));
                    data.put("status", request.getParameter("status"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                break;
            case download:
                if (Option.edit.toString().equals(option) || Option.remove.toString().equals(option)) {
                    data.put("id_dow", request.getParameter("id_dow"));
                }
                if (Option.remove.toString().equals(option)) {
                    data.put("path", getServletContext().getRealPath("/"));
                }
                if (Option.edit.toString().equals(option) || Option.add.toString().equals(option)) {
                    String file = request.getParameter("file");
                    String filename = "";
                    if (file != null) {
                        if (!"".equals(file)) {
                            String[] datas = file.split("[,]");
                            String[] filetype = datas[0].split("[/]");
                            filetype = filetype[1].split("[;]");
                            BASE64Decoder decoder = new BASE64Decoder();
                            filename = "file/download/" + UUID.randomUUID() + "." + filetype[0];
                            String base64 = datas[1];
                            byte[] normal = decoder.decodeBuffer(base64);
                            FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                            fo.write(normal);
                            fo.close();
                        }
                    }
                    data.put("id_gro", request.getParameter("id_gro"));
                    data.put("title", request.getParameter("title"));
                    data.put("file", filename);
                    data.put("status", request.getParameter("status"));
                }
                break;
            case session:
                HttpSession session = request.getSession(true);
                if (option.equals("set")) {
                    session.setAttribute("email", request.getParameter("email"));
                    session.setAttribute("pname", request.getParameter("pname"));
                    session.setAttribute("fname", request.getParameter("fname"));
                    session.setAttribute("lname", request.getParameter("lname"));
                    session.setAttribute("status", request.getParameter("status"));
                } else {
                    session.removeAttribute("email");
                    session.removeAttribute("pname");
                    session.removeAttribute("fname");
                    session.removeAttribute("lname");
                    session.removeAttribute("status");
                }
                break;
        }
        if (!content.equals("session")) {
            if (ContentData.setData(content, option, data.toString())) {
                result.put("result", "success");
            } else {
                result.put("result", "fail");
            }
        } else {
            result.put("result", "success");
        }
        out.print(result.toString());
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
