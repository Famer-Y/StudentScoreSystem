package com.bqlib.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bqlib.model.Score;
import com.bqlib.model.Student;
import com.bqlib.util.DbUtil;

public class ScoreDao {

	/**
	 * 根据学生ID和课程ID查询学生成绩
	 * @return
	 * @throws Exception
	 */
	public Score selectScoreBySidAndCid(String sSno,String cId)throws Exception{
		Score score = null;
		String sql = "select sSno,cId,usualResults,examResults,experimentalResults from score where sSno = ? and cId = ?";
		ResultSet rs = DbUtil.executeQuery(sql, new Object[]{sSno,cId});
		while (rs.next())
		{
			score = new Score();
			score.setsSno(rs.getString("sSno"));
			score.setcId(rs.getLong("cId"));
			score.setUsualResults(rs.getInt("usualResults"));
			score.setExamResults(rs.getInt("examResults"));
			score.setExperimentalResults(rs.getInt("experimentalResults"));
		}
		return score;
	}
	/**
	 * 根据学生ID和课程ID修改学生成绩
	 * @return
	 * @throws Exception
	 */
	public int updateScoreBySidAndCid(String sSno,long cId,Score score)throws Exception{
		String sql = "update score set usualResults=?,examResults=?,experimentalResults=?,totalScore=? where sSno = ? and cId = ?";
		int num = DbUtil.executeUpdate(sql, new Object[]{score.getUsualResults(),score.getExamResults(),score.getExperimentalResults(),score.getTotalScore(),sSno,cId});		
		return num;
	}
	
	
	/**
	 * 统计成绩的个数
	 * @return
	 * @throws Exception
	 */
	public int countScore(String tSno) throws Exception {
	    String sql = "select count(1) as countScore from score where tSno = ?";
	    ResultSet rs = DbUtil.executeQuery(sql, new Object[]{tSno});
	    int num = 0;
	    while (rs.next()){
	        num = rs.getInt("countScore");
	    }
	    return num;
	}
	
	/**
	 * 查询部分成绩信息(用于分页显示)
	 * @param start
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<Score> listScoreLimit(Integer start, Integer size,String tSno) throws Exception{    
        
	    List<Score> listScore = new ArrayList<Score>();
        String sql = "select top "+size+" sSno,cId,tSno,usualResults,examResults,experimentalResults,totalScore from score where id not in ( select top "+ start +" id from score where tSno = ? )and tSno = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{tSno,tSno});   
        Score score = null;
        while (rs.next()){
        	  score = new Score();
              score.setcId(rs.getLong("cId"));
              score.setExamResults(rs.getInt("examResults"));
              score.setExperimentalResults(rs.getInt("experimentalResults"));
              score.setsSno(rs.getString("sSno"));
              score.setTotalScore(rs.getInt("totalScore"));
              score.settSno(rs.getString("tSno"));
              score.setUsualResults(rs.getInt("usualResults"));
              score.setTotalScore(rs.getInt("totalScore"));
               listScore.add(score);
        }        
        return listScore;
    }
	
	public int countScoreBySid(String sSno) throws Exception {
        String sql = "select count(1) as countScore from score where sSno = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{sSno});
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countScore");
        }
        return num;
    }
	
	public int addScore(String sSno, long cId, String tSno) throws Exception {
        String sql = "insert into score(sSno, cId, tSno) values(?,?,?)";
        int num = DbUtil.executeUpdate(sql, new Object[]{sSno, cId, tSno});
        return num;
    }
	
	public List<Score> listScoreLimitBySid(Integer start, Integer size, String sSno) throws Exception{    
        
        List<Score> listScore = new ArrayList<Score>();
        String sql = "select top " + size + " sSno,cId,tSno,usualResults,examResults,experimentalResults,totalScore from score where id not in ( select top " + start + " id from score where sSno = ?) and sSno = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{sSno, sSno});   
        Score score = null;
        while (rs.next()){
              score = new Score();
              score.setcId(rs.getLong("cId"));
              score.setExamResults(rs.getInt("examResults"));
              score.setExperimentalResults(rs.getInt("experimentalResults"));
              score.setsSno(rs.getString("sSno"));
              score.setTotalScore(rs.getInt("totalScore"));
              score.settSno(rs.getString("tSno"));
              score.setUsualResults(rs.getInt("usualResults"));
              score.setTotalScore(rs.getInt("totalScore"));
              listScore.add(score);
        }   
        return listScore;
    }
	
	public int countScoreAll() throws Exception {
        String sql = "select count(1) as countScore from score";
        ResultSet rs = DbUtil.executeQuery(sql, null);
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countScore");
        }
        return num;
    }
	
	public List<Score> listScoreLimit(Integer start, Integer size) throws Exception{    
        
        List<Score> listScore = new ArrayList<Score>();
        String sql = "select top " + size + " sSno,cId,tSno,usualResults,examResults,experimentalResults,totalScore from score where id not in ( select top " + start + " id from score)";
        ResultSet rs = DbUtil.executeQuery(sql, null);   
        Score score = null;
        while (rs.next()){
              score = new Score();
              score.setcId(rs.getLong("cId"));
              score.setExamResults(rs.getInt("examResults"));
              score.setExperimentalResults(rs.getInt("experimentalResults"));
              score.setsSno(rs.getString("sSno"));
              score.setTotalScore(rs.getInt("totalScore"));
              score.settSno(rs.getString("tSno"));
              score.setUsualResults(rs.getInt("usualResults"));
              score.setTotalScore(rs.getInt("totalScore"));
              listScore.add(score);
        }   
        return listScore;
    }
	
	/**
	 * 查询表中成绩（根据教师ID）
	 * @return
	 * @throws Exception
	 */
	public List<Score> listScoreAll(String tSno) throws Exception{    
        List<Score> listScore = new ArrayList<Score>();
        String sql = "select sSno,cId,tSno,usualResults,examResults,experimentalResults,totalScore from score where tSno = '"+tSno+"'";
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Score score = new Score();
           score.setcId(rs.getLong("cId"));
           score.setExamResults(rs.getInt("examResults"));
           score.setExperimentalResults(rs.getInt("experimentalResults"));
           score.setsSno(rs.getString("sSno"));
           score.setTotalScore(rs.getInt("totalScore"));
           score.settSno(rs.getString("tSno"));
           score.setUsualResults(rs.getInt("usualResults"));
           
            listScore.add(score);
        }        
        return listScore;
    }
}
