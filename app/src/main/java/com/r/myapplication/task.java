package com.r.myapplication;

public class task {
    private int id;
    private String task_desc;
    private String date;


    public task(String task_desc, String date) {
        this.task_desc = task_desc;
        this.date = date;
    }

    public task(int id, String task_desc, String date) {
        this.id = id;
        this.task_desc = task_desc;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
