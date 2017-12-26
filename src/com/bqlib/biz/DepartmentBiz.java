package com.bqlib.biz;

import java.sql.Connection;
import java.util.List;

import com.bqlib.dao.DepartmentDao;
import com.bqlib.model.Department;
import com.bqlib.util.DbUtil;

public class DepartmentBiz {
    
    private DepartmentDao departmentDao = new DepartmentDao();
    
    /**
     * 获取表中所有的院系
     * @return
     * @throws Exception
     */
    public List<Department> listDeparment() throws Exception{
        Connection conn = DbUtil.getConn();
        List<Department> departmentList = departmentDao.listDeparment();
        DbUtil.close();
        return departmentList;
    }
}
