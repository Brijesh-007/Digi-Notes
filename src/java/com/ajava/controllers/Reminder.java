package com.ajava.controllers;

import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//Weblistner class to send emails to user for reminder
@WebListener
public class Reminder implements ServletContextListener {

    private ServletContext app;
    private ScheduledExecutorService scheduler;
    DBHandler dBHandler;

    //What to do when app is deployed
    public void contextInitialized(ServletContextEvent event) {
        //Init Context
        app = event.getServletContext();
        dBHandler = new DBHandler();
        //In my case I store my data in the servlet context with setAttribute("myKey", myObject)
        //and retrieve it with getAttribute("myKey") in any Servlet

        // Scheduler
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new AutomateRefresh(), 0, 24, TimeUnit.HOURS);
    }

    //Do not forget to destroyed your thread when app is destroyed
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdown();
    }

    //Your function
    public class AutomateRefresh implements Runnable {

        public void run() {
            while (true) {

                try {
                    String[] emails = dBHandler.getEmailofIncompleteTask();
                    for (int i = 0; i < emails.length; i++) {
                        System.out.println(emails[i]);
                        Properties properties = new Properties();

                        properties.put("mail.smtp.auth", "true");
                        properties.put("mail.smtp.starttls.enable", "true");
                        properties.put("mail.smtp.host", "smtp.gmail.com");
                        properties.put("mail.smtp.port", "587");

                        final String from = "Your Mail";
                        final String password = "Password";

                        Session session = Session.getInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(from, password);
                            }
                        });

                        try {
                            Message m = new MimeMessage(session);
                            m.setFrom(new InternetAddress(from));
                            m.setRecipient(Message.RecipientType.TO, new InternetAddress(emails[i]));
                            m.setSubject("Digi-Notes Reminder");
                            m.setContent("Hello, <br/> <h2><br><div style='padding:10px; color:white; background:#7e57c2; border-radius:10px'> This is just for reminder that some of your tasks in todo list have deadline today. Please complete it by today. </div></h2>", "text/html");
                            Transport.send(m);
                        } catch (Exception e) {
                            //Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,e);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Reminder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
