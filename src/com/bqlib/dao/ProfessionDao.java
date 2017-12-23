package com.bqlib.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
		}
		return listProfession;
	}
}
