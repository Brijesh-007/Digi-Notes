package com.ajava.models;

import java.util.Date;
//Bean ckass for TODO
public class Todo {

    int nid;
    int uid;
    String title;
    String type;
    String details;
    int status = 0;
    Date deadline;

    public Todo() {
    }

    public Todo(int nid, int uid, String title, String type, String details, int status, Date deadline) {
        this.nid = nid;
        this.uid = uid;
        this.title = title;
        this.type = type;
        this.details = details;
        this.status = status;
        this.deadline = deadline;
    }

    public int getNid() {
        return nid;
    }

    public int getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }

    public int getStatus() {
        return status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Notes{" + "nid=" + nid + ", uid=" + uid + ", title=" + title + ", type=" + type + ", details=" + details + '}';
    }
}
