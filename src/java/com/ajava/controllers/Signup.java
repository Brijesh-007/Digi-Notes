package com.ajava.controllers;

import com.ajava.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//servlet to store user data and send otp in mail
public class Signup extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MessagingException {
        if (request.getSession().getAttribute("username") != null) {
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String pass1 = request.getParameter("pass1");
            String pass2 = request.getParameter("pass2");
            User user = dbHandler.getUserByEmail(email);
            if (user.getName() != null) {
                request.setAttribute("error", "Account with Email already exist!");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            } else if (!pass1.equals(pass2)) {
                request.setAttribute("error", "Passwors is not matching!");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
            else{
                User u = new User(name, email, pass1, false);
                dbHandler.addNewUser(u);
                String pin = randomPin();
                request.getSession().setAttribute("pin", pin);
                request.getSession().setAttribute("email", email);
                sendMail(email, pin);
                request.getRequestDispatcher("verify.jsp").forward(request, response);
            }
        }
    }
    
    private String randomPin(){
        String[] numbers = {"1","2","3","4","5","6","7","8","9"};
        StringBuilder pin = new StringBuilder();
        for(int i = 0; i < 5; i++){
            pin.append(numbers[(int)(Math.random()*(numbers.length))]);
        }
        return pin.toString();
    }

    private void sendMail(String emailAddress, String pin) throws AddressException, MessagingException {
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        final String from = "YOUR MAIL";
        final String password = "PASSWORD";
        
        Session session = Session.getInstance(properties,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });
        
        try{
            Message m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
            m.setSubject("Todo List verification");
            m.setContent("Hello, <br/> <h2>OTP FOR VERIFICATION ON TODOLIST:<br><div style='padding:10px; color:white; background:#7e57c2; border-radius:10px'>"+pin+"</div></h2>","text/html");
        Transport.send(m);
        }
        catch(Exception e){
            //Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "For SignUp";
    }// </editor-fold>

}
