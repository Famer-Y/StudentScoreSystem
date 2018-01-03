/**
 * 管理员业务类
 */

package com.bqlib.biz;

import java.sql.Connection;

import com.bqlib.dao.AdminDao;
import com.bqlib.model.Admin;
import com.bqlib.util.DbUtil;

public class AdministratorBiz {
    private AdminDao adminDao = new AdminDao();
    
    /**
     * 登录业务
     * @param username
     * @param pwd
     * @return
     * @throws Exception
     */
    public Admin Login(String username, String pwd) throws Exception {
        Connection conn = DbUtil.getConn();
        Admin admin = adminDao.checkUser(username, pwd);
        DbUtil.close();
        return admin;
    }
}
