package com.bqlib.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bqlib.model.Department;
import com.bqlib.util.DbUtil;

public class DepartmentDao {
	
	/**
	 * 添加院系
	 * @param dep
	 * @return
	 * @throws Exception
	 */
	public int addDepartment(Department department) throws Exception{
		String sql = "insert into department(dId, dName, dAddress) values(?, ?, ?)";
		int num = DbUtil.executeUpdate(sql, new Object[]{department.getdId(), department.getdName(), department.getdAddress()});
		return num;
	}
	
	/**
	 * 按id查找院系
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	public Department getDeparmentById(String dId) throws Exception{
		Department department = null;
		String sql = "select dId, dName, dAddress from department where dId = ?";
		ResultSet rs = DbUtil.executeQuery(sql, new Object[]{dId});
		while (rs.next()){
			department = new Department();
			department.setdId(rs.getString("dId"));
			department.setdName(rs.getString("dName"));
			department.setdAddress(rs.getString("dAddress"));
		}
		return department;
	}
	
	/**
	 * 获取表中所有的院系
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	public List<Department> listDeparment() throws Exception{
        List<Department> departmentList = new ArrayList<Department>();
        String sql = "select dId, dName, dAddress from department";
        ResultSet rs = DbUtil.executeQuery(sql, null);
        while (rs.next()){
            Department department = new Department();
            department.setdId(rs.getString("dId"));
            department.setdName(rs.getString("dName"));
            department.setdAddress(rs.getString("dAddress"));
            departmentList.add(department);
        }
        return departmentList;
    }
}
