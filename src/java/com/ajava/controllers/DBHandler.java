package com.ajava.controllers;

import com.ajava.models.Todo;
import com.ajava.models.User;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

// Class for handling all database related tasks
public class DBHandler {

    Connection con;
    PreparedStatement addAccount, updateAccount, loginAccount, emailAccount, listInsert, listUpdate, listDone, listDelete, getlistByUidStatment, getlistByUidAndNidStatment, emailIncomplete;

    public DBHandler() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //*****************************Account**************************************************************
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist?zeroDateTimeBehavior=CONVERT_TO_NULL&jdbcCompliantTruncation=false", "root", "");

            addAccount = con.prepareStatement("INSERT INTO user(name, email, password, isverified) VALUES (?,?,?,?)");

            updateAccount = con.prepareStatement("UPDATE user SET name = ?, password = ?, email = ? WHERE uid = ?");

            loginAccount = con.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");

            emailAccount = con.prepareStatement("SELECT * FROM user WHERE email = ?");

            listInsert = con.prepareStatement("INSERT INTO todo(uid, title, type, details, deadline) values (?,?,?,?,?)");

            listDelete = con.prepareStatement("DELETE FROM todo WHERE nid=?");

            listDone = con.prepareStatement("UPDATE todo SET status=1 where nid=?");

            emailIncomplete = con.prepareStatement("SELECT * FROM user INNER JOIN todo ON user.uid=todo.uid WHERE todo.status=0");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean addNewUser(User u) {
        try {
            addAccount.setString(1, u.getName());
            addAccount.setString(2, u.getEmail());
            addAccount.setString(3, u.getPassword());
            addAccount.setBoolean(4, u.isVerified());

            int x = addAccount.executeUpdate();
            return x > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void makeUserVerified(String email) {
        PreparedStatement verify;
        try {
            verify = con.prepareStatement("UPDATE user SET isverified=1 WHERE email=?");
            verify.setString(1, email);
            verify.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public User authenticate(String email, String password) {
        User user = null;
        try {
            loginAccount.setString(1, email);
            loginAccount.setString(2, password);
            System.out.println(loginAccount);
            ResultSet set = loginAccount.executeQuery();
            boolean found = set.next();
            if (found) {
                user = new User();
                user.setDetails(set);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = new User();
        try {
            emailAccount.setString(1, email);
            ResultSet set = emailAccount.executeQuery();
            if (set.next()) {
                user.setDetails(set);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public boolean updateUser(User user) {
        int rows = 0;
        try {
            updateAccount.setString(1, user.getName());
            updateAccount.setString(2, user.getPassword());
            updateAccount.setString(3, user.getEmail());
            updateAccount.setInt(4, user.getUid());
            rows = updateAccount.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rows > 0;
    }

    public boolean addTodo(Todo t) {
        int x = 0;
        try {
            listInsert.setInt(1, t.getUid());
            listInsert.setString(2, t.getTitle());
            listInsert.setString(3, t.getType());
            listInsert.setString(4, t.getDetails());
//            listInsert.setInt(5, t.getStatus());
            listInsert.setDate(5, new java.sql.Date(t.getDeadline().getTime()));
            x = listInsert.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x > 0;

    }

    public boolean removeTodo(int nid) {
        int x = 0;
        try {
            listDelete.setInt(1, nid);
            x = listDelete.executeUpdate();
        } catch (Exception e) {
        }
        return x > 0;
    }

    public boolean doneTodo(int nid) {
        int x = 0;
        try {
            listDone.setInt(1, nid);
            x = listDone.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x > 0;
    }

    public String[] getEmailofIncompleteTask() throws SQLException {
        String emails = "";
        Date d = new Date(System.currentTimeMillis());
        java.sql.Date sd = new java.sql.Date(d.getTime());
        ResultSet rs = emailIncomplete.executeQuery();
        while (rs.next()) {
//            System.out.println(rs.getDate("deadline"));
            
            if (sd.toString().equals(rs.getDate("deadline").toString())) {
                emails += rs.getString("email") + " ";
            }
        }
        System.out.println(emails);
        emails = emails.strip();
        String[] email = emails.split(" ");
        emails = "";
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < email.length; i++) {
            map.put(email[i], 1);
        }
        String s = new String(map.keySet().toString());
        s = s.substring(1, s.length() - 1);
        return s.split(", ");
    }
}
