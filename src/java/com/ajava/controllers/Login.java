package com.ajava.controllers;

import com.ajava.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//servlet to validate login credentials
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DBHandler dbHandler;

    @Override
    public void init() throws ServletException {
        super.init();
        dbHandler = new DBHandler();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") != null) {
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = dbHandler.authenticate(email, password);

            if (user == null) {
                request.setAttribute("error", "Wrong email or password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                // go to welcome page
                request.getSession().setAttribute("username", user.getName());
                request.getSession().setAttribute("uid", user.getUid());
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        }
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "This servlet is to responsible all account related tasks.";
    }

}
