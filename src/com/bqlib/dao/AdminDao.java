package com.bqlib.dao;

import java.sql.ResultSet;

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
        String sql = "select * from admin where username = ? and pwd = ?";
        ResultSet rs = DbUtil.executeQuery(sql, new Object[]{username, pwd});
        while (rs.next()) {
            admin = new Admin();
            admin.setId(rs.getLong("id"));
            admin.setUsername(rs.getString("username"));
            admin.setPwd(rs.getString("pwd"));
        }
        return admin;
    }
    
    /**
     * 修改管理员密码
     * @param id
     * @param pwd
     * @return
     * @throws Exception 
     */
    public int updatePwd(long id, String pwd) throws Exception {
        String sql = "update admin set pwd = ? where id = ?";
        int num = DbUtil.executeUpdate(sql, new Object[]{pwd, id});
        return num;       
    }
}
