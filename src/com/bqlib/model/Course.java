package com.bqlib.model;

public class Course {
    private long cid;
    private String cName;
    private String cType;
    private String cExamtype;
    private int cTheoryHours;
    private int cExperimentalHours;
    private int cTotalHours;
    private int cCredit;
    
    public long getCid() {
        return cid;
    }
    public void setCid(long cid) {
        this.cid = cid;
    }
    public String getcName() {
        return cName;
    }
    public void setcName(String cName) {
        this.cName = cName;
    }
    public String getcType() {
        return cType;
    }
    public void setcType(String cType) {
        this.cType = cType;
    }
    public String getcExamtype() {
        return cExamtype;
    }
    public void setcExamtype(String cExamtype) {
        this.cExamtype = cExamtype;
    }
    public int getcTheoryHours() {
        return cTheoryHours;
    }
    public void setcTheoryHours(int cTheoryHours) {
        this.cTheoryHours = cTheoryHours;
    }
    public int getcExperimentalHours() {
        return cExperimentalHours;
    }
    public void setcExperimentalHours(int cExperimentalHours) {
        this.cExperimentalHours = cExperimentalHours;
    }
    public int getcTotalHours() {
        return cTotalHours;
    }
    public void setcTotalHours(int cTotalHours) {
        this.cTotalHours = cTotalHours;
    }
    public int getcCredit() {
        return cCredit;
    }
    public void setcCredit(int cCredit) {
        this.cCredit = cCredit;
    }
}
