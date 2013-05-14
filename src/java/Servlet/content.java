/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Class.ContentData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        System.out.println(content.hashCode());
        if ("slideshow".equals(content)) {
            if ("some".equals(option)) {
                data.put("id_sli", request.getParameter("id_sli"));
            }
        } else if ("news".equals(content)) {
            if ("some".equals(option)) {
                data.put("id_new", request.getParameter("id_new"));
            }
        } else if ("event".equals(content)) {
            if ("some".equals(option)) {
                data.put("id_eve", request.getParameter("id_eve"));
            }
        } else if ("knowledge".equals(content)) {
            if ("some".equals(option)) {
                data.put("id_kno", request.getParameter("id_kno"));
            }
        } else if ("link".equals(content)) {
            if ("some".equals(option)) {
                data.put("id_lin", request.getParameter("id_lin"));
            }
        } else if ("calendar".equals(content)) {
            if ("some".equals(option)) {
                data.put("id_cal", request.getParameter("id_cal"));
            } else if ("show".equals(option)) {
                data.put("month", request.getParameter("month"));
            }
        } else if ("user".equals(content)) {
            if ("check".equals(option)) {
                data.put("email", request.getParameter("email"));
            } else if ("login".equals(option)) {
                data.put("email", request.getParameter("email"));
                data.put("password", request.getParameter("password"));
            }
        }
        if (content != null) {
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
        if ("slideshow".equals(content)) {
            if ("edit".equals(option) || "remove".equals(option)) {
                data.put("id_sli", request.getParameter("id_sli"));
            }
            if ("remove".equals(option)) {
                data.put("path", getServletContext().getRealPath("/"));
            }
            if ("edit".equals(option) || "add".equals(option)) {
                String image = request.getParameter("image");
                String filename = "";
                if (image != null) {
                    if (!"".equals(image)) {
                        String[] datas = image.split("[,]");
                        filename = "" + new File(getServletContext().getRealPath("/") + "/images/slideshow").list().length;
                        String[] filetype = datas[0].split("[/]");
                        filetype = filetype[1].split("[;]");
                        BASE64Decoder decoder = new BASE64Decoder();
                        DecimalFormat decimal_format = new DecimalFormat("000000");
                        filename = "images/slideshow/" + decimal_format.format(Integer.parseInt(filename) + 1) + "." + filetype[0];
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
        } else if ("news".equals(content)) {
            if ("edit".equals(option) || "remove".equals(option)) {
                data.put("id_new", request.getParameter("id_new"));
            }
            if ("remove".equals(option)) {
                data.put("path", getServletContext().getRealPath("/"));
            }
            if ("edit".equals(option) || "add".equals(option)) {
                String file = request.getParameter("file");
                String filename = "";
                if (file != null) {
                    if (!"".equals(file)) {
                        String[] datas = file.split("[,]");
                        filename = "" + new File(getServletContext().getRealPath("/") + "/file/news").list().length;
                        String[] filetype = datas[0].split("[/]");
                        filetype = filetype[1].split("[;]");
                        BASE64Decoder decoder = new BASE64Decoder();
                        DecimalFormat decimal_format = new DecimalFormat("000000");
                        filename = "file/news/" + decimal_format.format(Integer.parseInt(filename) + 1) + "." + filetype[0];
                        String base64 = datas[1];
                        byte[] normal = decoder.decodeBuffer(base64);
                        FileOutputStream fo = new FileOutputStream(getServletContext().getRealPath("/") + filename);
                        fo.write(normal);
                        fo.close();
                    }
                }
                data.put("title", request.getParameter("title"));
                data.put("detail", request.getParameter("detail"));
                data.put("file", filename);
                data.put("startdate", request.getParameter("startdate"));
                data.put("status", request.getParameter("status"));
            }
        } else if ("event".equals(content)) {
            if ("edit".equals(option) || "remove".equals(option)) {
                data.put("id_eve", request.getParameter("id_eve"));
            }
            if ("remove".equals(option)) {
                data.put("path", getServletContext().getRealPath("/"));
            }
            if ("edit".equals(option) || "add".equals(option)) {
                String[] image = request.getParameterValues("image[]");
                String filenames = "";
                if (image != null) {
                    for (int i = 0; i < image.length; i++) {
                        if (!"".equals(image[i])) {
                            String[] datas = image[i].split("[,]");
                            String filename = "" + new File(getServletContext().getRealPath("/") + "/images/event").list().length;
                            String[] filetype = datas[0].split("[/]");
                            filetype = filetype[1].split("[;]");
                            BASE64Decoder decoder = new BASE64Decoder();
                            DecimalFormat decimal_format = new DecimalFormat("000000");
                            filename = "images/event/" + decimal_format.format(Integer.parseInt(filename) + 1) + "." + filetype[0];
                            filenames += filename;
                            if ((i + 1) < image.length) {
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
                data.put("title", request.getParameter("title"));
                data.put("detail", request.getParameter("detail"));
                data.put("image", filenames);
                data.put("startdate", request.getParameter("startdate"));
                data.put("enddate", request.getParameter("enddate"));
                data.put("status", request.getParameter("status"));
            }
        } else if ("knowledge".equals(content)) {
            if ("edit".equals(option) || "remove".equals(option)) {
                data.put("id_kno", request.getParameter("id_kno"));
            }
            if ("remove".equals(option)) {
                data.put("path", getServletContext().getRealPath("/"));
            }
            if ("edit".equals(option) || "add".equals(option)) {
                String[] image = request.getParameterValues("image[]");
                String filenames = "";
                if (image != null) {
                    for (int i = 0; i < image.length; i++) {
                        if (!"".equals(image[i])) {
                            String[] datas = image[i].split("[,]");
                            String filename = "" + new File(getServletContext().getRealPath("/") + "/images/knowledge").list().length;
                            String[] filetype = datas[0].split("[/]");
                            filetype = filetype[1].split("[;]");
                            BASE64Decoder decoder = new BASE64Decoder();
                            DecimalFormat decimal_format = new DecimalFormat("000000");
                            filename = "images/knowledge/" + decimal_format.format(Integer.parseInt(filename) + 1) + "." + filetype[0];
                            filenames += filename;
                            if ((i + 1) < image.length) {
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
                data.put("title", request.getParameter("title"));
                data.put("detail", request.getParameter("detail"));
                data.put("image", filenames);
                data.put("startdate", request.getParameter("startdate"));
                data.put("status", request.getParameter("status"));
            }
        } else if ("link".equals(content)) {
            if ("edit".equals(option) || "remove".equals(option)) {
                data.put("id_lin", request.getParameter("id_lin"));
            }
            if ("edit".equals(option) || "add".equals(option)) {
                data.put("title", request.getParameter("title"));
                data.put("link", request.getParameter("link"));
                data.put("sequence", request.getParameter("sequence"));
                data.put("status", request.getParameter("status"));
            }
        } else if ("calendar".equals(content)) {
            if ("edit".equals(option) || "remove".equals(option)) {
                data.put("id_cal", request.getParameter("id_cal"));
            }
            if ("edit".equals(option) || "add".equals(option)) {
                data.put("title", request.getParameter("title"));
                data.put("detail", request.getParameter("detail"));
                data.put("date", request.getParameter("date"));
                data.put("status", request.getParameter("status"));
            }
        } else if ("user".equals(content)) {
                data.put("email", request.getParameter("email"));
            if ("edit".equals(option) || "add".equals(option)) {
                data.put("password", request.getParameter("password"));
                data.put("pname", request.getParameter("pname"));
                data.put("fname", request.getParameter("fname"));
                data.put("lname", request.getParameter("lname"));
                data.put("status", request.getParameter("status"));
            }
        }
        if (ContentData.setData(content, option, data.toString())) {
            result.put("result", "success");
        } else {
            result.put("result", "fail");
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
