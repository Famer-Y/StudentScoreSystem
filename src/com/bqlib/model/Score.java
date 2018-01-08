package com.bqlib.model;

public class Score {
    private long id;
    private String sSno;
    private String sName;
    private long cId;
    private String cName;
    private String tSno;
    private String tName;
    private int usualResults;
    private int examResults;
    private int experimentalResults;
    private float totalScore;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getsSno() {
    	return sSno;
    }
    public void setsSno(String sSno) {
    	this.sSno = sSno;
    }
    public String getsName() {
    	return sName;
    }
    public void setsName(String sName) {
    	this.sName = sName;
    }
    public long getcId() {
    	return cId;
    }
    public void setcId(long cId) {
    	this.cId = cId;
    }
    public String getcName() {
    	return cName;
    }
    public void setcName(String cName) {
    	this.cName = cName;
    }
    public String gettSno() {
    	return tSno;
    }
    public void settSno(String tSno) {
    	this.tSno = tSno;
    }
    public String gettName() {
    	return tName;
    }
    public void settName(String tName) {
    	this.tName = tName;
    }
    public int getUsualResults() {
    	return usualResults;
    }
    public void setUsualResults(int usualResults) {
    	this.usualResults = usualResults;
    }
    public int getExamResults() {
    	return examResults;
    }
    public void setExamResults(int examResults) {
    	this.examResults = examResults;
    }
    public int getExperimentalResults() {
    	return experimentalResults;
    }
    public void setExperimentalResults(int experimentalResults) {
    	this.experimentalResults = experimentalResults;
    }
    public float getTotalScore() {
    	return totalScore;
    }
    public void setTotalScore(double totalScore) {
    	this.totalScore = (float) totalScore;
    }

}
