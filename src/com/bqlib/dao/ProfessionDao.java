package com.bqlib.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bqlib.model.Department;
import com.bqlib.model.Profession;
import com.bqlib.util.DbUtil;

public class ProfessionDao {
	
	/**
	 * 添加专业
	 * @param profession
	 * @return
	 * @throws Exception
	 */
	public int addProfession(Profession profession) throws Exception{
		String sql = "insert into profession(pId, pName, dId) values(?, ?, ?)";
		int num = DbUtil.executeUpdate(sql, new Object[]{profession.getpId(), profession.getpName(), profession.getdId()});
		return num;
	}
	
	/**
	 * 按id查找专业
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public Profession getProfessionById(String pId) throws Exception{
		Profession profession = null;
		String sql = "select pId, pName, dId from profession where pId = ?";
		ResultSet rs = DbUtil.executeQuery(sql, new Object[]{pId});
		while (rs.next()){
			profession = new Profession();
			profession.setpId(rs.getString("pId"));
			profession.setpName(rs.getString("pName"));
			profession.setdId(rs.getString("dId"));
		}
		return profession;
	}
	
	/**
     * 修改专业
     * @param profession
     * @return
     * @throws Exception
     */
    public int updateProfession(Profession profession) throws Exception {
        String sql = "update profession set pName = ?, dId = ? where pId = ?";
        int num = DbUtil.executeUpdate(sql, new Object[]{profession.getpName(), profession.getdId(), profession.getpId()});
        return num;
    }
    
    /**
     * 删除专业
     * @param profession
     * @return
     * @throws Exception
     */
    public int deleteProfession(String pId) throws Exception {
        String sql = "delete from profession where pId = ?";
        int num = DbUtil.executeUpdate(sql, new Object[]{pId});
        return num;
    }
	
	/**
	 * 按院系id查找专业
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	public List<Profession> listProfessionByDepartment(String dId) throws Exception{
		List<Profession> listProfession = new ArrayList<Profession>();
		String sql = "select pId, pName, dId from profession where dId = ?";
		ResultSet rs = DbUtil.executeQuery(sql, new Object[]{dId});
		while (rs.next()){
			Profession profession = new Profession();
			profession.setpId(rs.getString("pId"));
			profession.setpName(rs.getString("pName"));
			profession.setdId(rs.getString("dId"));
			listProfession.add(profession);
		}
		return listProfession;
	}
	
	/**
	 * 获取表中所有的专业
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	public List<Profession> listProfession() throws Exception{
	    List<Profession> listProfession = new ArrayList<Profession>();
        String sql = "select pId, pName, dId from profession";
        ResultSet rs = DbUtil.executeQuery(sql, null);
        while (rs.next()){
            Profession profession = new Profession();
            profession.setpId(rs.getString("pId"));
            profession.setpName(rs.getString("pName"));
            profession.setdId(rs.getString("dId"));
            listProfession.add(profession);
        }
        return listProfession;
	}
	
	/**
     * 统计表中专业的个数
     * @return
     * @throws Exception
     */
    public int countProfession() throws Exception {
        String sql = "select count(1) as countProfession from profession";
        ResultSet rs = DbUtil.executeQuery(sql, null);
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countProfession");
        }
        return num;
    }
    
    /**
     * 查询部分专业信息(用于分页显示)
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Profession> listProfessionLimit(Integer start, Integer size) throws Exception {    
        
        List<Profession> listProfession = new ArrayList<Profession>();
        String sql = "select top " + size + " pId,pName,dId from profession where pId not in ( select top " + start + " pId from profession)";
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Profession profession = new Profession();
            profession.setpName(rs.getString("pName"));
            profession.setpId(rs.getString("pId"));
            profession.setdId(rs.getString("dId"));
            listProfession.add(profession);
        }        
        return listProfession;
    }
}
