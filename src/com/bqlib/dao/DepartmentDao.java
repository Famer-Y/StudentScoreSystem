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
	public int addDepartment(Department department) throws Exception {
		String sql = "insert into department(dId, dName, dAddress) values(?, ?, ?)";
		int num = DbUtil.executeUpdate(sql, new Object[]{department.getdId(), department.getdName(), department.getdAddress()});
		return num;
	}
	
	/**
     * 修改院系
     * @param department
     * @return
     * @throws Exception
     */
    public int updateDepartment(Department department) throws Exception {
        String sql = "update department set dName = ?, dAddress = ? where dId = ?";
        int num = DbUtil.executeUpdate(sql, new Object[]{department.getdName(), department.getdAddress(), department.getdId()});
        return num;
    }
    
    /**
     * 删除院系
     * @param department
     * @return
     * @throws Exception
     */
    public int deleteDepartment(String dId) throws Exception {
        String sql = "delete from department where dId = ?";
        int num = DbUtil.executeUpdate(sql, new Object[]{dId});
        return num;
    }
	
	/**
	 * 按id查找院系
	 * @param dId
	 * @return
	 * @throws Exception
	 */
	public Department getDeparmentById(String dId) throws Exception {
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
	public List<Department> listDeparment() throws Exception {
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
	
	/**
	 * 统计表中院系的个数
	 * @return
	 * @throws Exception
	 */
	public int countDepartment() throws Exception {
        String sql = "select count(1) as countDepartment from department";
        ResultSet rs = DbUtil.executeQuery(sql, null);
        int num = 0;
        while (rs.next()){
            num = rs.getInt("countDepartment");
        }
        return num;
    }
	
	/**
     * 查询部分院系信息(用于分页显示)
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Department> listDepartmentLimit(Integer start, Integer size) throws Exception {    
        
        List<Department> listDepartment = new ArrayList<Department>();
        String sql = "select top " + size + " dId,dName,dAddress from department where dId not in ( select top " + start + " dId from department)";
        ResultSet rs = DbUtil.executeQuery(sql, null);        
        while (rs.next()){
            Department department = new Department();
            department.setdName(rs.getString("dName"));
            department.setdId(rs.getString("dId"));
            department.setdAddress(rs.getString("dAddress"));
            listDepartment.add(department);
        }        
        return listDepartment;
    }
}
