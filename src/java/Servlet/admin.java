/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Class.ContentData;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;

/**
 *
 * @author NewSuppamit
 */
public class admin extends HttpServlet {

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
            out.println("<title>Servlet admin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet admin at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        String page = "frmOfindex.jsp";
        System.out.println(session.getAttribute("status"));
        if(session.getAttribute("status")!=null){
            if(session.getAttribute("status").equals("2")){
                page = "admin/main.jsp";
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(page);
        view.forward(request, response);
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
        String page = "index";
        try {
            String content = request.getParameter("content");
            switch (ContentData.Content.valueOf(content)) {
                case slideshow:
                    page = "admin/slideshow.jsp";
                    break;
                case news:
                    page = "admin/news.jsp";
                    break;
                case event:
                    page = "admin/event.jsp";
                    break;
                case knowledge:
                    page = "admin/knowledge.jsp";
                    break;
                case link:
                    page = "admin/link.jsp";
                    break;
                case calendar:
                    page = "admin/calendar.jsp";
                    break;
                case user:
                    page = "admin/user.jsp";
                    break;
                case personnel:
                    page = "admin/personnel.jsp";
                    break;
                case student:
                    page = "admin/student.jsp";
                    break;
                case course:
                    page = "admin/course.jsp";
                    break;
                case academic:
                    page = "admin/academic.jsp";
                    break;
                case research:
                    page = "admin/research.jsp";
                    break;
                case project:
                    page = "admin/project.jsp";
                    break;
                case qassurance:
                    page = "admin/qassurance.jsp";
                    break;
                case groupdownload:
                    page = "admin/groupdownload.jsp";
                    break;
                case download:
                    page = "admin/download.jsp";
                    break;
            }
        } catch (Exception e) {
        }
        RequestDispatcher view = request.getRequestDispatcher(page);
        view.forward(request, response);
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
