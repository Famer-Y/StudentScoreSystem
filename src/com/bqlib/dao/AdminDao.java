package com.bqlib.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bqlib.model.Admin;
import com.bqlib.util.DbUtil;

public class AdminDao {
    
    /**
     * 检查用户
     * @return
     * @throws Exception
     */
    public Admin checkUser(String username, String pwd) throws Exception {
        Admin admin = null;
        String sql = "select * form where username = ? and pwd = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{username, pwd});
        while (rs.next()) {
            admin = new Admin();
            admin.setUsername(rs.getString("username"));
            admin.setPwd(rs.getString("pwd"));
        }
        return admin;
    }
}
