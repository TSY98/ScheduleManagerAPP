package com.example.schedulemanagerapp.schedule;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class ScheduleInfo extends DataSupport implements Serializable {
    private long id;

    //设置等级
    //1-important
    //2-medium(默认)
    //3-easy
    private int importance;

    //事件
    private String scheduleTitle;

    //备注
    private String remark;

    //设置是否完成
    private boolean isDone;

    //日程的时间
    //唯一一个public
    private String date;

    //是否提醒
    private Boolean remind;

    //构造函数
    public ScheduleInfo(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getRemind() {
        return remind;
    }

    public void setRemind(Boolean remind) {
        this.remind = remind;
    }
}
